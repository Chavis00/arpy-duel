package ar.edu.undef.fie.arpyduel.domain.jugador;


import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;

import java.util.List;

public class Jugador {
    private String username;
    private List<Personaje> personajes;

    // GETTERS & SETTERS
    public String getUsername() {
        return username;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }


}
