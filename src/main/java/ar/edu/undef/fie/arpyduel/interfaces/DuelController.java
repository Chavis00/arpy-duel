package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindDuelCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_services.CharacterCommandService;
import ar.edu.undef.fie.arpyduel.application.command_services.DuelCommandService;
import ar.edu.undef.fie.arpyduel.domain.duel.Duel;
import ar.edu.undef.fie.arpyduel.interfaces.requests.DuelRequest;
import ar.edu.undef.fie.arpyduel.interfaces.responses.CharacterResponse;
import ar.edu.undef.fie.arpyduel.interfaces.responses.DuelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/duels")
public class DuelController {
    private final DuelCommandService duelService;
    private final FindDuelCommandQuery duelQuery;
    private final CharacterCommandService characterService;
    private final FindCharacterCommandQuery characterQuery;


    public DuelController(DuelCommandService duelService, FindDuelCommandQuery duelQuery, CharacterCommandService characterService, FindCharacterCommandQuery characterQuery) {
        this.duelService = duelService;
        this.duelQuery = duelQuery;
        this.characterService = characterService;
        this.characterQuery = characterQuery;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDuel(@PathVariable Long id){
        duelService.delete(duelQuery.getById(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/pending/characters")
    public ResponseEntity<DuelResponse> challengeDuel(@RequestBody DuelRequest duelRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterService.challengeDuel(duelRequest, username).response();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/characters/{characterId}")
    public ResponseEntity<List<DuelResponse>> getAllDuels(@PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = duelService.getPendingDuels(characterQuery.getCharacterAuth(characterId, username)).stream().map(Duel::response).toList();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/characters/{characterId}")
    public ResponseEntity<CharacterResponse> finishDuel(@PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        characterService.endCurrentDuel(characterId, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("/loser/characters/{id}")
    public ResponseEntity<CharacterResponse> surrenderDuel(@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        var response = characterService.surrender(id, username).response();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}/characters/{characterId}")
    public ResponseEntity<?> acceptDuel(@PathVariable Long id, @PathVariable Long characterId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var username = auth.getName();
        characterService.acceptDuel(characterId, id, username);
        return ResponseEntity.ok().build();
    }

}
