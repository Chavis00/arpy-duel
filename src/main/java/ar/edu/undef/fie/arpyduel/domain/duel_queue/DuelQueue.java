package ar.edu.undef.fie.arpyduel.domain.duel_queue;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.exception.interactions.EmptyQueueException;

import jakarta.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="queue")
public class DuelQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Character> queue;

    @Enumerated(EnumType.STRING)
    private DuelEnum type;

    public DuelQueue(DuelEnum type) {
        this.type = type;
        this.queue = new LinkedList<>();
    }
    public DuelQueue() {
        this.queue = new LinkedList<>();
    }

    public List<Character> matchPlayers(){
        if(queue.size()==0){
            throw new EmptyQueueException();
        }
        if(queue.size() == 2){
            var player1 = queue.stream().findFirst().orElseThrow(EmptyQueueException::new);
            var player2 = findMatchingPlayer(player1);
            if(player2 != null) {
                queue.remove(player1);
                queue.remove(player2);
                return List.of(player1, player2);
            }
        }
        return List.of();
    }
    private Character findMatchingPlayer(Character character){
        for(var player : queue){
            if(player!=character){
                if(Objects.equals(player.getLevel(), character.getLevel())){
                    return player;
                }
            }
        }
        return null;
    }

    public void enqueue(Character character){
        queue.add(character);
    }
    public void dequeue(Character character){
        queue.remove(character);
    }

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Character> getQueue() {
        return queue;
    }

    public void setQueue(List<Character> queue) {
        this.queue = queue;
    }

    public DuelEnum getType() {
        return type;
    }

    public void setType(DuelEnum type) {
        this.type = type;
    }
}
