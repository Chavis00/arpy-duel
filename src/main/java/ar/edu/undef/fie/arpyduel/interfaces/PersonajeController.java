package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.*;
import ar.edu.undef.fie.arpyduel.application.command_services.DueloCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.requests.PersonajeRequest;
import ar.edu.undef.fie.arpyduel.interfaces.responses.PersonajeResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/personaje")
public class PersonajeController {
    private final FindPersonajeCommandQuery findPersonaje;
    private final PersonajeCommandService personajeService;
    private final FindClaseCommandQuery findClase;
    private final DueloCommandService dueloService;


    public PersonajeController(FindPersonajeCommandQuery findPersonaje, PersonajeCommandService service,
                               FindClaseCommandQuery findClase, DueloCommandService dueloService) {
        this.findPersonaje = findPersonaje;
        this.personajeService = service;
        this.findClase = findClase;
        this.dueloService = dueloService;
    }

    @GetMapping("/all")
    public List<PersonajeResponse> getArchivosAll(){
        return findPersonaje.getAll().
                stream().
                map(Personaje::response).
                collect(Collectors.toList());
    }
    @PostMapping("/create")
    public PersonajeResponse create(@RequestBody PersonajeRequest request, @RequestParam Long claseId
                                    ){
        Clase clase = findClase.get(claseId).orElseThrow(()->new RuntimeException("Clase No encontrada"));
        Arma arma = new Arma();
        Personaje personaje = new Personaje(request.getName(), clase);
        return personajeService.save(personaje).response();
    }
    @GetMapping("/{personajeId}")
    public List<PersonajeResponse> getMe(@PathVariable Long personajeId){
        return findPersonaje.get(personajeId).stream().map(Personaje::response).collect(Collectors.toList());
    }

    @PutMapping("/claim/set/arma/{meId}")
    public PersonajeResponse setearArmaClaim(@PathVariable Long meId){
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        me.equiparArmaClaim();
        personajeService.save(me);
        return me.response();
    }
    @PutMapping("/claim/set/armadura/{meId}")
    public PersonajeResponse setearArmaduraClaim(@PathVariable Long meId){
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        me.equiparArmaduraClaim();
        personajeService.save(me);
        return me.response();
    }
    @PutMapping("/claim/set/item/{meId}")
    public PersonajeResponse setearItemClaim(@PathVariable Long meId){
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        me.equiparItemClaim();
        personajeService.save(me);
        return me.response();
    }

    @PutMapping("/ataquebasico/{meId}")
    public PersonajeResponse ataqueBasico(@PathVariable Long meId){
        List<Personaje> personajes = new ArrayList<Personaje>();
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        if (me.getDead()){
            throw new RuntimeException("Perdiste...");
        }

        Duelo duelo = me.getDueloActualOp().orElseThrow(()->new RuntimeException("No estas en un Duelo"));
        if (duelo.turnoDe() != me){
            throw new RuntimeException("No es tu turno...");
        }
        if (me.getDueloActual() == null){
            new RuntimeException("El duelo termino");
        }
        Personaje oponente = me.getDueloActual().getRival(me);

        duelo = me.ataqueBasico(oponente);

        personajes.add(me);
        personajes.add(oponente);
        personajeService.saveAll(personajes);
        dueloService.save(duelo);

        return oponente.response();
    }



    @PutMapping("/ataquearma/{meId}")
    public PersonajeResponse ataqueArma(@PathVariable Long meId){
        List<Personaje> personajes = new ArrayList<Personaje>();
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        Duelo duelo = me.getDueloActualOp().orElseThrow(()->new RuntimeException("No estas en un Duelo"));
        if (duelo.turnoDe() != me){
            new RuntimeException("No es tu turno...");
        }
        if (me.getDueloActual() == null){
            new RuntimeException("El duelo termino");
        }
        Personaje oponente = me.getDueloActual().getRival(me);
        me.getArmaEnUsoOp().orElseThrow(()->new RuntimeException("No se puede efectuar ataque con arma porque no tenes un Arma equipada"));


        duelo = me.ataqueArma(oponente);

        personajes.add(me);
        personajes.add(oponente);
        personajeService.saveAll(personajes);
        dueloService.save(duelo);

        return oponente.response();
    }

    @PutMapping("/ataqueitem/{meId}")
    public PersonajeResponse ataqueBasico(@PathVariable Long meId, @PathVariable Long personajeId){
        List<Personaje> personajes = new ArrayList<Personaje>();
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        Duelo duelo = me.getDueloActualOp().orElseThrow(()->new RuntimeException("No estas en un Duelo"));
        if (duelo.turnoDe() != me){
            new RuntimeException("No es tu turno...");
        }
        if (me.getDueloActual() == null){
            new RuntimeException("El duelo termino");
        }
        Personaje oponente = me.getDueloActual().getRival(me);
        me.getItemOp().orElseThrow(()->new RuntimeException("No se puede efectuar ataque con arma porque no tenes un Item equipado"));

        duelo = me.usarItem();

        personajes.add(me);
        personajes.add(oponente);
        personajeService.saveAll(personajes);
        dueloService.save(duelo);

        return oponente.response();
    }

    @DeleteMapping("/delete/{idPersonaje}")
    public void deletePersonaje(@PathVariable Long idPersonaje){
        personajeService.delete(idPersonaje);
    }


}
