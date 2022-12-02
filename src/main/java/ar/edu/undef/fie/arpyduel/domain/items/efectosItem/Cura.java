package ar.edu.undef.fie.arpyduel.domain.items.efectosItem;

import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.AplicarConfusion;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class Cura extends ItemEfecto {
    public Cura() {
        super("Cura 30% de la vida actual");
    }


    @Override
    public void especialItem(Personaje me) {
        me.setVida(me.getVida()*1.3);
    }
}
