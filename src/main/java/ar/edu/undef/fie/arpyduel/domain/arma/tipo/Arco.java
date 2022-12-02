package ar.edu.undef.fie.arpyduel.domain.arma.tipo;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class Arco extends ArmaTipo {

    public Arco() {
        super("Arco");
    }


    @Override
    public double efectoArma(Personaje me, Personaje rival) {
        double dmg = me.getPoder() + me.getArmaEnUso().getDmg();
        if (me.getArmaEnUso().esCritico(me.getProbabilidadCritico())){
            dmg = 2 * dmg;
        }

        return dmg;
    }

    @Override
    public void nombreArma() {
        System.out.println(this.getClass().getSimpleName());
    }
}
