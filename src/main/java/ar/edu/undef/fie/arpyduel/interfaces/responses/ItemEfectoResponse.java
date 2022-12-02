package ar.edu.undef.fie.arpyduel.interfaces.responses;

public class ItemEfectoResponse {
    private Long id;
    private String descripcion;

    public ItemEfectoResponse(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
