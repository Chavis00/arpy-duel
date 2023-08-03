package ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Hunter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AimDisruptionTest {

    @Test
    void applyDebuffEffect() {
        var user = new User();
        var hunterClass = new Hunter();
        var character = new Character("Test", hunterClass, user);
        var aimDisruption = new AimDisruption();
        var reducedCriticalStrikeChance = character.getCriticalStrikeChance()*(3.0/4.0);
        aimDisruption.applyDebuffEffect(character);
        assertEquals(reducedCriticalStrikeChance, character.getCriticalStrikeChance());

    }

    @Test
    void removeDebuffEffect() {
        var user = new User();
        var hunterClass = new Hunter();
        var character = new Character("Test", hunterClass, user);
        var aimDisruption = new AimDisruption();
        var initialCriticalChance = character.getCriticalStrikeChance();
        aimDisruption.applyDebuffEffect(character);
        aimDisruption.removeDebuffEffect(character);

        assertEquals(initialCriticalChance, character.getCriticalStrikeChance());
    }
}