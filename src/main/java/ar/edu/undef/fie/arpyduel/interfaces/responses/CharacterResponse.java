package ar.edu.undef.fie.arpyduel.interfaces.responses;

import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;

public class CharacterResponse {
    private final Long id;
    private final String name;
    private final Double health;
    private final Double level;
    private final Double experience;
    private final Double experienceRequiredToNextLevel;
    private final Integer claims;
    private final Boolean isDead;
    private final Double criticalStrikeChance;
    private final Double spellPower;
    private final Double attackPower;
    private final Long currentDuel;
    private final String chararcterClass;
    private final String debuff;
    private final Long weapon;
    private final Long armor;
    private final Double experiencePercentage;


    public CharacterResponse(Long id, Double health, Double level, Double criticalStrikeChance, Double spellPower,
                             Double attackPower, String name, Long weapon, Long armor, Boolean isDead, Long currentDuel,
                             CharacterClass characterClass, String debuff, Integer claims, Double experience, Double experienceRequiredToNextLevel) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.level = level;
        this.experience = experience;
        this.experienceRequiredToNextLevel = experienceRequiredToNextLevel;
        this.experiencePercentage = (experience/experienceRequiredToNextLevel)*100;
        this.claims = claims;
        this.isDead = isDead;
        this.criticalStrikeChance = criticalStrikeChance;
        this.spellPower = spellPower;
        this.attackPower = attackPower;
        this.currentDuel = currentDuel;
        this.chararcterClass = characterClass.getName();
        this.debuff = debuff;
        this.weapon = weapon;
        this.armor = armor;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getHealth() {
        return health;
    }

    public Double getLevel() {
        return level;
    }

    public Double getExperience() {
        return experience;
    }

    public Double getExperienceRequiredToNextLevel() {
        return experienceRequiredToNextLevel;
    }

    public Integer getClaims() {
        return claims;
    }

    public Boolean getDead() {
        return isDead;
    }

    public Double getCriticalStrikeChance() {
        return criticalStrikeChance;
    }

    public Double getSpellPower() {
        return spellPower;
    }

    public Double getAttackPower() {
        return attackPower;
    }

    public Long getCurrentDuel() {
        return currentDuel;
    }

    public String getChararcterClass() {
        return chararcterClass;
    }

    public String getDebuff() {
        return debuff;
    }

    public Long getWeapon() {
        return weapon;
    }

    public Long getArmor() {
        return armor;
    }

    public Double getExperiencePercentage() {
        return experiencePercentage;
    }
}
