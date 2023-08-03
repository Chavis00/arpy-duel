package ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs;


import ar.edu.undef.fie.arpyduel.domain.character.Character;

import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import jakarta.persistence.*;

@Entity
public class AimDisruption extends Debuff {


    public AimDisruption(String name, String description) {
        super(name, description);
    }

    public AimDisruption() {

    }

    @Override
    public void applyDebuffEffect(Character character) {
        character.setCriticalStrikeChance(character.getCriticalStrikeChance()*(3.0/4.0));
    }

    @Override
    public void removeDebuffEffect(Character character) {
        character.setCriticalStrikeChance(character.getCriticalStrikeChance()/(3.0/4.0));
    }

    @Override
    public Boolean isDamageOnTime() {
        return false;
    }
}
