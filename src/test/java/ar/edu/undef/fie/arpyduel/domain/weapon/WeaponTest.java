package ar.edu.undef.fie.arpyduel.domain.weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {
    private final Double basicStatLvl1 = 2.0;
    private final Double commonStatLvl1 = 2.2;
    private final Double rareStatLvl1 = 2.5;
    private final Double epicStatLvl1 = 3.0;
    private final Double basicStatLvl10 = 56.0;
    private final Double commonStatLvl10 = 67.0;
    private final Double rareStatLvl10 = 83.50;
    private final Double epicStatLvl10 = 111.0;
    private final Double basicStatLvl20 = 211.0;
    private final Double commonStatLvl20 = 253.0;
    private final Double rareStatLvl20 = 316.0;
    private final Double epicStatLvl20 = 421.0;
    private final Double basicStatLvl30 = 466.0;
    private final Double commonStatLvl30 = 559.0;
    private final Double rareStatLvl30 = 698.50;
    private final Double epicStatLvl30 = 931.0;
    // CRITICAL CHANCE
    private final Double basicCritLvl1 = 0.050175000000000004;
    private final Double commonCritLvl1 = 0.050210000000000005;
    private final Double rareCritLvl1 = 0.0502625;
    private final Double epicCritLvl1 = 0.050350000000000006;
    private final Double basicCritLvl10 = 0.059625000000000004;
    private final Double commonCritLvl10 = 0.06155;
    private final Double rareCritLvl10 = 0.0644375;
    private final Double epicCritLvl10= 0.06925;
    private final Double basicCritLvl20 = 0.08675000000000001;
    private final Double commonCritLvl20 = 0.0941;
    private final Double rareCritLvl20 = 0.105125;
    private final Double epicCritLvl20 = 0.12350000000000001;
    private final Double basicCritLvl30 = 0.13137500000000002;
    private final Double commonCritLvl30 = 0.14765000000000003;
    private final Double rareCritLvl30 = 0.17206249999999998;
    private final Double epicCritLvl30 = 0.21275000000000002;

    @Test
    void buildWeaponLvl1Dmg(){
        var weapon = new Weapon();
        var level = 1.0;
        weapon.buildWeapon(level);
        var isDmgInNormalRange = weapon.getDmg().equals(basicStatLvl1) ||
                weapon.getDmg().equals(commonStatLvl1) ||
                weapon.getDmg().equals(rareStatLvl1) ||
                weapon.getDmg().equals(epicStatLvl1);
        assertTrue(isDmgInNormalRange);
    }
    @Test
    void buildWeaponLvl10Dmg(){
        var weapon = new Weapon();
        var level = 10.0;
        weapon.buildWeapon(level);
        var isDmgInNormalRange = weapon.getDmg().equals(basicStatLvl10) ||
                weapon.getDmg().equals(commonStatLvl10) ||
                weapon.getDmg().equals(rareStatLvl10) ||
                weapon.getDmg().equals(epicStatLvl10);
        assertTrue(isDmgInNormalRange);
    }
    @Test
    void buildWeaponLvl20Dmg(){
        var weapon = new Weapon();
        var level = 20.0;
        weapon.buildWeapon(level);
        var isDmgInNormalRange = weapon.getDmg().equals(basicStatLvl20) ||
                weapon.getDmg().equals(commonStatLvl20) ||
                weapon.getDmg().equals(rareStatLvl20) ||
                weapon.getDmg().equals(epicStatLvl20);
        assertTrue(isDmgInNormalRange);
    }
    @Test
    void buildWeaponLvl30Dmg(){
        var weapon = new Weapon();
        var level = 30.0;
        weapon.buildWeapon(level);
        var isDmgInNormalRange = weapon.getDmg().equals(basicStatLvl30) ||
                weapon.getDmg().equals(commonStatLvl30) ||
                weapon.getDmg().equals(rareStatLvl30) ||
                weapon.getDmg().equals(epicStatLvl30);
        assertTrue(isDmgInNormalRange);
    }

    @Test
    void buildWeaponLvl1AttackPower(){
        var weapon = new Weapon();
        var level = 1.0;
        weapon.buildWeapon(level);
        var isAttackPowerInNormalRange = weapon.getAttackPowerBonus().equals(basicStatLvl1) ||
                weapon.getAttackPowerBonus().equals(commonStatLvl1) ||
                weapon.getAttackPowerBonus().equals(rareStatLvl1) ||
                weapon.getAttackPowerBonus().equals(epicStatLvl1);
        assertTrue(isAttackPowerInNormalRange);
    }
    @Test
    void buildWeaponLvl10AttackPower(){
        var weapon = new Weapon();
        var level = 10.0;
        weapon.buildWeapon(level);
        var isAttackPowerInNormalRange = weapon.getAttackPowerBonus().equals(basicStatLvl10) ||
                weapon.getAttackPowerBonus().equals(commonStatLvl10) ||
                weapon.getAttackPowerBonus().equals(rareStatLvl10) ||
                weapon.getAttackPowerBonus().equals(epicStatLvl10);
        assertTrue(isAttackPowerInNormalRange);
    }
    @Test
    void buildWeaponLvl20AttackPower(){
        var weapon = new Weapon();
        var level = 20.0;
        weapon.buildWeapon(level);
        var isAttackPowerInNormalRange = weapon.getAttackPowerBonus().equals(basicStatLvl20) ||
                weapon.getAttackPowerBonus().equals(commonStatLvl20) ||
                weapon.getAttackPowerBonus().equals(rareStatLvl20) ||
                weapon.getAttackPowerBonus().equals(epicStatLvl20);
        assertTrue(isAttackPowerInNormalRange);
    }
    @Test
    void buildWeaponLvl30AttackPower(){
        var weapon = new Weapon();
        var level = 30.0;
        weapon.buildWeapon(level);
        var isAttackPowerInNormalRange = weapon.getAttackPowerBonus().equals(basicStatLvl30) ||
                weapon.getAttackPowerBonus().equals(commonStatLvl30) ||
                weapon.getAttackPowerBonus().equals(rareStatLvl30) ||
                weapon.getAttackPowerBonus().equals(epicStatLvl30);
        assertTrue(isAttackPowerInNormalRange);
    }
    @Test
    void buildWeaponLvl1spellPowerBonus(){
        var weapon = new Weapon();
        var level = 1.0;
        weapon.buildWeapon(level);
        var isSpellPowerBonusInNormalRange = weapon.getSpellPowerBonus().equals(basicStatLvl1) ||
                weapon.getSpellPowerBonus().equals(commonStatLvl1) ||
                weapon.getSpellPowerBonus().equals(rareStatLvl1) ||
                weapon.getSpellPowerBonus().equals(epicStatLvl1);
        assertTrue(isSpellPowerBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl10spellPowerBonus(){
        var weapon = new Weapon();
        var level = 10.0;
        weapon.buildWeapon(level);
        var isSpellPowerBonusInNormalRange = weapon.getSpellPowerBonus().equals(basicStatLvl10) ||
                weapon.getSpellPowerBonus().equals(commonStatLvl10) ||
                weapon.getSpellPowerBonus().equals(rareStatLvl10) ||
                weapon.getSpellPowerBonus().equals(epicStatLvl10);
        assertTrue(isSpellPowerBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl20spellPowerBonus(){
        var weapon = new Weapon();
        var level = 20.0;
        weapon.buildWeapon(level);
        var isSpellPowerBonusInNormalRange = weapon.getSpellPowerBonus().equals(basicStatLvl20) ||
                weapon.getSpellPowerBonus().equals(commonStatLvl20) ||
                weapon.getSpellPowerBonus().equals(rareStatLvl20) ||
                weapon.getSpellPowerBonus().equals(epicStatLvl20);
        assertTrue(isSpellPowerBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl30spellPowerBonus(){
        var weapon = new Weapon();
        var level = 30.0;
        weapon.buildWeapon(level);
        var isSpellPowerBonusInNormalRange = weapon.getSpellPowerBonus().equals(basicStatLvl30) ||
                weapon.getSpellPowerBonus().equals(commonStatLvl30) ||
                weapon.getSpellPowerBonus().equals(rareStatLvl30) ||
                weapon.getSpellPowerBonus().equals(epicStatLvl30);
        assertTrue(isSpellPowerBonusInNormalRange);
    }

    @Test
    void buildWeaponLvl1CriticalChanceBonus(){
        var weapon = new Weapon();
        var level = 1.0;
        weapon.buildWeapon(level);
        var isCriticalChanceBonusInNormalRange = weapon.getCriticalChanceBonus().equals(basicCritLvl1) ||
                weapon.getCriticalChanceBonus().equals(commonCritLvl1) ||
                weapon.getCriticalChanceBonus().equals(rareCritLvl1) ||
                weapon.getCriticalChanceBonus().equals(epicCritLvl1);
        assertTrue(isCriticalChanceBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl10CriticalChanceBonus(){
        var weapon = new Weapon();
        var level = 10.0;
        weapon.buildWeapon(level);
        var isCriticalChanceBonusInNormalRange = weapon.getCriticalChanceBonus().equals(basicCritLvl10) ||
                weapon.getCriticalChanceBonus().equals(commonCritLvl10) ||
                weapon.getCriticalChanceBonus().equals(rareCritLvl10) ||
                weapon.getCriticalChanceBonus().equals(epicCritLvl10);
        assertTrue(isCriticalChanceBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl20CriticalChanceBonus(){
        var weapon = new Weapon();
        var level = 20.0;
        weapon.buildWeapon(level);
        var isCriticalChanceBonusInNormalRange = weapon.getCriticalChanceBonus().equals(basicCritLvl20) ||
                weapon.getCriticalChanceBonus().equals(commonCritLvl20) ||
                weapon.getCriticalChanceBonus().equals(rareCritLvl20) ||
                weapon.getCriticalChanceBonus().equals(epicCritLvl20);
        assertTrue(isCriticalChanceBonusInNormalRange);
    }
    @Test
    void buildWeaponLvl30CriticalChanceBonus(){
        var weapon = new Weapon();
        var level = 30.0;
        weapon.buildWeapon(level);
        var isCriticalChanceBonusInNormalRange = weapon.getCriticalChanceBonus().equals(basicCritLvl30) ||
                weapon.getCriticalChanceBonus().equals(commonCritLvl30) ||
                weapon.getCriticalChanceBonus().equals(rareCritLvl30) ||
                weapon.getCriticalChanceBonus().equals(epicCritLvl30);
        assertTrue(isCriticalChanceBonusInNormalRange);
    }
}