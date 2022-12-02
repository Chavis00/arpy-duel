package ar.edu.undef.fie.arpyduel.domain.arma.encantamientos;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;
import java.util.Random;
@Entity
public class DobleJugada extends Encantamiento {
    public DobleJugada(String nombreEncantamiento) {
        super(nombreEncantamiento);
    }

    public DobleJugada() {

    }

    @Override
    public double poderEspecial(Personaje me , Personaje rival , Double dmg) {
        Random rn = new Random();
        int probabilidadDeDobleJugada = rn.nextInt(100) + 1;

        if (probabilidadDeDobleJugada < 50){
            return 2*dmg;
        }
        else {
            me.bajarVida(dmg/3);
            return 0;
        }
    }
}
