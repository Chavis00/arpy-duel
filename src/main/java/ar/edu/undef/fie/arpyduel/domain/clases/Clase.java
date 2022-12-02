package ar.edu.undef.fie.arpyduel.domain.clases;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ClaseResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public ClaseResponse response(){
        return new ClaseResponse(id, nombre);
    }

    public Clase(String nombre) {
        this.nombre = nombre;
    }

    public Clase() {
    }

    public abstract void subirEstadisticasNivel(Personaje me);

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
