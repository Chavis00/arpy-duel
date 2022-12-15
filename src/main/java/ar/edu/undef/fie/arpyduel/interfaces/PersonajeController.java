package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.*;
import ar.edu.undef.fie.arpyduel.application.command_services.DueloCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.domain.items.Item;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.requests.PersonajeRequest;
import ar.edu.undef.fie.arpyduel.interfaces.responses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/personajes")
public class PersonajeController {
    private final FindPersonajeCommandQuery findPersonaje;
    private final PersonajeCommandService personajeService;
    private final FindClaseCommandQuery findClase;
    private final FindDueloCommandQuery findDuelo;
    private final FindArmaCommandQuery findArma;
    private final FindArmaduraCommandQuery findArmadura;
    private final FindItemCommandQueryt findItem;
    private final DueloCommandService dueloService;


    public PersonajeController(FindPersonajeCommandQuery findPersonaje, PersonajeCommandService service,
                               FindClaseCommandQuery findClase, FindDueloCommandQuery findDuelo,
                               FindArmaCommandQuery findArma, FindArmaduraCommandQuery findArmadura,
                               FindItemCommandQueryt findItem, DueloCommandService dueloService) {
        this.findPersonaje = findPersonaje;
        this.personajeService = service;
        this.findClase = findClase;
        this.findDuelo = findDuelo;
        this.findArma = findArma;
        this.findArmadura = findArmadura;
        this.findItem = findItem;
        this.dueloService = dueloService;
    }

    @GetMapping("/")
    public List<PersonajeResponse> getArchivosAll(){
        return findPersonaje.getAll().
                stream().
                map(Personaje::response).
                collect(Collectors.toList());
    }
    @PostMapping("/clases/{claseId}")
    public PersonajeResponse create(@RequestBody PersonajeRequest request,
                                    @PathVariable Long claseId){
        return personajeService.create(request,
                findClase.
                        get(claseId).
                        orElseThrow(
                                ()->new RuntimeException("Clase No encontrada")
                        )
        ).
                response();
    }
    @PatchMapping("/{personajeId}")
    public PersonajeResponse editarNombrePersonaje(@PathVariable Long personajeId,
                                                   @RequestParam String nombre){
        return personajeService.
                cambiarNombre(findPersonaje.get(personajeId).
                                orElseThrow(()->new RuntimeException("Personaje no encontrado")),
                        nombre).
                response();
    }
    @GetMapping("/{personajeId}")
    public List<PersonajeResponse> getMe(@PathVariable Long personajeId){
        return findPersonaje.
                get(personajeId).
                stream().
                map(Personaje::response).
                collect(
                        Collectors.
                                toList()
                );
    }

