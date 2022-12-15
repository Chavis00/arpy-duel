package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindItemCommandQueryt;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindItemEfectoCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindPersonajeCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.ItemCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.ItemEfectoCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.PersonajeCommandService;
import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.domain.items.Item;
import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.ItemEfecto;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemCommandService itemService;
    private final FindPersonajeCommandQuery findPersonaje;

    public ItemController(ItemCommandService itemService, FindPersonajeCommandQuery findPersonaje) {
        this.itemService = itemService;
        this.findPersonaje = findPersonaje;
    }

    @PostMapping("/claim/personajes/{meId}")
    public ItemResponse generarClaim(@PathVariable Long meId) {
        return itemService.
                create(
                        findPersonaje.
                                get(meId).
                                orElseThrow(
                                        ()->new RuntimeException("Personaje No encontrado")
                                )
                ).
                response();
    }


}
