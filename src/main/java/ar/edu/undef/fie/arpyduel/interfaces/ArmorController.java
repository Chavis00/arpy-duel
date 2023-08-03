package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.ArmorCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.CharacterArmorCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.CharacterCommandService;
import ar.edu.undef.fie.arpyduel.domain.armor.Armor;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoEquippedArmorException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/armors")
public class ArmorController {
    private final ArmorCommandService armorService;
    private final FindCharacterCommandQuery characterQuery;
    private final CharacterCommandService characterService;
    private final CharacterArmorCommandService characterArmorService;

    public ArmorController(ArmorCommandService armorService, FindCharacterCommandQuery characterQuery, CharacterCommandService characterService, CharacterArmorCommandService characterArmorService) {
        this.armorService = armorService;
        this.characterQuery = characterQuery;
        this.characterService = characterService;
        this.characterArmorService = characterArmorService;
    }
    @PostMapping("/characters/{characterId}/claim")
    public ResponseEntity<ArmorResponse> generateClaim(@PathVariable  Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = armorService.generateClaim(characterId, username).response();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/characters/{characterId}/claim")
    public ResponseEntity<Object> setClaimedArmor(@PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        characterArmorService.equipClaimedArmor(characterId, username);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<ArmorResponse> getEquippedArmor(@PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response =  characterQuery.getCharacterAuth(characterId, username).getEquippedArmorOp().map(Armor::response).orElseThrow(NoEquippedArmorException::new);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ArmorResponse> getArmor(@PathVariable Long id){
        var response = armorService.get(id).response();
        return ResponseEntity.ok(response);
    }

}
