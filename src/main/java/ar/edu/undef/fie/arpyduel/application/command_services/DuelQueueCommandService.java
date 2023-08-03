package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindDuelTypeCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.duel_queue.DuelQueue;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.infrastructure.DuelQueueRepository;
import org.springframework.stereotype.Service;

@Service
public class DuelQueueCommandService {
    private final DuelQueueRepository repository;
    private final FindCharacterCommandQuery characterQuery;
    private final CharacterCommandService characterService;
    private final FindDuelTypeCommandQuery duelTypeQuery;

    public DuelQueueCommandService(DuelQueueRepository repository, FindCharacterCommandQuery characterQuery, CharacterCommandService characterService, FindDuelTypeCommandQuery duelTypeQuery) {
        this.repository = repository;
        this.characterQuery = characterQuery;
        this.characterService = characterService;
        this.duelTypeQuery = duelTypeQuery;
    }

    public void joinQueue(DuelEnum type, Long characterId ) {
        var queue = repository.findByType(type).stream().findFirst().orElse(new DuelQueue(type));
        var character = characterQuery.getById(characterId);
        queue.enqueue(character);
        var players = queue.matchPlayers();
        var duelType = duelTypeQuery.getByType(type);

        if(!players.isEmpty()) {
            characterService.startDuel(players, duelType);
        }
        repository.save(queue);
    }
}
