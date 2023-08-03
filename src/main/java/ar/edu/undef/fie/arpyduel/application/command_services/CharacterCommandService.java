package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.*;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.duel.Duel;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import ar.edu.undef.fie.arpyduel.exception.badrequest.*;
import ar.edu.undef.fie.arpyduel.infrastructure.CharacterRepository;
import ar.edu.undef.fie.arpyduel.interfaces.requests.CharacterRequest;
import ar.edu.undef.fie.arpyduel.interfaces.requests.DuelRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

@Service
public class CharacterCommandService {
    private final CharacterRepository repository;
    private final FindCharacterCommandQuery characterQuery;
    private final FindClassCommandQuery classQuery;
    private final DuelCommandService duelService;
    private final FindDuelCommandQuery duelQuery;
    private final FindUserCommandQuery userQuery;
    private final UserCommandService userService;
    private final WeaponCommandService weaponService;
    private final FindDuelTypeCommandQuery duelTypeQuery;

    public CharacterCommandService(CharacterRepository repository, FindCharacterCommandQuery characterQuery, FindClassCommandQuery classQuery, DuelCommandService duelService, FindDuelCommandQuery duelQuery, FindUserCommandQuery userQuery, UserCommandService userService, WeaponCommandService weaponService, FindDuelTypeCommandQuery duelTypeQuery) {
        this.repository = repository;
        this.characterQuery = characterQuery;
        this.classQuery = classQuery;
        this.duelService = duelService;
        this.duelQuery = duelQuery;
        this.userQuery = userQuery;
        this.userService = userService;
        this.weaponService = weaponService;
        this.duelTypeQuery = duelTypeQuery;
    }

    public Character save(Character character){
        return repository.save(character);
    }
    public List<Character> saveAll(List<Character> characters){
        return repository.saveAll(characters);
    }
    public void delete(Long id, String username) {
        var character = characterQuery.getCharacterAuth(id, username);
        var user = userQuery.getByUsername(username);
        user.removeCharacter(character);
        userService.save(user);
        repository.delete(character);
        user.removeCharacter(character);
        userService.save(user);
    }

    public Character updateName(Long characterId, String name, String username) {
        var character = characterQuery.getCharacterAuth(characterId, username);
        character.setName(name);
        return repository.save(character);
    }

    public Character create(CharacterRequest request, String username) {
        var user = userQuery.getByUsername(username);
        user.checkCharacterLimit();
        var characterClass = classQuery.getById(request.getClassId());
        var character = new Character(request.getName(), characterClass, user);
        repository.save(character);
        user.addCharacter(character);
        userService.save(user);
        return character;
    }




    public Character performAttack(Long id, String username, BiConsumer<Character, Character> attackFunction) {
        var character = characterQuery.getCharacterAuth(id, username);
        var characters = new ArrayList<Character>();
        var duel = character.getDuelOp().orElseThrow(NotInDuelException::new);

        if (character.isDead()){
            throw new CharacterIsDeadException();
        }
        if (duel.turnOf() != character){
            throw new WrongTurnException();
        }
        if (character.getDuel() == null){
            throw new NotInDuelException();
        }
        var opponent = character.getDuel().getOpponent(character);
        attackFunction.accept(character, opponent);
        duelService.checkDuelStatus(duel);

        characters.add(character);
        characters.add(opponent);
        repository.saveAll(characters);
        duel.nextTurn();
        duelService.save(duel);
        return opponent;
    }

    public Character basicAttack(Long id, String username) {
        return performAttack(id, username, Character::basicAttack);
    }

    public Character weaponAttack(Long id, String username, Boolean enchant) {
        if(enchant)
            return performAttack(id, username, Character::weaponAttackEnchanted);
        return performAttack(id, username, Character::weaponAttack);

    }

    public Character useItem(Long id, String username) {
        return performAttack(id, username, Character::useItem);
    }

    public void acceptDuel(Long id, Long duelId, String username) {
        var character = characterQuery.getCharacterAuth(id, username);
        var duel = duelQuery.getById(duelId);
        var characters = duel.getCharacters();

        if (Objects.equals(duel.getChallenger(), character.getName()))
            throw new ChallengerCanNotAcceptDuel(duelId);
        if (!character.getPendingDuels().contains(duel))
            throw new DuelNotInPendingDuelsException(duelId);

        duel.setTurnOf(duel.getOpponent(character));
        characters.forEach(c-> c.acceptDuel(duel));
        repository.saveAll(characters);
    }


    public Character surrender(Long characterId, String username) {
        var character = characterQuery.getCharacterAuth(characterId, username);
        var opponent = character.getDuel().getOpponent(character);
        if (opponent != character.getDuel().getOpponent(character))
            throw new NotInDuelWithCharacter(opponent.getName());
        var duel = character.getDuel();
        var characters = new ArrayList<Character>();

        duel.endDuel(opponent);


        characters.add(character);
        characters.add(opponent);
        repository.saveAll(characters);
        duelService.save(duel);

        return character;
    }


    public void endCurrentDuel(Long id, String username) {
        var character = characterQuery.getCharacterAuth(id, username);
        var duel = character.getDuel();
        if (character.getDuel() == null)
            throw new NotInDuelException();
        if(character.getDuel().getWinner() == null)
            throw new RuntimeException("No puedes Borrar un duelo que no termino");
        character.endCurrentDuel();
        duel.endDuel(character);
        repository.save(character);
    }
    public Duel challengeDuel(DuelRequest request, String username) {
        var character = characterQuery.getCharacterAuth(request.getChallengerId(), username);
        var opponent = characterQuery.getById(request.getOpponentId());
        var characters = new ArrayList<Character>();
        var duelType = duelTypeQuery.getByType(DuelEnum.FRIENDLY);
        var duel = character.challengeToDuel(opponent, duelType);

        duelService.save(duel);
        characters.add(character);
        characters.add(opponent);
        repository.saveAll(characters);
        return duel;
    }

    public void startDuel(List<Character> characters, DuelType duelType) {
        var duel = duelService.startDuel(characters.get(0), characters.get(1), duelType);
        for (var character: characters){
            character.setDuel(duel);
        }
        repository.saveAll(characters);
    }

    public FindCharacterCommandQuery getCharacterQuery() {
        return characterQuery;
    }

}
