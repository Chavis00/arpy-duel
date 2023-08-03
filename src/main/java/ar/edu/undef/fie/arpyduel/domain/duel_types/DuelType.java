package ar.edu.undef.fie.arpyduel.domain.duel_types;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import jakarta.persistence.*;

@Entity
@Table(name = "duel_types")
public abstract class DuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private DuelEnum type;

    public DuelType(DuelEnum type) {
        this.type = type;
    }
    public DuelType() {
    }
    public abstract void winDuel(Character character);
    public abstract void looseDuel(Character character);

    /*
        GETTERS & SETTERS
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DuelEnum getType() {
        return type;
    }

    public void setType(DuelEnum type) {
        this.type = type;
    }
}
