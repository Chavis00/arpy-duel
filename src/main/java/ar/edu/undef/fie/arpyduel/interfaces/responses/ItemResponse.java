package ar.edu.undef.fie.arpyduel.interfaces.responses;

import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.ItemEfecto;

public class ItemResponse {
    private Long id;
    private Boolean usado;
    private ItemEfectoResponse efecto;


    public ItemResponse(Long id, Boolean usado, ItemEfectoResponse efecto) {
        this.id = id;
        this.usado = usado;
        this.efecto = efecto;
    }

    public Long getId() {
        return id;
    }


    public Boolean getUsado() {
        return usado;
    }

    public ItemEfectoResponse getEfecto() {
        return efecto;
    }
}
