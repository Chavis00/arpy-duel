package ar.edu.undef.fie.arpyduel.interfaces.responses;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import java.util.List;

public class DueloResponse {
    private Long id;
    private Integer turno;
    private String ganador;
    private String retador;

    public DueloResponse(Long id, Integer turno,
                         String ganador, String retador) {
        this.id = id;
        this.turno = turno;

    this.ganador = ganador;
        this.retador = retador;
    }

    public Long getId() {
        return id;
    }


    public Integer getTurno() {
        return turno;
    }


    public String getGanador() {
        return ganador;
    }

    public String getRetador() {
        return retador;
    }
}
