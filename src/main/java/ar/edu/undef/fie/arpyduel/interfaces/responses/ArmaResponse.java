package ar.edu.undef.fie.arpyduel.interfaces.responses;

import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;

import javax.persistence.ManyToOne;

public class ArmaResponse {
    private Long id;
    private Double Dmg;
    private Double bonusPoder;
    private Double bonusVelocidad;
    private Double bonusMagico;
    private Double probabilidadCritico;
    private Encantamiento encantamiento;

    public ArmaResponse(Long id, Double dmg, Double bonusPoder, Double bonusVelocidad, Double bonusMagico,
                        Double probabilidadCritico, Encantamiento encantamiento) {
        this.id = id;
        this.Dmg = dmg;
        this.bonusPoder = bonusPoder;
        this.bonusVelocidad = bonusVelocidad;
        this.bonusMagico = bonusMagico;
        this.probabilidadCritico = probabilidadCritico;
        this.encantamiento = encantamiento;
    }

    public Long getId() {
        return id;
    }

    public Double getDmg() {
        return Dmg;
    }

    public Double getBonusPoder() {
        return bonusPoder;
    }

    public Double getBonusVelocidad() {
        return bonusVelocidad;
    }

    public Double getBonusMagico() {
        return bonusMagico;
    }

    public Double getProbabilidadCritico() {
        return probabilidadCritico;
    }

    public Encantamiento getEncantamiento() {
        return encantamiento;
    }
}
