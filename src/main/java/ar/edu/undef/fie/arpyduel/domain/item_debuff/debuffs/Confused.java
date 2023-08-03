package ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs;

import ar.edu.undef.fie.arpyduel.domain.character.Character;

import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import jakarta.persistence.*;
import java.util.Random;

@Entity
public class Confused extends Debuff {

    public Confused(String name, String description) {
        super(name, description);
    }

    public Confused() {

    }

    @Override
    public void applyDebuffEffect(Character character) {
        Random rn = new Random();
        int debuffProbability = rn.nextInt(100) + 1;

        if (debuffProbability < 26){
            character.reduceHealth(character.getAttackPower());
            character.getDuel().skipTurn();
        }
    }

    @Override
    public void removeDebuffEffect(Character character) {
    }

    @Override
    public Boolean isDamageOnTime() {
        return true;
    }

}
