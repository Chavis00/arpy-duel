package ar.edu.undef.fie.arpyduel.domain.character;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.domain.armor.Armor;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Hunter;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Mage;
import ar.edu.undef.fie.arpyduel.domain.character_class.classes.Warrior;
import ar.edu.undef.fie.arpyduel.domain.duel.Duel;
import ar.edu.undef.fie.arpyduel.domain.duel_types.types.Friendly;
import ar.edu.undef.fie.arpyduel.domain.weapon.Weapon;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.types.Bow;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.types.Staff;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.types.Sword;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoWeaponEquippedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    void levelUp() {
        var user = new User();
        var hunter = new Hunter();
        var character = new Character("Hunter", hunter, user);
        for(int level = 1; level<35; level++){
            character.levelUp();
        }
        assertEquals(30, character.getLevel());
        }

    @Test
    void challengeToDuel() {
        var user1 = new User();
        var user2 = new User();
        var hunter = new Hunter();
        var warrior = new Warrior();
        var character = new Character("Character", hunter, user1);
        var opponent = new Character("Opponent", warrior, user2);
        assertTrue(opponent.getPendingDuels().isEmpty());
        character.challengeToDuel(opponent, new Friendly());
        assertEquals(1, opponent.getPendingDuels().size());
    }

    @Test
    void incrementStats() {
    }

    @Test
    void acceptDuel() {
        var user1 = new User();
        var user2 = new User();
        var hunter = new Hunter();
        var warrior = new Warrior();
        var character = new Character("Character", hunter, user1);
        var opponent = new Character("Opponent", warrior, user2);
        character.challengeToDuel(opponent, new Friendly());
        opponent.acceptDuel(opponent.getPendingDuels().get(0));
        assertNotNull(opponent.getDuel());
    }

    @Test
    void declineDuel() {
        var user1 = new User();
        var user2 = new User();
        var hunter = new Hunter();
        var warrior = new Warrior();
        var character = new Character("Character", hunter, user1);
        var opponent = new Character("Opponent", warrior, user2);
        character.challengeToDuel(opponent, new Friendly());
        opponent.declineDuel(opponent.getPendingDuels().get(0));
        assertNull(opponent.getDuel());
        assertTrue(opponent.getPendingDuels().isEmpty());
    }

    @Test
    void surrender() {
        var user1 = new User();
        var user2 = new User();
        var hunter = new Hunter();
        var warrior = new Warrior();
        var character = new Character("Character", hunter, user1);
        var opponent = new Character("Opponent", warrior, user2);
        character.challengeToDuel(opponent, new Friendly());
        opponent.acceptDuel(opponent.getPendingDuels().get(0));
        opponent.setHealth(1.0);
        opponent.surrender();
        assertEquals(opponent.getMaxHealth(), opponent.getHealth());
        assertNull(opponent.getDuel());
    }

    @Test
    void basicAttack() {
        var user1 = new User();
        var user2 = new User();
        var hunter = new Hunter();
        var warrior = new Warrior();
        var character = new Character("Character", hunter, user1);
        var opponent = new Character("Opponent", warrior, user2);
        var duel = new Duel(character, opponent, new Friendly());
        character.setDuel(duel);
        opponent.setDuel(duel);
        assertEquals(opponent.getHealth(), opponent.getMaxHealth());
        character.basicAttack(opponent);
        assertNotEquals(opponent.getHealth(), opponent.getMaxHealth());
    }

    @Test
    void weaponAttack() {
        var user1 = new User();
        var user2 = new User();
        var hunter = new Hunter();
        var warrior = new Warrior();
        var character = new Character("Character", hunter, user1);
        var opponent = new Character("Opponent", warrior, user2);
        var duel = new Duel(character, opponent, new Friendly());
        character.setDuel(duel);
        opponent.setDuel(duel);
        assertThrows(NoWeaponEquippedException.class, () -> character.weaponAttack(opponent));
        var bow = new Weapon(new Bow());
        character.setWeapon(bow);
        character.weaponAttack(opponent);
        assertNotEquals(opponent.getHealth(), opponent.getMaxHealth());
    }

    @Test
    void dealDmg() {
        var user1 = new User();
        var user2 = new User();
        var character = new Character("Character", new Hunter(), user1);
        var opponent = new Character("Opponent", new Warrior(), user2);
        var dmg = opponent.getMaxHealth();
        opponent.reduceHealth(dmg);
        assertEquals(opponent.getMaxHealth()-dmg, opponent.getHealth());
    }

    @Test
    void reduceHealth() {
        var user1 = new User();
        var character = new Character("Character", new Hunter(), user1);
        var dmg = character.getMaxHealth();
        character.reduceHealth(dmg);
        assertTrue(character.isDead());
    }

    @Test
    void kill() {
        var user1 = new User();
        var character = new Character("Character", new Hunter(), user1);
        character.kill();
        assertTrue(character.isDead());
        assertEquals(0.0 ,character.getHealth());
    }
    @Test
    void duelTestWarriorVsHunter(){
        var level = 30.0;
        var hunter = new Hunter();
        var warrior = new Warrior();
        var user1 = new User();
        var user2 = new User();
        var character = new Character("Hunter", hunter, user1);
        var opponent = new Character("Opponent", warrior, user2);
        var bow = new Weapon();
        bow.setType(new Bow());
        var sword = new Weapon();
        sword.setType(new Sword());
        var armor1 = new Armor();
        var armor2 = new Armor();
        // BUILDS
        armor1.buildArmor(level);
        armor2.buildArmor(level);
        bow.buildWeapon(level);
        sword.buildWeapon(level);

        for(int i = 0; i < level; i++) {
            character.levelUp();
            opponent.levelUp();
        }
        // EQUIPS
        character.equipArmor(armor1);
        character.setWeapon(bow);
        opponent.setWeapon(sword);
        opponent.equipArmor(armor2);

        character.setCriticalStrikeChance(character.getCriticalStrikeChance()+bow.getCriticalChanceBonus());
        opponent.setAttackPower(opponent.getAttackPower()+sword.getAttackPowerBonus());
        var duel = new Duel(character, opponent, new Friendly());

        System.out.println("-------- STATS --------");
        System.out.println("======= HUNTER =======");
        System.out.println("Health: " + character.getHealth());
        System.out.println("Attack Power: " + character.getAttackPower());
        System.out.println("Critic: " + character.getCriticalStrikeChance());
        System.out.println("Weapon Attack Power: " + bow.getAttackPowerBonus());
        System.out.println("Armor Health Bonus: " + character.getArmor().getHealthBonus());

        System.out.println("======= WARRIOR =======");
        System.out.println("Health: "+ opponent.getHealth());
        System.out.println("Attack Power: "+ opponent.getAttackPower());
        System.out.println("Critic: " + opponent.getCriticalStrikeChance());
        System.out.println("Weapon Attack Power: "+ sword.getAttackPowerBonus());
        System.out.println("Armor Health Bonus: " + opponent.getArmor().getHealthBonus());




        for(int i= 0; i<15; i++){
            character.weaponAttack(opponent);
            opponent.weaponAttack(character);
            System.out.println("-------- TURN " + (i+1) + " --------");
            System.out.println("Hunter: " + character.getHealth());
            System.out.println("Warrior: "+ opponent.getHealth());
        }
    }

        @Test
        void duelTestMageVsHunter(){
            var level = 30.0;
            var hunterClass = new Hunter();
            var mageClass = new Mage();
            var user1 = new User();
            var user2 = new User();
            var hunter = new Character("Hunter", hunterClass, user1);
            var mage = new Character("Mage", mageClass, user2);
            var bow = new Weapon();
            bow.setType(new Bow());
            var staff = new Weapon();
            staff.setType(new Staff());
            var armor1 = new Armor();
            var armor2 = new Armor();
            // BUILDS
            armor1.buildArmor(level);
            armor2.buildArmor(level);
            bow.buildWeapon(level);
            staff.buildWeapon(level);

            for(int i = 0; i < level; i++) {
                hunter.levelUp();
                mage.levelUp();
            }
            // EQUIPS
            hunter.equipArmor(armor1);
            hunter.setWeapon(bow);
            mage.setWeapon(staff);
            mage.equipArmor(armor2);

            hunter.setCriticalStrikeChance(hunter.getCriticalStrikeChance()+bow.getCriticalChanceBonus());
            mage.setAttackPower(mage.getAttackPower()+staff.getAttackPowerBonus());
            var duel = new Duel(hunter, mage, new Friendly());

            System.out.println("-------- STATS --------");
            System.out.println("======= HUNTER =======");
            System.out.println("Health: " + hunter.getHealth());
            System.out.println("Attack Power: " + hunter.getAttackPower());
            System.out.println("Critic: " + hunter.getCriticalStrikeChance());
            System.out.println("Weapon Attack Power: " + bow.getAttackPowerBonus());
            System.out.println("Armor Health Bonus: " + hunter.getArmor().getHealthBonus());

            System.out.println("======= MAGE =======");
            System.out.println("Health: "+ mage.getHealth());
            System.out.println("Attack Power: "+ mage.getAttackPower());
            System.out.println("Critic: " + mage.getCriticalStrikeChance());
            System.out.println("Weapon Attack Power: "+ staff.getAttackPowerBonus());
            System.out.println("Armor Health Bonus: " + mage.getArmor().getHealthBonus());




            for(int i= 0; i<15; i++){
                hunter.weaponAttack(mage);
                mage.weaponAttack(hunter);
                System.out.println("-------- TURN " + (i+1) + " --------");
                System.out.println("Hunter: " + hunter.getHealth());
                System.out.println("Mage: "+ mage.getHealth());
            }

    }
    @Test
    void duelTestWarriorVsMage(){
        var level = 1.0;
        var mageClass = new Mage();
        var warriorClass = new Warrior();
        var user1 = new User();
        var user2 = new User();
        var mage = new Character("Mage", mageClass, user1);
        var warrior = new Character("Opponent", warriorClass, user2);
        var staff = new Weapon();
        staff.setType(new Staff());
        var sword = new Weapon();
        sword.setType(new Sword());
        var armor1 = new Armor();
        var armor2 = new Armor();
        // BUILDS
        armor1.buildArmor(level);
        armor2.buildArmor(level);
        staff.buildWeapon(level);
        sword.buildWeapon(level);

        for(int i = 0; i < level; i++) {
            mage.levelUp();
            warrior.levelUp();
        }
        // EQUIPS
        mage.equipArmor(armor1);
        mage.setWeapon(staff);
        warrior.setWeapon(sword);
        warrior.equipArmor(armor2);

        warrior.setAttackPower(warrior.getAttackPower()+sword.getAttackPowerBonus());
        var duel = new Duel(mage, warrior, new Friendly());

        System.out.println("-------- STATS --------");
        System.out.println("======= MAGE =======");
        System.out.println("Health: " + mage.getHealth());
        System.out.println("Attack Power: " + mage.getAttackPower());
        System.out.println("Critic: " + mage.getCriticalStrikeChance());
        System.out.println("Weapon Attack Power: " + staff.getAttackPowerBonus());
        System.out.println("Armor Health Bonus: " + mage.getArmor().getHealthBonus());

        System.out.println("======= WARRIOR =======");
        System.out.println("Health: "+ warrior.getHealth());
        System.out.println("Attack Power: "+ warrior.getAttackPower());
        System.out.println("Critic: " + warrior.getCriticalStrikeChance());
        System.out.println("Weapon Attack Power: "+ sword.getAttackPowerBonus());
        System.out.println("Armor Health Bonus: " + warrior.getArmor().getHealthBonus());




        for(int i= 0; i<15; i++){
            mage.weaponAttack(warrior);
            warrior.weaponAttack(mage);
            System.out.println("-------- TURN " + (i+1) + " --------");
            System.out.println("Mage: " + mage.getHealth());
            System.out.println("Warrior: "+ warrior.getHealth());
        }
    }
}