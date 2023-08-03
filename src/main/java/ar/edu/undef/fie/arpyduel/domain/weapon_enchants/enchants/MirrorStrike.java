package ar.edu.undef.fie.arpyduel.domain.weapon_enchants.enchants;

import ar.edu.undef.fie.arpyduel.domain.character.Character;

import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import jakarta.persistence.*;
import java.util.Random;
@Entity
public class MirrorStrike extends Enchant {
    public MirrorStrike(String name, String description) {
        super(name, description);
    }

    public MirrorStrike() {

    }

    @Override
    public void enchantUse(Character character , Character opponent) {
        /*
        * 30% chance to deal double damage
        * 30% chance to deal 1/3 of the weapon damage to self
        * 40% chance to deal normal damage
         */
        int chanceToMirrorStrike = rollDice100();
        var weapon = character.getWeapon();
        var weaponDmg = weapon.use(character);
        character.dealDmg(opponent, weaponDmg);
        if(chanceToMirrorStrike < 30){
            weaponDmg *= 2;
        }
        if(chanceToMirrorStrike > 70){
            character.reduceHealth(weaponDmg/3);
        }
        character.dealDmg(opponent, weaponDmg);
    }
    private Integer rollDice100(){
        Random rn = new Random();
        return rn.nextInt(100) + 1;
    }
}
