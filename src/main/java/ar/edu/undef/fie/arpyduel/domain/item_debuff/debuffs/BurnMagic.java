package ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs;


import ar.edu.undef.fie.arpyduel.domain.character.Character;

import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import jakarta.persistence.*;

@Entity
public class BurnMagic extends Debuff {

    public BurnMagic(String name, String description) {
        super(name, description);
    }

    public BurnMagic() {

    }

    @Override
    public void applyDebuffEffect(Character character) {
        character.reduceHealth(character.getSpellPower()*(4.0 /10.0));
    }

    @Override
    public void removeDebuffEffect(Character character) {
        return;
    }

    @Override
    public Boolean isDamageOnTime() {
        return false;
    }
}
