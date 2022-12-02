package ar.edu.undef.fie.arpyduel.domain.items.efectosItem;

import ar.edu.undef.fie.arpyduel.domain.estado.Confuso;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class AplicarConfusion extends ItemEfecto {

    public AplicarConfusion() {
        super("Aplica Confusion al oponente");
    }


    @Override
    public void especialItem(Personaje me) {
        me.getDueloActual().getRival(me).setEstado(new Confuso("Confusion"));
    }


}
