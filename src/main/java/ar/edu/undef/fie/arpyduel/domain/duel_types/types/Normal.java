package ar.edu.undef.fie.arpyduel.domain.duel_types.types;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import jakarta.persistence.Entity;

@Entity
public class Normal extends DuelType {
    public Normal(DuelEnum type) {
        super(type);
    }
    public Normal() {
    }

    @Override
    public void winDuel(Character character) {
        character.setDuelExperienceByOpponent();
        character.setClaims(character.getClaims()+1);
        character.tryToLevelUp();
    }

    @Override
    public void looseDuel(Character character) {
        character.setDuelExperienceByOpponentLoose();
        character.tryToLevelUp();
    }
}
