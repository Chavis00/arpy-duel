package ar.edu.undef.fie.arpyduel.domain.armor;

import ar.edu.undef.fie.arpyduel.domain.weapon.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    @Test
    void buildWeaponLvl10Health(){
        var armor = new Armor();
        var level = 10.0;
        armor.buildArmor(level);
        var isHeathInNormalRange = armor.getHealthBonus().equals(4440.0) ||
                armor.getHealthBonus().equals(6640.0) ||
                armor.getHealthBonus().equals(8400.0) ||
                armor.getHealthBonus().equals(11040.0);
        assertTrue(isHeathInNormalRange);
    }
    @Test
    void buildWeaponLvl20Health(){
        var armor = new Armor();
        var level = 20.0;
        armor.buildArmor(level);
        var isHeathInNormalRange = armor.getHealthBonus().equals(16840.0) ||
                armor.getHealthBonus().equals(25240.0) ||
                armor.getHealthBonus().equals(31960.0) ||
                armor.getHealthBonus().equals(42040.0);
        assertTrue(isHeathInNormalRange);
    }
    @Test
    void buildWeaponLvl30Health(){
        var armor = new Armor();
        var level = 30.0;
        armor.buildArmor(level);
        var isHeathInNormalRange = armor.getHealthBonus().equals(37240.0) ||
                armor.getHealthBonus().equals(55840.0) ||
                armor.getHealthBonus().equals(70720.0) ||
                armor.getHealthBonus().equals(93040.0);
        assertTrue(isHeathInNormalRange);
    }
    @Test
    void buildWeaponLvl10SpellPower(){
        var armor = new Armor();
        var level = 10.0;
        armor.buildArmor(level);
        System.out.println(armor.getSpellPowerBonus());
        var isSpellPowerBonusInNormalRange = armor.getSpellPowerBonus().equals(111.0) ||
                armor.getSpellPowerBonus().equals(166.0) ||
                armor.getSpellPowerBonus().equals(210.0) ||
                armor.getSpellPowerBonus().equals(276.0);
        assertTrue(isSpellPowerBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl20SpellPower(){
        var armor = new Armor();
        var level = 20.0;
        armor.buildArmor(level);
        var isSpellPowerBonusInNormalRange = armor.getSpellPowerBonus().equals(421.0) ||
                armor.getSpellPowerBonus().equals(631.0) ||
                armor.getSpellPowerBonus().equals(799.0) ||
                armor.getSpellPowerBonus().equals(1051.0);
        assertTrue(isSpellPowerBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl30SpellPower(){
        var armor = new Armor();
        var level = 30.0;
        armor.buildArmor(level);
        var isSpellPowerBonusInNormalRange = armor.getSpellPowerBonus().equals(931.0) ||
                armor.getSpellPowerBonus().equals(1396.0) ||
                armor.getSpellPowerBonus().equals(1768.0) ||
                armor.getSpellPowerBonus().equals(2326.0);
        assertTrue(isSpellPowerBonusInNormalRange);
    }
}