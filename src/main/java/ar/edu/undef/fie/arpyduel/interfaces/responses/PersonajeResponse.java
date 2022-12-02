package ar.edu.undef.fie.arpyduel.interfaces.responses;

import ar.edu.undef.fie.arpyduel.domain.clases.Clase;

public class PersonajeResponse {
    private Long id;
    private String name;
    private double vida;
    private double nivel;
    private String experiencia;
    private Integer claims;
    private Boolean isDead;
    protected Double velocidad;
    private Double probabilidadCritico;
    private Double poderMagico;
    private Double poder;

    private DueloResponse dueloActual;
    private String clase;
    private String estado;
    private ArmaResponse armaEnUso;
    private ArmaduraResponse armaduraEnUso;



    public PersonajeResponse(Long id, Double vida, Double nivel, Double velocidad, Double probabilidadCritico, Double poderMagico,
                             Double poder, String name, ArmaResponse armaEnUso, ArmaduraResponse armaduraEnUso,
                             Boolean isDead, DueloResponse dueloActual,
                             Clase clase, String estado, Integer claims, Double experiencia, Double experienciaNecesaria) {
        this.id = id;
        this.name = name;
        this.vida = vida;
        this.nivel = nivel;
        this.experiencia = experiencia.toString()+" / "+experienciaNecesaria.toString();
        this.claims = claims;
        this.isDead = isDead;

        this.velocidad = velocidad;
        this.probabilidadCritico = probabilidadCritico;
        this.poderMagico = poderMagico;
        this.poder = poder;

        this.dueloActual = dueloActual;
        this.clase = clase.getNombre();
        this.estado = estado;
        this.armaEnUso = armaEnUso;
        this.armaduraEnUso = armaduraEnUso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public Double getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArmaResponse getArmaEnUso() {
        return armaEnUso;
    }

    public void setArmaEnUso(ArmaResponse armaEnUso) {
        this.armaEnUso = armaEnUso;
    }

    public ArmaduraResponse getArmaduraEnUso() {
        return armaduraEnUso;
    }

    public void setArmaduraEnUso(ArmaduraResponse armaduraEnUso) {
        this.armaduraEnUso = armaduraEnUso;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }
    public DueloResponse getDueloActual() {
        return dueloActual;
    }

    public void setDueloActual(DueloResponse dueloActual) {
        this.dueloActual = dueloActual;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getClaims() {
        return claims;
    }

    public String getExperiencia() {
        return experiencia;
    }
}
