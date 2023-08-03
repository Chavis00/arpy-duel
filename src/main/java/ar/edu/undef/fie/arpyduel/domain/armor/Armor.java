package ar.edu.undef.fie.arpyduel.domain.armor;


import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmorResponse;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "armors")
public class Armor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double healthBonus;
    @Column(nullable = false)
    private Double criticalBonus;
    @Column(nullable = false)
    private Double spellPowerBonus;


    public ArmorResponse response(){
        return new ArmorResponse(id, healthBonus, criticalBonus, spellPowerBonus);
    }
    public Armor(){
        this.healthBonus = 40.0;
        this.criticalBonus = 0.01;
        this.spellPowerBonus = 1.0;
    }


    public void buildArmor(Double level) {
        setStats(level);
    }
    private void setStats(Double level){
        var regulatorSpellPower = 1.0;
        var regulatorHealth = 40.0;
        var regulatorCrit = 0.00001;
        // each stat has a different random bonus to increase the stat
        setCriticalBonus(calculateStatPerLevel(getCriticalBonus(), level, getBonusIncrement(), regulatorCrit));
        setHealthBonus(calculateStatPerLevel(getHealthBonus(), level, getBonusIncrement(), regulatorHealth));
        setSpellPowerBonus(calculateStatPerLevel(getSpellPowerBonus(), level, 3.0, regulatorSpellPower));
    }
    private Double calculateStatPerLevel(Double firstLevelStatValue,Double level, Double increment, Double regulator){
        // statAtCurrentLevel = statAtPreviousLevel + lvl * increment * regulator
        if (level==1)
            return firstLevelStatValue + (level * increment * regulator);
        var statAtPreviousLevel = calculateStatPerLevel(firstLevelStatValue, level-1, increment, regulator);
        return statAtPreviousLevel + (level * increment * regulator);
    }
    private Double getBonusIncrement(){
        /*
            50% chance to get a common bonus
            20% chance to get a rare bonus
            3% chance to get an epic bonus
         */
        int chanceToUpgrade = rollDace100();
        if(chanceToUpgrade<5)
            return getBonusEpic();
        if (chanceToUpgrade<20)
            return getBonusRare();
        if (chanceToUpgrade<50)
            return getBonusCommon();
        return getBonusBase();
    }
    private double calculateCritic(double number){
            return 1.0 + 0.25 * (number - 1.0);
        }
    private Integer rollDace100(){
        // roll a d100
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
    /*
        BONUS
    */
    private Double getBonusBase(){
        return 2.0;
    }
    private Double getBonusCommon(){
        return 3.0;
    }
    private Double getBonusRare(){
        return 3.8;
    }
    private Double getBonusEpic(){
        return 5.0;
    }


    /*
        GETTERS & SETTERS
    */
    public Double getCriticalBonus() {
        return criticalBonus;
    }

    public void setCriticalBonus(Double bonusCritico) {
        this.criticalBonus = bonusCritico;
    }

    public Double getSpellPowerBonus() {
        return spellPowerBonus;
    }

    public void setSpellPowerBonus(Double spellPowerBonus) {
        this.spellPowerBonus = spellPowerBonus;
    }


    public Double getHealthBonus() {
        return healthBonus;
    }

    public void setHealthBonus(Double healthBonus) {
        this.healthBonus = healthBonus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
