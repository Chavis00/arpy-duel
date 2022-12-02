package ar.edu.undef.fie.arpyduel.interfaces.responses;

public class ArmaTipoResponse {
    private Long id;
    private String tipo;

    public ArmaTipoResponse(Long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
}
