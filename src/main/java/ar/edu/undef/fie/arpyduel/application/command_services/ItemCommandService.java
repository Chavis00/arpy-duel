package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.FindCharacterCommandQuery;
import ar.edu.undef.fie.arpyduel.application.command_queries.FindDebuffCommandQuery;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.item.Item;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoClaimedItemException;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoClaimsAvailableException;
import ar.edu.undef.fie.arpyduel.exception.notfound.ItemNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ItemCommandService {
    private final ItemRepository repository;
    private final FindDebuffCommandQuery debuffQuery;
    private final CharacterCommandService characterService;
    private final FindCharacterCommandQuery characterQuery;



    public ItemCommandService(ItemRepository repository, FindDebuffCommandQuery debuffQuery, CharacterCommandService characterService, FindCharacterCommandQuery characterQuery) {
        this.repository = repository;
        this.debuffQuery = debuffQuery;
        this.characterService = characterService;
        this.characterQuery = characterQuery;
    }

    public void save(Item item){
        this.repository.save(item);
    }

    public Item generateClaim(Long characterId, String username) {
        var character = characterQuery.getCharacterAuth(characterId, username);
        if (character.hasEmptyClaims())
            throw new NoClaimsAvailableException();
        deleteCharacterClaimItem(character);
        var item = generateAndCreateItem();
        character.setItemClaim(item);// Set Item to Character Item Claims
        character.setClaims(character.getClaims()-1); // Claims - 1
        characterService.save(character);
        return item;
    }
    public void equipClaimedItem(Long id, String username) {
        var character = characterQuery.getCharacterAuth(id, username);
        if(character.getItemClaim() == null)
            throw new NoClaimedItemException();
        deleteCharacterItem(character);
        character.equipClaimedItem();
        characterService.save(character);
    }
    public Item generateAndCreateItem(){
        // Get All Items
        var availableDebuffs = debuffQuery.getAll();
        // Get Random Item
        var randomEffectIndex = new Random().nextInt(availableDebuffs.size());
        var randomEffect = availableDebuffs.get(randomEffectIndex);
        var item = new Item(randomEffect); // Create Item
        return repository.save(item); // Save Item
    }
    public void deleteCharacterClaimItem(Character character){
        if(character.hasItemClaim()){
            var oldItem = character.getItemClaim();
            character.setItemClaim(null);
            characterService.save(character);
            delete(oldItem);
        }
    }
    public void deleteCharacterItem(Character character){
        if(character.hasItem()){
            var oldItem = character.getItem();
            character.setItem(null);
            characterService.save(character);
            delete(oldItem);
        }
    }
    public void delete(Item item){
        repository.delete(item);
    }

    public Item get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }
}
