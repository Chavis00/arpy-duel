package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.armor.Armor;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoClaimsAvailableException;
import ar.edu.undef.fie.arpyduel.exception.notfound.ArmorNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmorRepository;
import org.springframework.stereotype.Service;

@Service
public class ArmorCommandService {
    private final ArmorRepository repository;
    private final CharacterCommandService characterService;
    private final FindCharacterCommandQuery characterQuery;

    public ArmorCommandService(ArmorRepository repository, CharacterCommandService characterService) {
        this.repository = repository;
        this.characterService = characterService;
        this.characterQuery = characterService.getCharacterQuery();
    }

    public Armor save(Armor armor){
        return repository.save(armor);
    }

    public Armor generateClaim(Long characterId, String username) {
        var character = characterQuery.getCharacterAuth(characterId, username);
        if (character.hasEmptyClaims())
            throw new NoClaimsAvailableException();
        Armor armor = new Armor();
        armor.buildArmor(character.getLevel());
        repository.save(armor);
        deleteCharacterClaimArmor(character);
        character.setClaims(character.getClaims()-1);
        character.setClaimArmor(armor);
        characterService.save(character);
        return armor;
    }
    public void deleteCharacterClaimArmor(Character character){
        if(character.hasArmorClaim()){
            var oldArmor = character.getArmorClaim();
            character.setArmorClaim(null);
            characterService.save(character);
            delete(oldArmor);
        }
    }
    public void deleteCharacterArmor(Character character){
        if(character.hasArmor()){
            var oldArmor = character.getArmor();
            character.setArmor(null);
            characterService.save(character);
            delete(oldArmor);
        }
    }

    public Armor get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ArmorNotFoundException(id));
    }

    public void delete(Armor oldArmor) {
        repository.delete(oldArmor);
    }
}
