package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindEnchantCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.weapon.Weapon;
import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoClaimsAvailableException;
import ar.edu.undef.fie.arpyduel.infrastructure.WeaponRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WeaponCommandService {
    private final WeaponRepository repository;
    private final FindCharacterCommandQuery characterQuery;
    private final FindEnchantCommandQuery enchantQuery;
    private final CharacterRepository characterRepository;

    public WeaponCommandService(WeaponRepository repository, FindCharacterCommandQuery characterQuery, FindEnchantCommandQuery enchantQuery, CharacterRepository characterRepository) {
        this.repository = repository;
        this.characterQuery = characterQuery;
        this.enchantQuery = enchantQuery;
        this.characterRepository = characterRepository;
    }
    public Weapon save(Weapon weapon){
        return repository.save(weapon);
    }

    public void delete(Weapon weapon) {
        repository.delete(weapon);
    }

    public Weapon generateClaim(Character character, WeaponType type) {
        if (character.hasEmptyClaims())
            throw new NoClaimsAvailableException();
        deleteCharacterClaimWeapon(character);

        Weapon weapon = new Weapon(type);
        weapon.buildWeapon(character.getLevel());

        List<Enchant> availableEnchants = enchantQuery.getAll();
        Enchant randomEnchant = getRandomEnchant(availableEnchants);
        weapon.setEnchant(randomEnchant);

        character.setClaims(character.getClaims()-1);
        character.setClaimWeapon(weapon);
        repository.save(weapon);
        characterRepository.save(character);
        return weapon;
    }
    public Character equipClaimedWeapon(Long id, String username) {
        var character = characterQuery.getCharacterAuth(id, username);
        var weapon = character.getWeaponClaimOrException();
        deleteCharacterWeapon(character);
        weapon.setOwner(character);
        save(weapon);
        character.equipClaimedWeapon();
        return characterRepository.save(character);
    }
    private Enchant getRandomEnchant(List<Enchant> availableEnchants){
        var random = new Random();
        return availableEnchants.get(random.nextInt((int) enchantQuery.countEnchants()));
    }
    public void deleteCharacterClaimWeapon(Character character){
        if(character.hasWeaponClaim()){
            var oldWeapon = character.getWeaponClaim();
            character.setWeaponClaim(null);
            characterRepository.save(character);
            delete(oldWeapon);
        }
    }
    public void deleteCharacterWeapon(Character character){
        if(character.hasWeapon()){
            var oldWeapon = character.getWeapon();
            character.setWeapon(null);
            characterRepository.save(character);
            delete(oldWeapon);
        }
    }
}
