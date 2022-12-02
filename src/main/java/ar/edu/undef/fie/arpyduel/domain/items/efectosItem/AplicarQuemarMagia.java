package ar.edu.undef.fie.arpyduel.domain.items.efectosItem;

import ar.edu.undef.fie.arpyduel.domain.estado.QuemarMagia;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class AplicarQuemarMagia extends ItemEfecto {
    public AplicarQuemarMagia() {
        super("Reduce Ataque magico del Enemigo");
    }


    @Override
    public void especialItem(Personaje me) {
        me.getDueloActual().getRival(me).setEstado(new QuemarMagia("QUEMAR MAGIA"));

    }
}
