package ar.edu.undef.fie.arpyduel.domain.duel_types.types;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import jakarta.persistence.Entity;

@Entity
public class Ranked extends DuelType {
    public Ranked(DuelEnum type) {
        super(type);
    }
    public Ranked() {
    }
    @Override
    public void winDuel(Character character) {

    }

    @Override
    public void looseDuel(Character character) {

    }
}
