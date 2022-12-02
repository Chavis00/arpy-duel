package ar.edu.undef.fie.arpyduel.domain.arma;

import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaResponse;

import javax.persistence.*;
import java.util.Random;
@Entity
public class Arma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double Dmg;
    private Double bonusPoder;
    private Double bonusVelocidad;
    private Double bonusMagico;
    private Double probabilidadCritico;
    @ManyToOne
    private ArmaTipo tipo;
    @ManyToOne
    private Encantamiento encantamiento;

    public ArmaResponse response(){
        return new ArmaResponse(id, Dmg, bonusPoder,bonusVelocidad, bonusMagico, probabilidadCritico, encantamiento);
    }

    public Arma(ArmaTipo tipoArma) {
        this.Dmg = 1.0;
        this.bonusPoder = 1.0;
        this.bonusVelocidad = 1.0;
        this.bonusMagico = 1.0;
        this.probabilidadCritico = 0.05;
        this.tipo = tipoArma;
        this.encantamiento = null;
    }

    public Arma() {

    }

    public void construirArma(Double nivel){
        double aumento = 1;
        Random rn = new Random();
        int probabilidadDeAumento = rn.nextInt(100) + 1;
        if (probabilidadDeAumento<20)
            aumento += nivel/150;

        setDmg(getDmg()+nivel*0.5*aumento);
        setBonusPoder(getBonusPoder()+nivel*0.5*aumento);
        setBonusVelocidad(getBonusVelocidad()+nivel*0.5*aumento);
        setProbabilidadCritico(getProbabilidadCritico()+nivel*0.00035*aumento);
        setBonusMagico(getBonusMagico()+nivel*0.5*aumento);
    }


    public double dmgDeArma(Personaje me, Personaje rival){
        double dmg = tipo.efectoArma(me, rival);
        if (encantamiento != null)
            dmg = encantamiento.poderEspecial(me, rival, dmg);
        return dmg;

    }

    public  Boolean esCritico(Double probabilidadDeCritico){
        Random rn = new Random();
        int critico = rn.nextInt(100) + 1;
        return critico < (probabilidadDeCritico * 100);
    }

    /*
        GETTERS & SETTERS
    */
    public void setTipo(ArmaTipo t){
        tipo = t;
    }

    public Double getBonusPoder() {
        return bonusPoder;
    }

    public void setBonusPoder(Double bonusPoder) {
        this.bonusPoder = bonusPoder;
    }

    public Double getBonusVelocidad() {
        return bonusVelocidad;
    }

    public void setBonusVelocidad(Double bonusVelocidad) {
        this.bonusVelocidad = bonusVelocidad;
    }

    public Double getBonusMagico() {
        return bonusMagico;
    }

    public void setBonusMagico(Double bonusMagico) {
        this.bonusMagico = bonusMagico;
    }

    public Double getProbabilidadCritico() {
        return probabilidadCritico;
    }

    public void setProbabilidadCritico(Double probabilidadCritico) {
        this.probabilidadCritico = probabilidadCritico;
    }

    public Double getDmg() {
        return Dmg;
    }

    public void setDmg(Double dmg) {
        Dmg = dmg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public ArmaTipo getTipo() {
        return tipo;
    }

    public Encantamiento getEncantamiento() {
        return encantamiento;
    }

    public void setEncantamiento(Encantamiento encantamiento) {
        this.encantamiento = encantamiento;
    }
}