    @PatchMapping("{meId}/claim/arma")
    public PersonajeResponse setearArmaClaim(@PathVariable Long meId,
                                             @RequestParam Long armaEnUsoId){
        return personajeService.
                equiparArmaClaim(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                ),
                        findArma.
                                get(armaEnUsoId).
                                orElseThrow(
                                        ()->new RuntimeException("Arma no encontrada")
                                )
                ).
                response();
    }
    @PatchMapping("{meId}/claim/armadura")
    public PersonajeResponse setearArmaduraClaim(@PathVariable Long meId,
                                                 @RequestParam Long armaduraEnUsoId){
        return personajeService.
                equiparArmaduraClaim(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                ),
                        findArmadura.
                                get(armaduraEnUsoId).
                                orElseThrow(
                                        ()->new RuntimeException("Armadura no encontrada")
                                )
                ).
                response();
    }
    @PatchMapping("{meId}/claim/item")
    public PersonajeResponse setearItemClaim(@PathVariable Long meId,
                                             @RequestParam Long itemEnUsoId){
        return personajeService.
                equiparItemClaim(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                ),
                        findItem.
                                get(itemEnUsoId).
                                orElseThrow(
                                        ()->new RuntimeException("Item no encontrado")
                                )
                ).
                response();
    }

    @PostMapping("{meId}/ataque/basico")
    public PersonajeResponse ataqueBasico(@PathVariable Long meId){
        return personajeService.
                ataqueBasico(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                )
                ).
                response();
    }



    @PostMapping("{meId}/ataques/arma")
    public PersonajeResponse ataqueArma(@PathVariable  Long meId){
        return personajeService.
                ataqueArma(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                )
                ).
                response();
    }

    @PostMapping("/ataque/item/")
    public PersonajeResponse usarItem(@PathVariable Long meId){

        return personajeService.
                usarItem(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                )
                ).
                response();
    }

    @DeleteMapping("/{idPersonaje}")
    public ResponseEntity<String> deletePersonaje(@PathVariable Long idPersonaje){
        personajeService.
                delete(idPersonaje);
        return ResponseEntity.
                status(HttpStatus.NO_CONTENT).
                body("Personaje Borrado");
    }

    @PatchMapping("{meId}/duelos/{dueloId}")
    public PersonajeResponse aceptarDuelo(@PathVariable Long meId,
                                          @RequestParam Long oponenteId,
                                          @PathVariable Long dueloId){
        return personajeService.
                aceptarDuelo(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                ),
                        findPersonaje.
                                get(oponenteId).
                                orElseThrow(
                                        ()->new RuntimeException("Oponente No encontrado")
                                ),
                        findDuelo.
                                get(dueloId).
                                orElseThrow(
                                        ()->new RuntimeException("Duelo No encontrado")
                                )
                ).
                response();
    }

    @PostMapping("{meId}/duelos/oponente/{oponenteId}")
    public DueloResponse retarDuelo(@PathVariable Long meId,
                                    @PathVariable Long oponenteId){
        return personajeService.
                retarDuelo(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                ),
                        findPersonaje.
                                get(oponenteId).
                                orElseThrow(
                                        ()->new RuntimeException("Oponente No encontrado")
                                )
                ).
                response();
    }

    @PatchMapping("/{meId}/duelos/actual")
    public PersonajeResponse rendirseDuelo(@PathVariable Long meId,
                                           @RequestParam Long ganadorId){
        return personajeService.
                rendirse(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                ),
                        findPersonaje.
                                get(ganadorId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje Ganador No encontrado")
                                )
                ).
                response();
    }

    @DeleteMapping("{meId}/duelos/actual")
    public PersonajeResponse terminarDuelo(@PathVariable Long meId){
        return personajeService.
                terminarDueloActual(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                )
                ).
                response();
    }
    @GetMapping("{meId}/arma")
    public ArmaResponse getArmaEquipada(@PathVariable Long meId){
        return findPersonaje.
                get(meId).
                orElseThrow(
                        () -> new RuntimeException("Personaje No encontrado")
                ).
                getArmaEnUsoOp().
                map(Arma::response).
                orElseThrow(
                        ()->new RuntimeException("No existe un Arma equipada")
                );
    }
    @GetMapping("{meId}/item")
    public ItemResponse getItemEquipado(@PathVariable Long meId){
        return findPersonaje.
                get(meId).
                orElseThrow(
                        () -> new RuntimeException("Personaje No encontrado")
                ).
                getItemOp().
                map(Item::response).
                orElseThrow(
                        ()->new RuntimeException("No existe un Item equipado")
                );
    }
    @GetMapping("{meId}/armadura")
    public ArmaduraResponse getArmaduraEquipada(@PathVariable Long meId){
        return findPersonaje.
                get(meId).
                orElseThrow(
                        () -> new RuntimeException("Personaje No encontrado")
                ).
                getArmaduraEnUsoOp().
                map(Armadura::response).
                orElseThrow(
                        ()->new RuntimeException("No existe una Armadura equipada")
                );
    }
    @GetMapping("{meId}/duelos")
    public List<DueloResponse> getAllDuelos(@PathVariable Long meId){
        return dueloService.
                getDuelosPendientes(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                )
                ).
                stream().
                map(Duelo::response).
                collect(
                        Collectors.toList()
                );
    }
}
