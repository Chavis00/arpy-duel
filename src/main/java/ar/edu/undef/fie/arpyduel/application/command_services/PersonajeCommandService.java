package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.domain.items.Item;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.infrastructure.DueloRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.PersonajeRepository;
import ar.edu.undef.fie.arpyduel.interfaces.requests.PersonajeRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonajeCommandService {
    private final PersonajeRepository repository;
    private final DueloRepository dueloRepository;
    private final DueloCommandService dueloCommandService;

    public PersonajeCommandService(PersonajeRepository repository, DueloRepository dueloRepository, DueloCommandService dueloCommandService) {
        this.repository = repository;
        this.dueloRepository = dueloRepository;
        this.dueloCommandService = dueloCommandService;
    }

    public Personaje save(Personaje personaje){
        return repository.save(personaje);
    }
    public List<Personaje> saveAll(List<Personaje> personajes){
        return repository.saveAll(personajes);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }

    public Personaje cambiarNombre(Personaje personaje, String nombre) {
        personaje.setName(nombre);
        repository.save(personaje);
        return personaje;
    }

    public Personaje create(PersonajeRequest request, Clase clase) {
        return repository.
                save(
                        new Personaje(
                                request.getName(),
                                clase)
                );
    }

    public Personaje equiparArmaClaim(Personaje personaje, Arma arma) {
        if (personaje.getArmaClaim() != arma)
            new RuntimeException("Ese Arma no te pertenece");
        personaje.equiparArmaClaim(arma);
        return repository.save(personaje);
    }

    public Personaje equiparArmaduraClaim(Personaje personaje, Armadura armadura) {
        if (personaje.getArmaduraClaim()!=armadura)
            new RuntimeException("Esa Armadura no te pertenece");
        personaje.equiparArmaduraClaim(armadura);
        return repository.save(personaje);
    }

    public Personaje equiparItemClaim(Personaje personaje, Item item) {
        personaje.equiparItemClaim(item);
        return save(personaje);
    }

    public Personaje ataqueBasico(Personaje personaje) {
        List<Personaje> personajes = new ArrayList<Personaje>();

        if (personaje.getDead()){
            throw new RuntimeException("Perdiste...");
        }
        Duelo duelo = personaje.getDueloActualOp().orElseThrow(()->new RuntimeException("No estas en un Duelo"));
        if (duelo.turnoDe() != personaje){
            throw new RuntimeException("No es tu turno...");
        }
        if (personaje.getDueloActual() == null){
            new RuntimeException("El duelo termino");
        }
        Personaje oponente = personaje.getDueloActual().getRival(personaje);

        duelo = personaje.ataqueBasico(oponente);

        personajes.add(personaje);
        personajes.add(oponente);
        repository.saveAll(personajes);
        dueloRepository.save(duelo);
        return oponente;
    }

    public Personaje ataqueArma(Personaje personaje) {
        List<Personaje> personajes = new ArrayList<Personaje>();
        Duelo duelo = personaje.getDueloActualOp().orElseThrow(()->new RuntimeException("No estas en un Duelo"));
        if (duelo.turnoDe() != personaje){
            new RuntimeException("No es tu turno...");
        }
        if (personaje.getDueloActual() == null){
            new RuntimeException("El duelo termino");
        }
        Personaje oponente = personaje.getDueloActual().getRival(personaje);
        personaje.getArmaEnUsoOp().orElseThrow(()->new RuntimeException("No se puede efectuar ataque con arma porque no tenes un Arma equipada"));


        duelo = personaje.ataqueArma(oponente);

        personajes.add(personaje);
        personajes.add(oponente);
        repository.saveAll(personajes);
        dueloRepository.save(duelo);
        return oponente;
    }

    public Personaje usarItem(Personaje personaje) {
        List<Personaje> personajes = new ArrayList<Personaje>();
        Duelo duelo = personaje.getDueloActualOp().orElseThrow(()->new RuntimeException("No estas en un Duelo"));
        if (duelo.turnoDe() != personaje){
            new RuntimeException("No es tu turno...");
        }
        if (personaje.getDueloActual() == null){
            new RuntimeException("El duelo termino");
        }
        Personaje oponente = personaje.getDueloActual().getRival(personaje);
        personaje.getItemOp().orElseThrow(()->new RuntimeException("No se puede efectuar ataque con arma porque no tenes un Item equipado"));

        duelo = personaje.usarItem();

        personajes.add(personaje);
        personajes.add(oponente);
        repository.saveAll(personajes);
        dueloRepository.save(duelo);

        return oponente;
    }

    public Personaje aceptarDuelo(Personaje personaje, Personaje oponente, Duelo duelo) {
        if (personaje.getDuelosPendientes().contains(duelo)==false){
            new RuntimeException("El duelo no se encuentra en la lista de duelos pendientes");
        }
        List<Personaje> personajes = new ArrayList<Personaje>();
        personaje.aceptarDuelo(oponente, duelo);
        personajes.add(personaje);
        personajes.add(oponente);
        repository.saveAll(personajes);
        return personaje;
    }

    public Duelo retarDuelo(Personaje personaje, Personaje oponente) {
        List<Personaje> personajes = new ArrayList<Personaje>();

        Duelo duelo = personaje.retarDuelo(oponente);
        dueloRepository.save(duelo);

        personajes.add(personaje);
        personajes.add(oponente);
        repository.saveAll(personajes);
        return duelo;
    }

    public Personaje rendirse(Personaje personaje, Personaje ganador) {
        List<Personaje> personajes = new ArrayList<Personaje>();
        if (ganador != personaje.getDueloActual().getRival(personaje))
            new RuntimeException("No estas en duelo con ese personaje");

        Duelo duelo = personaje.rendirse(personaje.getDueloActual());
        ganador.setDueloActual(null);
        dueloRepository.delete(duelo);

        personajes.add(personaje);
        personajes.add(ganador);
        repository.saveAll(personajes);

        return personaje;
    }

    public Personaje terminarDueloActual(Personaje personaje) {
        if (personaje.getDueloActual().equals(null))
            new RuntimeException("No te encuentras en un duelo");
        if(personaje.getDueloActual().getGanador().equals(null))
            new RuntimeException("No puedes Borrar un duelo que no termino");
        Duelo duelo = personaje.terminarDuelo(personaje.getDueloActual());
        repository.save(personaje);

        if (duelo.getPersonajes().isEmpty()){
            dueloRepository.delete(duelo);
        }

        return personaje;
    }
}
