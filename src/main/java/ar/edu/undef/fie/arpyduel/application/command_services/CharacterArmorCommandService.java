package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import org.springframework.stereotype.Service;

@Service
public class CharacterArmorCommandService {
    private final ArmorCommandService armorService;
    private final CharacterCommandService characterService;
    private final FindCharacterCommandQuery characterQuery;

    public CharacterArmorCommandService(ArmorCommandService armorService, CharacterCommandService characterService, FindCharacterCommandQuery characterQuery) {
        this.armorService = armorService;
        this.characterService = characterService;
        this.characterQuery = characterQuery;
    }

    public void equipClaimedArmor(Long characterId, String username) {
        var character = characterQuery.getCharacterAuth(characterId, username);
        armorService.deleteCharacterArmor(character);
        character.equipClaimedArmor();
        characterService.save(character);
    }
}