package ar.edu.undef.fie.arpyduel.domain.items.efectosItem;

import ar.edu.undef.fie.arpyduel.domain.estado.ErrorDePunteria;
import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.AplicarConfusion;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class AplicarErrorDePunteria extends ItemEfecto {

    public AplicarErrorDePunteria() {
        super("Reduce Probabilidad de Critico del Enemigo");
    }



    @Override
    public void especialItem(Personaje me) {
        me.getDueloActual().getRival(me).setEstado(new ErrorDePunteria("Error de Punteria"));
    }
}
