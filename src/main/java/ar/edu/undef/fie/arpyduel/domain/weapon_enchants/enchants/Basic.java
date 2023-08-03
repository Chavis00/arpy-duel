package ar.edu.undef.fie.arpyduel.domain.weapon_enchants.enchants;

import ar.edu.undef.fie.arpyduel.domain.character.Character;

import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import jakarta.persistence.*;

@Entity
public class Basic extends Enchant {

    public Basic(String name, String description) {
        super(name, description);
    }

    public Basic() {

    }

    @Override
    public void enchantUse(Character character , Character opponent) {
        character.weaponAttack(opponent);
    }
}
