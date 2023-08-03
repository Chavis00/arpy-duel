package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.CharacterCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.ItemCommandService;
import ar.edu.undef.fie.arpyduel.domain.item.Item;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoEquippedItemException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemCommandService itemService;
    private final FindCharacterCommandQuery characterQuery;
    private final CharacterCommandService characterService;

    public ItemController(ItemCommandService itemService, FindCharacterCommandQuery characterQuery, CharacterCommandService characterService) {
        this.itemService = itemService;
        this.characterQuery = characterQuery;
        this.characterService = characterService;
    }

    @PostMapping("/characters/{characterId}/claim")
    public ResponseEntity<ItemResponse> generateClaim(@PathVariable Long characterId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = itemService.generateClaim(characterId, username).response();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/characters/{characterId}/claim")
    public ResponseEntity<Object> setClaimedItem(@PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        itemService.equipClaimedItem(characterId, username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<ItemResponse> getEquippedItem(@PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response =  characterQuery.getCharacterAuth(characterId, username).getItemOp().map(Item::response).orElseThrow(NoEquippedItemException::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long id){
        var response = itemService.get(id).response();
        return ResponseEntity.ok(response);
    }

}
