package ar.edu.undef.fie.arpyduel.domain.items;

import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.AplicarConfusion;
import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.ItemEfecto;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ItemEfecto efecto;
    private Boolean usado;


    public Item(ItemEfecto efecto) {
        this.efecto = efecto;
        this.usado = false;
    }

    public Item() {

    }
    public ItemResponse response(){
        return new ItemResponse(id, usado, getEfectoOp().map(ItemEfecto::response).orElse(null));
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void especialItem(Personaje personaje) {
        efecto.especialItem(personaje);
    }

    public ItemEfecto getEfecto() {
        return efecto;
    }

    public void setEfecto(ItemEfecto efecto) {
        this.efecto = efecto;
    }

    public Boolean getUsado() {
        return usado;
    }

    public void setUsado(Boolean usado) {
        this.usado = usado;
    }

    public Optional<ItemEfecto> getEfectoOp() {
        return Optional.ofNullable(efecto);
    }


}
