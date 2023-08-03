package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.exception.interactions.CharacterNotOwnedException;
import ar.edu.undef.fie.arpyduel.exception.notfound.CharacterNotFoundExceptionById;
import ar.edu.undef.fie.arpyduel.infrastructure.CharacterRepository;
import ar.edu.undef.fie.arpyduel.interfaces.responses.interactions.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FindCharacterCommandQuery {
    private final CharacterRepository repository;
    private final FindUserCommandQuery userQuery;

    public FindCharacterCommandQuery(CharacterRepository repository, FindUserCommandQuery userQuery) {
        this.repository = repository;
        this.userQuery = userQuery;
    }

    public List<Character> getAllByOnwerUsername(String username){
        return repository.findAllByOwnerUsername(username);
    }
    public Character getById(Long id){
        return repository.findById(id).orElseThrow(()->new CharacterNotFoundExceptionById(id));
    }
    public Character getByIdAndOwner(Long id){
        return repository.findById(id).orElseThrow(()->new CharacterNotFoundExceptionById(id));
    }

    public Character getCharacterAuth(Long characterId, String username){
        var character = getById(characterId);
        var owner = userQuery.getByUsername(username);
        if(!Objects.equals(character.getOwner(), owner)){
            throw new CharacterNotOwnedException();
        }
        return character;
    }

    public AvailableDuelInteractionsResponse getByAvailableDuelInteractions(Long id, String username) {
        var character = getCharacterAuth(id, username);
        var item = character.getItem();
        var weapon = character.getWeapon();
        var attackPower = character.getAttackPower();
        var basicAttackResponse = new BasicAttackInteraction(character.getDuel()!=null);
        var surrenderResponse = new SurrenderInteractionResponse(character.getDuel()!=null);
        var weaponAttackResponse = new WeaponAttackInteractionResponse(weapon!=null?weapon.response():null, character.getDuel()!=null&&character.getWeapon()!=null);
        var itemAttackResponse = new ItemInteractionResponse(item!=null?item.response():null, character.getDuel()!=null&&character.getWeapon()!=null);
        return new AvailableDuelInteractionsResponse(basicAttackResponse,weaponAttackResponse, itemAttackResponse, surrenderResponse);
    }
}
