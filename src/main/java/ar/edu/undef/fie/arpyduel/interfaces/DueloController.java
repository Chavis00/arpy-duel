package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindDueloCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindPersonajeCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.DueloCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.DueloResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.PersonajeResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/duelo")
public class DueloController {
    private final DueloCommandService dueloService;
    private final FindDueloCommandQuery findDuelo;
    private final FindPersonajeCommandQuery findPersonaje;
    private final PersonajeCommandService personajeService;


    public DueloController(DueloCommandService serviceDuelo, FindDueloCommandQuery findDuelo,
                           FindPersonajeCommandQuery findPersonaje, PersonajeCommandService personajeService) {
        this.dueloService = serviceDuelo;
        this.findDuelo = findDuelo;
        this.findPersonaje = findPersonaje;
        this.personajeService = personajeService;

    }

    @GetMapping("/pendientes/{meId}")
    public List<DueloResponse> getAllDuelos(@PathVariable Long meId){
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        List<Duelo> duelos = me.getDuelosPendientes();
        return duelos.stream().map(Duelo::response).collect(Collectors.toList());

    }
    @PutMapping("/aceptar/{meId}/{personajeId}/{dueloId}")
    public PersonajeResponse aceptarDuelo(@PathVariable Long meId,
                                          @PathVariable Long personajeId,
                                          @PathVariable Long dueloId){
        List<Personaje> personajes = new ArrayList<Personaje>();

        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        Personaje oponente = findPersonaje.get(personajeId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        Duelo duelo = findDuelo.get(dueloId).orElseThrow(()->new RuntimeException("Duelo No encontrado"));
        me.aceptarDuelo(oponente, duelo);
        personajes.add(me);
        personajes.add(oponente);
        personajeService.saveAll(personajes);

        return me.response();
    }

    @PutMapping("/rendirse/{meId}")
    public PersonajeResponse rendirseDuelo(@PathVariable Long meId){
        List<Personaje> personajes = new ArrayList<Personaje>();
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        Personaje oponente = me.getDueloActual().getRival(me);

        Duelo duelo = me.rendirse(me.getDueloActual());
        oponente.setDueloActual(null);
        dueloService.delete(duelo);

        personajes.add(me);
        personajes.add(oponente);
        personajeService.saveAll(personajes);

        return me.response();
    }

    @PutMapping("/retar/{meId}/{personajeId}")
    public PersonajeResponse retarDuelo(@PathVariable Long meId, @PathVariable Long personajeId){
        List<Personaje> personajes = new ArrayList<Personaje>();
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        Personaje oponente = findPersonaje.get(personajeId).orElseThrow(()->new RuntimeException("Oponente No encontrado"));

        Duelo duelo = me.retarDuelo(oponente);
        dueloService.save(duelo);

        personajes.add(me);
        personajes.add(oponente);
        personajeService.saveAll(personajes);

        return me.response();
    }
    @DeleteMapping("/{dueloId}")
    public void deleteDuelo(@PathVariable Long dueloId){
        for(Personaje personaje: findDuelo.get(dueloId).
                orElseThrow(()->new RuntimeException("Duelo no encontrado")).
                getPersonajes()){
            personaje.setDueloActual(null);
            personajeService.save(personaje);
        }
        dueloService.deleteById(dueloId);
    }

    @PutMapping("/terminar/{meId}/{dueloId}")
    public PersonajeResponse terminarDuelo(@PathVariable Long meId, @PathVariable Long dueloId){
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        Duelo duelo = findDuelo.get(dueloId).orElseThrow(()->new RuntimeException("Duelo No encontrado"));
        duelo = me.terminarDuelo(duelo);
        personajeService.save(me);

        if (duelo.getPersonajes().isEmpty()){
            dueloService.delete(duelo);
        }

        return me.response();
    }


}
