package ar.edu.undef.fie.arpyduel.domain.duel_queue;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Hunter;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Warrior;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.exception.interactions.EmptyQueueException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DuelQueueTest {

    @Test
    void matchPlayers() {
        var character = new Character("Hunter", new Hunter(), new User());
        var opponent = new Character("Warrior", new Warrior(), new User());
        var players = List.of(character, opponent);
        var queue = new DuelQueue(DuelEnum.NORMAL);
        assertThrows(EmptyQueueException.class, queue::matchPlayers);
        queue.enqueue(character);
        queue.enqueue(opponent);
        var queuePlayers = queue.matchPlayers();
        assertEquals(players, queuePlayers);

    }

    @Test
    void enqueue() {
        var character = new Character("Hunter", new Hunter(), new User());
        var opponent = new Character("Warrior", new Warrior(), new User());
        var queue = new DuelQueue(DuelEnum.NORMAL);
        queue.enqueue(character);
        queue.enqueue(opponent);
        assertEquals(List.of(character, opponent), queue.getQueue());
    }
}