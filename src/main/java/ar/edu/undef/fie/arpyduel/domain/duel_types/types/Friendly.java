package ar.edu.undef.fie.arpyduel.domain.duel_types.types;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import jakarta.persistence.Entity;

@Entity
public class Friendly extends DuelType {
    public Friendly(DuelEnum type) {
        super(type);
    }
    public Friendly() {
    }

    @Override
    public void winDuel(Character character) {

    }

    @Override
    public void looseDuel(Character character) {

    }
}
