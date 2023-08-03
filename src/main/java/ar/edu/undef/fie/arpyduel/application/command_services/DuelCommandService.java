package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.duel.Duel;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import ar.edu.undef.fie.arpyduel.infrastructure.DuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuelCommandService {
    private final DuelRepository repository;
    private final FindCharacterCommandQuery characterQuery;

    public DuelCommandService(DuelRepository repository, FindCharacterCommandQuery characterQuery) {
        this.repository = repository;
        this.characterQuery = characterQuery;
    }

    public void save(Duel duel){
        repository.save(duel);
    }
    public void delete(Duel duel){
        for(Character character : duel.getCharacters()){
            character.setDuel(null);
            character.getPendingDuels().removeIf(duelo1 -> duelo1 == duel);
            //characterService.save(character);
        }
        repository.delete(duel);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Duel> getPendingDuels(Character character) {
        return character.getPendingDuels();
    }

    public void checkDuelStatus(Duel duel) {
        duel.checkStatus();
    }

    public Duel startDuel(Character player1, Character player2, DuelType type) {
        var duel = new Duel(player1, player2, type);
        return repository.save(duel);
    }
}
