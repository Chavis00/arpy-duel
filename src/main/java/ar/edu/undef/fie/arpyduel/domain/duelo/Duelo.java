package ar.edu.undef.fie.arpyduel.domain.duelo;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.DueloResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Duelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double tiempoDeDuelo;
    private Integer turno;
    @OneToMany
    private List<Personaje> personajes;
    @OneToOne
    private Personaje ganador;
    private String retador;

    public DueloResponse response(){
        return new DueloResponse(id, turno,
                getGanadorOp().map(Personaje::getName).orElse(null), retador);
    }
    public Duelo(List<Personaje> personajesDuelo){
        tiempoDeDuelo = 0.0;
        turno = 0;
        personajes = personajesDuelo;
    }

    public Duelo() {

    }

    public void pasarTurno(){
        turno += 1;
    }
    public Personaje turnoDe(){
        int index = turno % 2;
        return personajes.get(index);
    }

    public Double getTiempoDeDuelo() {
        return tiempoDeDuelo;
    }
    public void addPersonaje(Personaje personaje){
        personajes.add(personaje);
    }

    public void setTiempoDeDuelo(Double tiempoDeDuelo) {
        this.tiempoDeDuelo = tiempoDeDuelo;
    }

    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }


    public Personaje getRival(Personaje me) {

        Personaje rival = personajes.stream()
                .filter(personaje -> !me.equals(personaje))
                .collect(Collectors.toList()).get(0);;

        return rival;
    }


    public Personaje getGanador() {
        return ganador;
    }

    public void setGanador(Personaje ganador) {
        this.ganador = ganador;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public String getRetador() {
        return retador;
    }

    public void setRetador(String retador) {
        this.retador = retador;
    }
    //Optional
    public Optional<Personaje> getGanadorOp() {
        return Optional.ofNullable(ganador);
    }

}
