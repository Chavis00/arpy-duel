package ar.edu.undef.fie.arpyduel.domain.character_class.classes;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    @Test
    void statsPerLevel() {
        var user = new User();
        var character = new Character("Hunter", new Hunter(),  user);
        var maxLevel = 30.0;
        for(int level=1; level<maxLevel+1; level++){
            character.levelUp();
            if (level==10){
                assertEquals(931.3225746154785, character.getHealth());
                assertEquals(100.85686188869539, character.getAttackPower());
                assertEquals(10.0, character.getSpellPower());
                assertEquals(0.16569331881168065, character.getCriticalStrikeChance());
            }
            if (level==20){
                assertEquals(8673.617379884035, character.getHealth());
                assertEquals(1017.2106590035376, character.getAttackPower());
                assertEquals(10.0, character.getSpellPower());
                assertEquals(0.18302850599219497, character.getCriticalStrikeChance());
            }
            if (level==30){
                assertEquals(64623.48535570528, character.getHealth());
                assertEquals(8142.275789430842, character.getAttackPower());
                assertEquals(10.0, character.getSpellPower());
                assertEquals(0.20017558148508494, character.getCriticalStrikeChance());
            }
        }
    }

}