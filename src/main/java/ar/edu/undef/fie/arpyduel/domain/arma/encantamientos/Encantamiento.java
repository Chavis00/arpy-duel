package ar.edu.undef.fie.arpyduel.domain.arma.encantamientos;


import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Encantamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreEncantamiento;

    public Encantamiento(String nombreEncantamiento) {
        this.nombreEncantamiento = nombreEncantamiento;
    }

    public Encantamiento() {

    }

    public abstract double poderEspecial(Personaje me, Personaje rival, Double dmg);

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombreEncantamiento() {
        return nombreEncantamiento;
    }

    public void setNombreEncantamiento(String nombreEncantamiento) {
        this.nombreEncantamiento = nombreEncantamiento;
    }
}
