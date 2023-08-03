package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindWeaponCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindWeaponTypeCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.CharacterCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.WeaponCommandService;
import ar.edu.undef.fie.arpyduel.domain.weapon.Weapon;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoEquippedWeaponException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.WeaponResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.WeaponTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/weapons")
public class WeaponController {
    private final FindWeaponTypeCommandQuery weaponTypeQuery;
    private final WeaponCommandService weaponService;
    private final FindWeaponCommandQuery weaponQuery;
    private final FindCharacterCommandQuery characterQuery;
    private final CharacterCommandService characterService;

    public WeaponController(FindWeaponTypeCommandQuery weaponTypeQuery, WeaponCommandService weaponService, FindWeaponCommandQuery weaponQuery, FindCharacterCommandQuery characterQuery, CharacterCommandService characterService) {
        this.weaponTypeQuery = weaponTypeQuery;
        this.weaponService = weaponService;
        this.weaponQuery = weaponQuery;
        this.characterQuery = characterQuery;
        this.characterService = characterService;
    }


    @GetMapping("/types")
    public List<WeaponTypeResponse> getAllWeaponTypes(){
        return weaponTypeQuery.getAll().
                stream().
                map(WeaponType::response).
                collect(Collectors.toList());
    }
    @PatchMapping("/characters/{characterId}/claim")
    public  ResponseEntity<Object> setClaimWeapon(@PathVariable Long characterId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        weaponService.equipClaimedWeapon(characterId, username).response();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/characters/{characterId}/claim/types/{weaponTypeId}")
    public ResponseEntity<WeaponResponse> generateClaim(@PathVariable Long characterId, @PathVariable Long weaponTypeId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = weaponService.generateClaim(characterQuery.getCharacterAuth(characterId, username), weaponTypeQuery.get(weaponTypeId)).response();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<WeaponResponse> getEquippedWeapon(@PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterQuery.getCharacterAuth(characterId, username).getEquippedWeaponOp().map(Weapon::response).orElseThrow(NoEquippedWeaponException::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeaponResponse> getWeapon(@PathVariable Long id){
        var response = weaponQuery.getById(id).response();
        return ResponseEntity.ok(response);
    }

}
