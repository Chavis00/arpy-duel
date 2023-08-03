package ar.edu.undef.fie.arpyduel.interfaces;

import ar.edu.undef.fie.arpyduel.application.command_services.DuelQueueCommandService;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/queue")
public class DuelQueueController {
    private final DuelQueueCommandService duelQueueService;

    public DuelQueueController(DuelQueueCommandService duelQueueService) {
        this.duelQueueService = duelQueueService;
    }

    @PostMapping("/types/{type}/characters/{characterId}")
    public ResponseEntity<?> joinNormalQueue(@PathVariable DuelEnum type, @PathVariable Long characterId) {
        duelQueueService.joinQueue(type, characterId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/types")
    public ResponseEntity<?> getQueueTypes() {
        return ResponseEntity.ok(DuelEnum.values());
    }
}
