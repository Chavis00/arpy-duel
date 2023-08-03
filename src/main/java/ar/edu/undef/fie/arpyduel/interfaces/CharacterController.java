package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.*;
import ar.edu.undef.fie.arpyduel.application.command_services.CharacterCommandService;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.interfaces.requests.CharacterRequest;
import ar.edu.undef.fie.arpyduel.interfaces.responses.*;
import ar.edu.undef.fie.arpyduel.interfaces.responses.interactions.AvailableDuelInteractionsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/characters")
public class CharacterController {
    private final FindCharacterCommandQuery characterQuery;
    private final CharacterCommandService characterService;

    public CharacterController(FindCharacterCommandQuery characterQuery, CharacterCommandService characterService) {
        this.characterQuery = characterQuery;
        this.characterService = characterService;
    }
    @GetMapping("")
    public ResponseEntity<List<CharacterResponse>> getAllCharacters(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterQuery.getAllByOnwerUsername(username).stream().map(Character::response).toList();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}/available-interactions")
    public ResponseEntity<AvailableDuelInteractionsResponse> getAvailableDuelInteractions(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterQuery.getByAvailableDuelInteractions(id, username);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CharacterResponse> getCharacterById(@PathVariable Long id){
        var response = characterQuery.getById(id).response();
        return ResponseEntity.ok(response);
    }
    @PostMapping("")
    public ResponseEntity<CharacterResponse> createCharacter(@RequestBody CharacterRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterService.create(request, username).response();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CharacterResponse> updateCharacterName(@PathVariable Long id, @RequestParam String name){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterService.updateName(id, name, username).response();
        return ResponseEntity.ok(response);
    }
    @PostMapping("{id}/attack/basic")
    public ResponseEntity<CharacterResponse> characterBasicAttack(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterService.basicAttack(id, username).response();
        return ResponseEntity.ok(response);
    }
    @PostMapping("{id}/attack/weapon")
    public ResponseEntity<CharacterResponse> characterWeaponAttack(@PathVariable  Long id, @RequestParam Boolean enchantment){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterService.weaponAttack(id, username, enchantment).response();
        return ResponseEntity.ok(response);
    }

    @PostMapping("{id}/attack/item")
    public ResponseEntity<CharacterResponse> characterItemUse(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterService.useItem(id, username).response();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCharater(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        characterService.delete(id, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
