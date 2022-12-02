package ar.edu.undef.fie.arpyduel.domain.estado;


import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class QuemarMagia extends Estado {
    private Boolean usado = false;

    public QuemarMagia(String nombreEstado) {
        super(nombreEstado);
    }

    public QuemarMagia() {

    }

    @Override
    public void efectoEstado(Personaje personaje) {
        personaje.bajarVida(personaje.getPoderMagico()*0.4);
        this.usado = true;
    }
}
