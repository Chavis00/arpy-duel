package ar.edu.undef.fie.arpyduel.domain.armadura;


import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaduraResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class Armadura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double bonusVida;
    private Double bonusCritico;
    private Double bonusPoderMagico;


    public ArmaduraResponse response(){
        return new ArmaduraResponse(id, bonusVida, bonusCritico, bonusPoderMagico);
    }
    public Armadura(){
        this.bonusVida = 1.0;
        this.bonusCritico = 1.0;
        this.bonusPoderMagico = 1.0;
    }


    public void construirArmadura(Double nivel) {
        double aumentoCritico = 0;
        double aumentoVida = 0;
        double aumentoPoderMagico = 0;
        Random rn = new Random();

        int probabilidadDeAumento = rn.nextInt(100) + 1;
        if (probabilidadDeAumento<20)
            aumentoCritico += nivel/120;
        probabilidadDeAumento = rn.nextInt(100) + 1;
        if (probabilidadDeAumento<20)
            aumentoVida += nivel/120;
        probabilidadDeAumento = rn.nextInt(100) + 1;
        if (probabilidadDeAumento<20)
            aumentoPoderMagico += nivel/120;

        setBonusCritico(getBonusCritico()*(1.04+aumentoCritico));
        setBonusVida(getBonusVida()*(1.2+aumentoVida));
        setBonusPoderMagico(getBonusPoderMagico()*(1.2+aumentoPoderMagico));
    }

    public Double getBonusCritico() {
        return bonusCritico;
    }

    public void setBonusCritico(Double bonusCritico) {
        this.bonusCritico = bonusCritico;
    }

    public Double getBonusPoderMagico() {
        return bonusPoderMagico;
    }

    public void setBonusPoderMagico(Double bonusPoderMagico) {
        this.bonusPoderMagico = bonusPoderMagico;
    }



    public Double getBonusVida() {
        return bonusVida;
    }

    public void setBonusVida(Double bonusVida) {
        this.bonusVida = bonusVida;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
