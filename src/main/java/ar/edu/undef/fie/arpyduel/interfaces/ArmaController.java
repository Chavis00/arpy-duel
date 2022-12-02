package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindArmaTipoCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindEncantamientoCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindPersonajeCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.ArmaCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaTipoResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/arma")
public class ArmaController {
    private final FindArmaTipoCommandQuery findArmaTipo;
    private final FindPersonajeCommandQuery findPersonaje;
    private final PersonajeCommandService personajeService;
    private final ArmaCommandService armaService;
    private final FindEncantamientoCommandQuery findEncantamiento;


    public ArmaController(FindArmaTipoCommandQuery findArma, FindPersonajeCommandQuery findPersonaje,
                          PersonajeCommandService personajeService, ArmaCommandService armaService,
                          FindEncantamientoCommandQuery findEncantamiento) {
        this.findArmaTipo = findArma;
        this.findPersonaje = findPersonaje;
        this.personajeService = personajeService;
        this.armaService = armaService;
        this.findEncantamiento = findEncantamiento;
    }
    @GetMapping("/tipo/all")
    public List<ArmaTipoResponse> getAllTiposArma(){
        return findArmaTipo.getAll().
                stream().
                map(ArmaTipo::response).
                collect(Collectors.toList());
    }

    @PutMapping("/claim/generar/{meId}/{armaTipoId}")
    public ArmaResponse generarClaim(@PathVariable Long meId, @PathVariable Long armaTipoId){
        Personaje me = findPersonaje.get(meId).orElseThrow(()->new RuntimeException("Personaje No encontrado"));
        if (me.claimsVacios())
            return null;
        ArmaTipo tipo = findArmaTipo.get(armaTipoId).orElseThrow(()->new RuntimeException("Tipo de Arma No encontrado"));
        Arma arma = new Arma(tipo);
        Random rn = new Random();

        arma.construirArma(me.getNivel());

        List<Encantamiento> encantamientosDisponibles = findEncantamiento.getAll();
        Encantamiento encantamientoRandom = encantamientosDisponibles.get(rn.nextInt((int) findEncantamiento.countEncantamientos()));
        arma.setEncantamiento(encantamientoRandom);

        me.setClaims(me.getClaims()-1);
        me.claimArma(arma);
        armaService.save(arma);
        personajeService.save(me);

        return arma.response();
    }

    @GetMapping("/equipado/{meId}")
    public ArmaResponse getItemEquipado(@PathVariable Long meId){
        return findPersonaje.
                get(meId).
                orElseThrow(() -> new RuntimeException("Personaje No encontrado")).
                getArmaEnUsoOp().
                map(Arma::response).
                orElseThrow(()->new RuntimeException("No existe un Arma equipada"));
    }

}
