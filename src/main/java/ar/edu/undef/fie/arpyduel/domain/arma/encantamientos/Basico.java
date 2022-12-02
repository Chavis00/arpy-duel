package ar.edu.undef.fie.arpyduel.domain.arma.encantamientos;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class Basico extends Encantamiento {

    public Basico(String nombreEncantamiento) {
        super(nombreEncantamiento);
    }

    public Basico() {

    }

    @Override
    public double poderEspecial(Personaje me , Personaje rival , Double dmg) {
        return dmg;
    }
}
