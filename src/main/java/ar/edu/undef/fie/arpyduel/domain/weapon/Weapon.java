package ar.edu.undef.fie.arpyduel.domain.weapon;

import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.exception.interactions.NoEnchantException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.WeaponResponse;

import jakarta.persistence.*;

import java.util.Random;
@Entity
@Table(name = "weapons")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double dmg;

    @Column(nullable = false)
    private Double attackPowerBonus;
    @Column(nullable = false)
    private Double spellPowerBonus;
    @Column(nullable = false)
    private Double criticalChanceBonus;
    @ManyToOne
    private WeaponType type;
    @ManyToOne
    private Enchant enchant;
    @OneToOne
    private Character owner;

    public WeaponResponse response(){
        return new WeaponResponse(id, dmg, attackPowerBonus, spellPowerBonus, criticalChanceBonus, enchant.response());
    }

    public Weapon(WeaponType weaponType) {
        this.dmg = 1.0;
        this.attackPowerBonus = 1.0;
        this.spellPowerBonus = 1.0;
        this.criticalChanceBonus = 0.052;
        this.type = weaponType;
        this.enchant = null;
    }

    public Weapon() {
        this.dmg = 1.0;
        this.attackPowerBonus = 1.0;
        this.spellPowerBonus = 1.0;
        this.criticalChanceBonus = 0.05;
        this.type = null;
        this.enchant = null;
    }


    public void buildWeapon(Double level){
        setStats(level);
    }
    private void setStats(Double level){
        // critical chance strike is calculated with a different regulator
        var regulatorBase = 1.0;
        var regulatorForCriticChance = 0.00035 * 0.5;
        // each stat has a different random bonus to increase the stat
        setDmg(calculateStatPerLevel(dmg,level, getBonusIncrement(), regulatorBase));
        setAttackPowerBonus(calculateStatPerLevel(attackPowerBonus, level, getBonusIncrement(), regulatorBase));
        setCriticalChanceBonus(calculateStatPerLevel(criticalChanceBonus, level, getBonusIncrement(), regulatorForCriticChance));
        setSpellPowerBonus(calculateStatPerLevel(spellPowerBonus, level, getBonusIncrement(), regulatorBase));

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
    private Double calculateStatPerLevel(Double firstLevelStatValue,Double level, Double increment, Double regulator){
        // statAtCurrentLevel = statAtPreviousLevel + lvl * increment * regulator
        if (level==1)
            return firstLevelStatValue + (level * increment * regulator);
        var statAtPreviousLevel = calculateStatPerLevel(firstLevelStatValue, level-1, increment, regulator);
        return statAtPreviousLevel + (level * increment * regulator);
    }

    private Integer rollDace100(){
        // roll a d100
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public Double use(Character character){
        /*
            Each weapon has a different way to calculate the damage
            If the weapon has multiple hit, it will calculate the magic damage
            If the weapon has not multiple hit, it will calculate the physical damage
         */
        if(getType().hasWeaponMultipleHit()){
            return calculateMagicDmg(character);
        }
        return calculatePhysicalDmg(character);
    }
    public void useEnchant(Character character, Character opponent) {
        getEnchant().enchantUse(character, opponent);
    }
    private double calculatePhysicalDmg(Character character) {
        /*
            Physical damage is attack power damage with critical chance
         */
        var dmg = calculateAttackPowerDmg(character.getAttackPower());
        return calculateCritic(dmg, character.getCriticalStrikeChance());
    }

    private Double calculateMagicDmg(Character character) {
        /*
            Each time the weapon is used, it will roll a d4 to determine how many times the weapon will hit.
            Each hit will deal 1/3 of the spell power of the character.
            Each hit will have a 5% chance to be a critical hit.
            Each critical hit will deal double damage.
         */
        var magicDmg = 0.0;
        int totalTimes = getRandomTimes();
        for(int times=0; times<totalTimes+1; times++){
            var spell =   (character.getSpellPower()+getSpellPowerBonus())/3;
            magicDmg += calculateCritic(spell, character.getCriticalStrikeChance());
        }
        return  magicDmg;
    }

    public Double calculateAttackPowerDmg(Double attackPower){
        /*
            Attack power damage is calculated with the following formula:
            (weapon dmg + attack power + attack power bonus) * dmg bonus
            dmg bonus is calculated with the weapon type
         */
        return (getDmg()+attackPower+getAttackPowerBonus())*getDmgBonus();
    }
    public Double calculateCritic(Double dmg, Double criticalStrikeChance){
        /*
            Critical strike chance dmg is calculated with the following formula:
            dmg * critical strike bonus
            critical strike bonus is calculated with the weapon type
         */
        if (isCritic(criticalStrikeChance))
            dmg = dmg * getCriticalBonus();
        return dmg;
    }
    public Double getDmgBonus(){
        return type.calculateWeaponDmgBonus();
    }
    public Double getCriticalBonus(){
        return type.calculateWeaponCriticalStrikeBonus();
    }
    public  Boolean isCritic(Double criticalChance){
        Random rn = new Random();
        int chance = rn.nextInt(100) + 1;
        return chance < (criticalChance * 100);
    }
    private Integer getRandomTimes(){
        // roll a d4 if roll is 0 returns 0.5 (Half of attack)
        Random rn = new Random();
        return rn.nextInt(4)+ 1;
    }

    /*
        BONUS
    */
    private Double getBonusBase(){
        return 1.0;
    }
    private Double getBonusCommon(){
        return 1.2;
    }
    private Double getBonusRare(){
        return 1.5;
    }
    private Double getBonusEpic(){
        return 2.0;
    }


    /*
        GETTERS & SETTERS
    */
    public void setType(WeaponType t){
        type = t;
    }

    public Double getAttackPowerBonus() {
        return attackPowerBonus;
    }

    public void setAttackPowerBonus(Double attackPowerBonus) {
        this.attackPowerBonus = attackPowerBonus;
    }

    public Double getSpellPowerBonus() {
        return spellPowerBonus;
    }

    public void setSpellPowerBonus(Double spellPowerBonus) {
        this.spellPowerBonus = spellPowerBonus;
    }

    public Double getCriticalChanceBonus() {
        return criticalChanceBonus;
    }

    public void setCriticalChanceBonus(Double criticalChanceBonus) {
        this.criticalChanceBonus = criticalChanceBonus;
    }

    public Double getDmg() {
        return dmg;
    }

    public void setDmg(Double dmg) {
        this.dmg = dmg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public WeaponType getType() {
        return type;
    }

    public Enchant getEnchant() {
        if(enchant==null)
            throw new NoEnchantException();
        return enchant;
    }

    public void setEnchant(Enchant enchant) {
        this.enchant = enchant;
    }

    public Character getOwner() {
        return owner;
    }

    public void setOwner(Character owner) {
        this.owner = owner;
    }
}
