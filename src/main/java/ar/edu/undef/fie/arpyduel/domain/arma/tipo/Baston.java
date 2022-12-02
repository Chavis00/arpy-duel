package ar.edu.undef.fie.arpyduel.domain.arma.tipo;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;

@Entity
public class Baston extends ArmaTipo {
    public Baston() {
        super("Baston");
    }

    @Override
    public double efectoArma(Personaje me, Personaje adversario) {
        double dmg = me.getPoderMagico() + me.getArmaEnUso().getDmg();
        if (me.getArmaEnUso().esCritico(me.getProbabilidadCritico())){
            dmg = 2 * dmg;
        }
        return dmg;
    }

    @Override
    public void nombreArma() {

    }
}
