package ar.edu.undef.fie.arpyduel.domain.duel;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import ar.edu.undef.fie.arpyduel.exception.badrequest.CharacterNotBelongToDuelException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.DuelResponse;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "duels")
public class Duel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer turnCounter;
    @ManyToOne
    private Character player1;
    @ManyToOne
    private Character player2;
    @ManyToOne
    private Character turnOf;
    @ManyToOne
    private Character winner;
    private String challenger;

    @ManyToOne
    private DuelType type;

    public DuelResponse response(){
        return new DuelResponse(id, turnCounter,
                getWinnerOp().map(Character::getName).orElse(null), challenger);
    }
    public Duel(Character player1, Character player2, DuelType type) {
        this.turnCounter = 0;
        this.player1 = player1;
        this.player2 = player2;
        this.challenger = player1.getName();
        this.turnOf = player1;
        this.type = type;
    }

    public Duel() {

    }

    public void skipTurn(){
        turnCounter += 1;
        getTurnOf().applyDot();
    }
    public void endDuel(Character winner) {
        var characters = getCharacters();
        this.winner = winner;
        type.winDuel(winner);
        type.looseDuel(getLooser());
        characters.forEach(Character::endCurrentDuel);

    }
    private Character getLooser(){
        return getOpponent(winner);
    }
    public Character nextCharacter(){
        return turnOf==player1? player2:player1;
    }
    public void nextTurn() {
        turnCounter += 1;
        turnOf=nextCharacter();
    }

    public Boolean someoneIsDead(){
        return getCharacters().stream().map(Character::isDead).toList().contains(true);
    }
    public Character calculateWinner(){
        return getCharacters().stream().filter(character -> !character.isDead()).findFirst().orElse(null);
    }
    public void checkStatus() {
        if (someoneIsDead()) {
            var winner = calculateWinner();
            endDuel(winner);
        }
    }
    public Character getOpponent(Character player) {
        return getCharacters().stream().filter(c -> !c.equals(player)).findFirst().orElseThrow(CharacterNotBelongToDuelException::new);
    }
    /*
        Getters & Setters
     */
    public Character turnOf(){
        return getTurnOf();
    }

    public Integer getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(Integer turn) {
        this.turnCounter = turn;
    }

    public Character getWinner() {
        return winner;
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public List<Character> getCharacters() {
        return List.of(player1, player2);
    }


    public String getChallenger() {
        return challenger;
    }

    //Optional
    public Optional<Character> getWinnerOp() {
        return Optional.ofNullable(winner);
    }

    public Character getPlayer1() {
        return player1;
    }

    public void setPlayer1(Character player1) {
        this.player1 = player1;
    }

    public Character getPlayer2() {
        return player2;
    }

    public void setPlayer2(Character player2) {
        this.player2 = player2;
    }

    public Character getTurnOf() {
        return turnOf;
    }

    public void setTurnOf(Character turnOf) {
        this.turnOf = turnOf;
    }


    public void setChallenger(String challenger) {
        this.challenger = challenger;
    }

    public DuelType getType() {
        return type;
    }

    public void setType(DuelType type) {
        this.type = type;
    }
}
