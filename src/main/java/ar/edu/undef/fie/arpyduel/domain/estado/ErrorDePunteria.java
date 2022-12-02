package ar.edu.undef.fie.arpyduel.domain.estado;


import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;

@Entity
public class ErrorDePunteria extends Estado {


    public ErrorDePunteria(String nombreEstado) {
        super(nombreEstado);
    }

    public ErrorDePunteria() {

    }

    @Override
    public void efectoEstado(Personaje personaje) {
        personaje.setProbabilidadCritico(personaje.getProbabilidadCritico()*0.75);
        personaje.getItem().setUsado(true);
    }
}
