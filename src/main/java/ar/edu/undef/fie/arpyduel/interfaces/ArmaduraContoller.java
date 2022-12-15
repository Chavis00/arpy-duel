package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindPersonajeCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindArmaduraCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.ArmaduraCommadnService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaduraResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/armaduras")
public class ArmaduraContoller {
    private final FindArmaduraCommandQuery findArmadura;
    private final ArmaduraCommadnService armaduraService;
    private final FindPersonajeCommandQuery findPersonaje;
    private final PersonajeCommandService personajeService;


    public ArmaduraContoller(FindArmaduraCommandQuery findArmadura, ArmaduraCommadnService armaduraService, FindPersonajeCommandQuery findPersonaje, PersonajeCommandService personajeService) {
        this.findArmadura = findArmadura;
        this.armaduraService = armaduraService;
        this.findPersonaje = findPersonaje;
        this.personajeService = personajeService;
    }

    @PostMapping("/claim/personajes/{meId}")
    public ArmaduraResponse generarClaim(@PathVariable  Long meId){
        return armaduraService.
                generarClaim(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                )
                ).
                response();
    }

}
