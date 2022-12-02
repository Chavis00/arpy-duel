package ar.edu.undef.fie.arpyduel.domain.estado;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreEstado;


    public Estado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Estado() {
        
    }

    public abstract void efectoEstado(Personaje personaje);

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }








}
