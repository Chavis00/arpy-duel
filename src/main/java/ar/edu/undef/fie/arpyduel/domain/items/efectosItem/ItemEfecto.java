package ar.edu.undef.fie.arpyduel.domain.items.efectosItem;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemEfectoResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class ItemEfecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;


    public ItemEfectoResponse response(){
        return new ItemEfectoResponse(id, descripcion);
    }

    public ItemEfecto(String descripcion) {
        this.descripcion = descripcion;
    }

    public ItemEfecto() {
    }

    public abstract void especialItem(Personaje me);

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}