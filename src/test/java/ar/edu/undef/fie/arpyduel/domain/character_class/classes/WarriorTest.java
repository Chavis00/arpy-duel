package ar.edu.undef.fie.arpyduel.domain.character_class.classes;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarriorTest {
    @Test
    void statsPerLevel() {
        var user = new User();
        var character = new Character("Warrior", new Warrior(),  user);
        var maxLevel = 30.0;
        for(int level=1; level<maxLevel+1; level++){
            character.levelUp();
            if (level==10){
                assertEquals(1180.5916207174116, character.getHealth());
                assertEquals(100.85686188869539, character.getAttackPower());
                assertEquals(10.0, character.getSpellPower());
                assertEquals(0.08981054088575684, character.getCriticalStrikeChance());
            }
            if (level==20){
                assertEquals(13937.965749081648, character.getHealth());
                assertEquals(1017.2106590035376, character.getAttackPower());
                assertEquals(10.0, character.getSpellPower());
                assertEquals(0.053772888361281336, character.getCriticalStrikeChance());
            }
            if (level==30){
                assertEquals(128555.04354071931, character.getHealth());
                assertEquals(8142.275789430842, character.getAttackPower());
                assertEquals(10.0, character.getSpellPower());
                assertEquals(0.03389033114888488, character.getCriticalStrikeChance());
            }
        }
    }
}