package ar.edu.undef.fie.arpyduel.domain.estado;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Confuso extends Estado {
    private Boolean usado;

    public Confuso(String nombreEstado) {
        super(nombreEstado);
    }

    public Confuso() {

    }

    @Override
    public void efectoEstado(Personaje personaje) {
        Random rn = new Random();
        int probabilidadDeEfecto = rn.nextInt(100) + 1;

        if (probabilidadDeEfecto < 25){
            personaje.bajarVida(personaje.getPoder());
            personaje.getDueloActual().pasarTurno();
        }
        this.usado = true;
    }

}
