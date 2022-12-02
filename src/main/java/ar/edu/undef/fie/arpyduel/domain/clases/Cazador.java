package ar.edu.undef.fie.arpyduel.domain.clases;


import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class Cazador extends Clase {
    @Override
    public void subirEstadisticasNivel(Personaje me) {
        me.setVidaMaxima(me.getVidaMaxima()+1.5*me.getNivel());
        me.setPoder(me.getPoder()+0.15*me.getNivel());
        me.setPoderMagico(me.getPoderMagico()+1);
        me.setProbabilidadCritico(me.getProbabilidadCritico()+0.0007*me.getNivel());
    }

    public Cazador(String nombre) {
        super(nombre);
    }

    public Cazador() {
    }
}
