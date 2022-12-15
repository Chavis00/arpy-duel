package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindDueloCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindPersonajeCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.DueloCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.DueloResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.PersonajeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/duelos")
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


    @DeleteMapping("/{dueloId}")
    public ResponseEntity<String> deleteDuelo(@PathVariable Long dueloId){
        dueloService.delete(findDuelo.get(dueloId).orElseThrow(()-> new RuntimeException("Duelo no encontrado")));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Duelo Eliminado");
    }






}
