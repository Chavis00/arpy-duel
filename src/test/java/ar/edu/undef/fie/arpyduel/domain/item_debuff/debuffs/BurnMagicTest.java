package ar.edu.undef.fie.arpyduel.domain.item_debuff.debuffs;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Hunter;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Mage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BurnMagicTest {

    @Test
    void applyDebuffEffect() {
        var user = new User();
        var mageClass = new Mage();
        var character = new Character("Test", mageClass, user);
        var burnMagic = new BurnMagic();
        var reducedHealth = character.getMaxHealth()- character.getSpellPower()*(4.0/10.0);
        burnMagic.applyDebuffEffect(character);
        assertEquals(reducedHealth, character.getHealth());
    }

    @Test
    void removeDebuffEffect() {
        var user = new User();
        var mageClass = new Mage();
        var character = new Character("Test", mageClass, user);
        var burnMagic = new BurnMagic();
        var reducedHealth = character.getMaxHealth()- character.getSpellPower()*(4.0/10.0);
        burnMagic.applyDebuffEffect(character);
        burnMagic.removeDebuffEffect(character);
        assertEquals(reducedHealth, character.getHealth());
    }
}