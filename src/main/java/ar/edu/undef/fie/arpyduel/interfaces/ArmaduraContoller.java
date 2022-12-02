package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindPersonajeCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.findArmaduraCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.ArmaduraCommadnService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaduraResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/armadura")
public class ArmaduraContoller {
    private final findArmaduraCommandQuery findArmadura;
    private final ArmaduraCommadnService armaduraService;
    private final FindPersonajeCommandQuery findPersonaje;
    private final PersonajeCommandService personajeService;


    public ArmaduraContoller(findArmaduraCommandQuery findArmadura, ArmaduraCommadnService armaduraService, FindPersonajeCommandQuery findPersonaje, PersonajeCommandService personajeService) {
        this.findArmadura = findArmadura;
        this.armaduraService = armaduraService;
        this.findPersonaje = findPersonaje;
        this.personajeService = personajeService;
    }

    @PutMapping("/claim/generar/{meId}")
    public ArmaduraResponse generarClaim(@PathVariable Long meId){
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        if (me.claimsVacios())
            return null;
        Armadura armadura = new Armadura();
        armadura.construirArmadura(me.getNivel());
        armaduraService.save(armadura);
        me.setClaims(me.getClaims()-1);
        me.claimArmadura(armadura);
        personajeService.save(me);

        return armadura.response();
    }
    @GetMapping("/equipado/{meId}")
    public ArmaduraResponse getItemEquipado(@PathVariable Long meId){
        return findPersonaje.
                get(meId).
                orElseThrow(() -> new RuntimeException("Personaje No encontrado")).
                getArmaduraEnUsoOp().
                map(Armadura::response).
                orElseThrow(()->new RuntimeException("No existe una Armadura equipada"));
    }
}
