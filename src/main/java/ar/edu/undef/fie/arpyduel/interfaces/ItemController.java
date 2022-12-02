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
@RequestMapping("/api/item")
public class ItemController {
    private final FindItemEfectoCommandQuery findItemEfecto;
    private final FindItemCommandQueryt findItem;
    private final ItemEfectoCommandService itemEfectoService;
    private final ItemCommandService itemService;
    private final FindPersonajeCommandQuery findPersonaje;
    private final PersonajeCommandService personajeService;

    public ItemController(FindItemEfectoCommandQuery findItem, FindItemCommandQueryt findItem1, ItemEfectoCommandService itemEfectoService, ItemCommandService itemService, FindPersonajeCommandQuery findPersonaje, PersonajeCommandService personajeService) {
        this.findItemEfecto = findItem;
        this.findItem = findItem1;
        this.itemEfectoService = itemEfectoService;
        this.itemService = itemService;
        this.findPersonaje = findPersonaje;
        this.personajeService = personajeService;
    }

    @PutMapping("/claim/generar/{meId}")
    public ItemResponse generarClaim(@PathVariable Long meId) {
        Personaje me = findPersonaje.get(meId).orElseThrow(() -> new RuntimeException("Personaje No encontrado"));
        if (me.claimsVacios())
            return null;

        Random rn = new Random();
        List<ItemEfecto> efectosExistentes = findItemEfecto.getAll();
        ItemEfecto itemEfectoRandom = efectosExistentes.get(rn.nextInt((int) findItemEfecto.count()));
        Item item = new Item(itemEfectoRandom);
        itemService.save(item);
        me.setItemClaim(item);
        me.setClaims(me.getClaims()-1);
        personajeService.save(me);

        return item.response();
    }

    @GetMapping("/equipado/{meId}")
    public ItemResponse getItemEquipado(@PathVariable Long meId){
        return findPersonaje.
                get(meId).
                orElseThrow(() -> new RuntimeException("Personaje No encontrado")).
                getItemOp().
                map(Item::response).
                orElseThrow(()->new RuntimeException("No existe un Item equipad"));
    }
}
