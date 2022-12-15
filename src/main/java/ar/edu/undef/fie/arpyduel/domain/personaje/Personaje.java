package ar.edu.undef.fie.arpyduel.domain.personaje;

import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.AplicarConfusion;
import ar.edu.undef.fie.arpyduel.domain.items.Item;
import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.domain.estado.Estado;
import ar.edu.undef.fie.arpyduel.interfaces.responses.PersonajeResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double vida;
    private Double vidaMaxima;
    private Double nivel;
    protected Double velocidad;
    private Double probabilidadCritico;
    private Double poderMagico;
    private Double poder;
    @Column(nullable = false)
    private String name;
    @OneToOne
    private Arma armaEnUso;
    @OneToOne
    private Armadura armaduraEnUso;
    private Boolean isDead;
    @OneToMany
    private List<Duelo> duelosPendientes;
    @OneToOne
    private Duelo dueloActual;
    @OneToOne
    private Clase clase;
    @OneToOne
    private Item item;
    @OneToOne
    private Estado estado;
    private Integer claims;
    @OneToOne
    private Arma armaClaim;
    @OneToOne
    private Armadura armaduraClaim;
    @OneToOne
    private Item itemClaim;
    private Double experiencia;
    private Double experienciaNecesaria;


    public PersonajeResponse response(){
        return new PersonajeResponse(id, vida, nivel, velocidad, probabilidadCritico, poderMagico, poder, name,
                getArmaEnUsoOp().map(Arma::response).orElse(null),
                getArmaduraEnUsoOp().map(Armadura::response).orElse(null),
                isDead, getDueloActualOp().map(Duelo::response).orElse(null),
                clase, getEstadoOp().map(Estado::getNombreEstado).orElse(null), claims,
                experiencia, experienciaNecesaria);
    }
    public Personaje(String nombrePersonaje, Clase clasePersonaje){
        //Cada vez que se crea un personaje empieza en lvl 1
        this.name = nombrePersonaje;
        this.clase = clasePersonaje;
        this.vida = 100.0;
        this.vidaMaxima = this.vida;
        this.nivel = 1.0;
        this.velocidad = 1.0;
        this.probabilidadCritico = 0.15;
        this.poderMagico = 10.0;
        this.poder = 10.0;
        this.armaEnUso = null;
        this.armaduraEnUso = null;
        this.isDead = false;
        this.item = null;
        this.claims = 4;
        this.experiencia = 0.0;
        this.experienciaNecesaria = nivel*10;
        duelosPendientes = new ArrayList<Duelo>();
    }

    public Personaje() {

    }

    public Duelo retarDuelo(Personaje personaje){
        // Se ingresa personaje a retar y se retorna el duelo creado
        List<Personaje> personajes = new ArrayList<Personaje>();
        personajes.add(this);
        personajes.add(personaje);
        Duelo duelo = new Duelo(personajes);
        duelo.setRetador(this.getName());
        duelo.setPersonajes(personajes);
        personaje.agregarDueloPendiente(duelo);
        return duelo;
    }


    public void subirNivel(){
        //Sube de nivel hasta lvl 30
        if (nivel >= 30){
            return;
        }
        setExperiencia(getExperiencia()-getExperienciaNecesaria());
        setExperienciaNecesaria(getExperienciaNecesaria()*1.3);
        if (getExperiencia()<0)
            setExperiencia(0.0);
        nivel+=1;
        clase.subirEstadisticasNivel(this);
    }
    public void claimArmadura(Armadura armadura){
        this.armaduraClaim = armadura;
    }
    public void equiparClaimArmadura(Armadura armadura){
        this.armaduraClaim = armadura;
    }
    public void claimArma(Arma arma){
        this.armaClaim = arma;
    }
    public void equiparClaimArma(Arma arma){
        this.armaClaim = arma;
    }


    public void agregarDueloPendiente(Duelo duelo){
        // Se ingresa duelo y se anade a la lista de duelos pendientes del personaje
        duelosPendientes.add(duelo);
    }

    public Duelo aceptarDuelo(Personaje retador, Duelo duelo){
        // Se ingresa duelo y retador y empieza el duelo
        retador.setDueloActual(duelo);
        setDueloActual(duelo);
        this.duelosPendientes.remove(duelo);
        return duelo;
    }

    public void rechazarDuelo(Duelo duelo){
        // Se ingresa duelo y se rechaza
        duelosPendientes.remove(duelo);
    }

    public Duelo rendirse(Duelo duelo){
        this.duelosPendientes.remove(duelo);
        setDueloActual(null);
        duelo.setGanador(duelo.getRival(this));
        return duelo;
    }


    public Duelo ataqueBasico(Personaje rival){
        infligirDmg(rival, getPoder());
        this.dueloActual.pasarTurno();
        return this.dueloActual;
    }

    public Duelo ataqueArma(Personaje rival){
        infligirDmg(rival, armaEnUso.dmgDeArma(this, rival));
        this.dueloActual.pasarTurno();
        return this.dueloActual;
    }

    void infligirDmg(Personaje rival, double dmg){
        if (isDead){
            System.out.println("Estas muerto..");
            return;
        }
        rival.bajarVida(dmg);
    }
    public Boolean claimsVacios(){
        if (this.claims<1)
                return true;
        return false;

    }
    public void bajarVida(double dmg) {
        if (isDead){
            System.out.println(name + " ya esta muerto...");
            return;
        }

        this.vida = this.vida - dmg;
        if (this.vida <=0)
            kill();
    }

    public void kill(){
        setVida(0.0);
        isDead = true;
        dueloActual.setGanador(dueloActual.getRival(this));
    }

    private void aplicarEstadisticasArmadura(Armadura armadura) {
        this.vida += armadura.getBonusVida();
        this.probabilidadCritico += armadura.getBonusCritico();
        this.poderMagico += armadura.getBonusPoderMagico();
    }
    private void removerEstadisticasArmadura() {
        this.vida -= this.armaduraEnUso.getBonusVida();
        this.probabilidadCritico -= this.armaduraEnUso.getBonusCritico();
        this.poderMagico -= this.armaduraEnUso.getBonusPoderMagico();
    }

    public void usarArmadura(Armadura armadura){
        setArmaduraEnUso(armadura);
        aplicarEstadisticasArmadura(armadura);
    }

    public void retirarArmadura(){
        removerEstadisticasArmadura();
        setArmaduraEnUso(null);
    }
    public void cambiarArmadura(Armadura armadura){
        if (armaduraEnUso != null){
            retirarArmadura();
        }
        usarArmadura(armadura);
    }

    public Duelo usarItem(){
        this.item.especialItem(this);
        this.dueloActual.pasarTurno();
        return this.dueloActual;
    }

    public void equiparArmaClaim(Arma arma){
        this.armaEnUso = arma;
        setArmaClaim(null);
    }
    public void equiparArmaduraClaim(Armadura armadura){
        this.armaduraEnUso = armadura;
        setArmaduraClaim(null);
    }
    public void equiparItemClaim(Item item){
        this.item = item;
        setItemClaim(null);
    }
    /*
        GETTERS & SETTERS
     */

    public List<Duelo> getDuelosPendientes() {
        return duelosPendientes;
    }

    public void setDuelosPendientes(List<Duelo> duelosPendientes) {
        this.duelosPendientes = duelosPendientes;
    }

    public Duelo getDueloActual() {
        return dueloActual;
    }

    public void setDueloActual(Duelo dueloActual) {
        this.dueloActual = dueloActual;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNivel() {
        return nivel;
    }

    public void setNivel(Double nivel) {
        this.nivel = nivel;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public Double getProbabilidadCritico() {
        return probabilidadCritico;
    }

    public void setProbabilidadCritico(Double probabilidadCritico) {
        this.probabilidadCritico = probabilidadCritico;
    }

    public Double getPoderMagico() {
        return poderMagico;
    }

    public void setPoderMagico(Double poderMagico) {
        this.poderMagico = poderMagico;
    }

    public Double getPoder() {
        return poder;
    }

    public void setPoder(Double poder) {
        this.poder = poder;
    }

    public Arma getArmaEnUso() {
        return armaEnUso;
    }

    public void setArmaEnUso(Arma armaEnUso) {
        this.armaEnUso = armaEnUso;
    }

    public Armadura getArmaduraEnUso() {
        return armaduraEnUso;
    }

    public void setArmaduraEnUso(Armadura armaduraEnUso) {
        this.armaduraEnUso = armaduraEnUso;
    }

    public double getVida() {
        return vida;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getClaims() {
        return claims;
    }

    public Item getItemClaim() {
        return itemClaim;
    }

    public void setItemClaim(Item itemClaim) {
        this.itemClaim = itemClaim;
    }

    public void setClaims(Integer claims) {
        this.claims = claims;
    }

    public Arma getArmaClaim() {
        return armaClaim;
    }

    public void setArmaClaim(Arma armaClaim) {
        this.armaClaim = armaClaim;
    }

    public Armadura getArmaduraClaim() {
        return armaduraClaim;
    }

    public void setArmaduraClaim(Armadura armaduraClaim) {
        this.armaduraClaim = armaduraClaim;
    }

    public void setVida(Double vida) {
        this.vida = vida;
    }

    public Double getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(Double vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public Double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Double experiencia) {
        this.experiencia = experiencia;
    }

    public Double getExperienciaNecesaria() {
        return experienciaNecesaria;
    }

    public void setExperienciaNecesaria(Double experienciaNecesaria) {
        this.experienciaNecesaria = experienciaNecesaria;
    }

    //Optional
    public Optional<Arma> getArmaEnUsoOp() {
        return Optional.ofNullable(armaEnUso);
    }
    public Optional<Armadura> getArmaduraEnUsoOp() {
        return Optional.ofNullable(armaduraEnUso);
    }
    public Optional<Duelo> getDueloActualOp() {
        return Optional.ofNullable(dueloActual);
    }
    public Optional<Item> getItemOp() {
        return Optional.ofNullable(item);
    }
    public Optional<Estado> getEstadoOp() {
        return Optional.ofNullable(estado);
    }


    public Duelo terminarDuelo(Duelo duelo) {
        if (this.isDead == true)
                setDead(false);
        if (dueloActual.getGanador() == this){
            setExperiencia(dueloActual.getRival(this).getNivel()*3);
            setClaims(getClaims()+4);
        }
        if (getExperiencia() >= getExperienciaNecesaria()){

            subirNivel();
        }
        duelo.setPersonajes(duelo.getPersonajes().stream().filter(p->p==this).collect(Collectors.toList()));
        setVida(getVidaMaxima());
        setDueloActual(null);
        return duelo;
    }
}
