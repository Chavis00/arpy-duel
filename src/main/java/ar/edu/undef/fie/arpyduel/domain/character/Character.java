package ar.edu.undef.fie.arpyduel.domain.character;

import ar.edu.undef.fie.arpyduel.domain.account.User;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import ar.edu.undef.fie.arpyduel.domain.item.Item;
import ar.edu.undef.fie.arpyduel.domain.weapon.Weapon;
import ar.edu.undef.fie.arpyduel.domain.armor.Armor;
import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import ar.edu.undef.fie.arpyduel.domain.duel.Duel;
import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import ar.edu.undef.fie.arpyduel.exception.badrequest.CannotEquipArmorInDuelException;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoClaimedArmorException;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoEquippedItemException;
import ar.edu.undef.fie.arpyduel.exception.badrequest.NoWeaponEquippedException;
import ar.edu.undef.fie.arpyduel.exception.interactions.NoWeaponClaimException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.CharacterResponse;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Double health;
    @Column(nullable = false)
    private Double maxHealth;
    @Column(nullable = false)
    private Double level;
    protected Double speed;
    @Column(nullable = false)
    private Double criticalStrikeChance;
    @Column(nullable = false)
    private Double spellPower;
    @Column(nullable = false)
    private Double attackPower;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToOne
    private Weapon weapon;
    @OneToOne
    private Armor armor;
    @Column(nullable = false)
    private Boolean dead;
    @OneToMany
    private List<Duel> pendingDuels;
    @ManyToOne
    private Duel duel;
    @ManyToOne
    private CharacterClass characterClass;
    @OneToOne
    private Item item;
    @OneToOne
    private Debuff debuff;
    private Integer claims;
    @ManyToOne
    private Weapon weaponClaim;
    @ManyToOne
    private Armor armorClaim;
    @ManyToOne
    private Item itemClaim;
    private Double experience;
    private Double experienceRequiredToNextLevel;
    @ManyToOne
    private User owner;


    public CharacterResponse response(){
        return new CharacterResponse(id, health, level, criticalStrikeChance, spellPower, attackPower, name,
                getEquippedWeaponOp().map(Weapon::getId).orElse(null),
                getEquippedArmorOp().map(Armor::getId).orElse(null),
                dead, getDuelOp().map(Duel::getId).orElse(null),
                characterClass, getDebuffOp().map(Debuff::getName).orElse(null), claims,
                experience, experienceRequiredToNextLevel);
    }
    public Character(String name, CharacterClass characterClass, User owner) {
        //Cada vez que se crea un personaje empieza en lvl 1
        this.owner = owner;
        this.name = name;
        this.characterClass = characterClass;
        this.health = 100.0;
        this.maxHealth = this.health;
        this.level = 1.0;
        this.speed = 1.0;
        this.criticalStrikeChance = 0.15;
        this.spellPower = 10.0;
        this.attackPower = 10.0;
        this.weapon = null;
        this.armor = null;
        this.dead = false;
        this.item = null;
        this.claims = 4;
        this.experience = 0.0;
        this.experienceRequiredToNextLevel = level *10;
        pendingDuels = new ArrayList<Duel>();
    }

    public Character() {

    }

    public Duel challengeToDuel(Character opponent, DuelType duelType){
        Duel duel = new Duel(this, opponent, duelType);
        opponent.addPendingDuel(duel);
        return duel;
    }

    public void levelUp(){
        if (level >= 30){
            // Return without leveling up if character is already max level
            return;
        }
        setLevelUpExperience();
        setLevelUpExperienceRequiredToNextLevel();
        balanceLevelUpExperience();
        setLevelUpLevel();
        setLevelUpAttributes();
        rest();
    }

    /**
     * Sets the attributes of the character to the base attributes of the character class
     */
    private void setLevelUpAttributes(){
        var healthPercentageIncreasePerLevel = characterClass.getHealthPercentageIncreasePerLevel();
        var attackPercentagePowerIncreasePerLevel = characterClass.getAttackPercentagePowerIncreasePerLevel();
        var criticalStrikeChancePercentageIncreasePerLevel = characterClass.getCriticalStrikeChancePercentageIncreasePerLevel();
        var spellPowerPercentageIncreasePerLevel = characterClass.getSpellPowerPercentageIncreasePerLevel();
        removeArmorAttributes(); // Remove armor attributes before incrementing stats
        incrementStats(healthPercentageIncreasePerLevel, attackPercentagePowerIncreasePerLevel, criticalStrikeChancePercentageIncreasePerLevel, spellPowerPercentageIncreasePerLevel);
        applyArmorAttributes(getArmor()); // Apply armor attributes after incrementing stats
    }

    /**
     *  Increases the stats of the character by the given percentages
     */
    private void incrementStats(double healthIncreasePercentage, double attackPowerIncreasePercentage, double criticalStrikeChanceIncreasePercentage, double spellPowerIncreasePercentage) {
        var currentMaxHealth = getMaxHealth();
        var currentAttackPower = getAttackPower();
        var currentCriticalStrikeChance = getCriticalStrikeChance();
        var currentSpellPower = getSpellPower();
        var incrementedMaxHealth = currentMaxHealth + (currentMaxHealth * healthIncreasePercentage);
        var incrementedAttackPower = currentAttackPower + (currentAttackPower * attackPowerIncreasePercentage);
        var incrementedCriticalStrikeChance = currentCriticalStrikeChance + (currentCriticalStrikeChance * criticalStrikeChanceIncreasePercentage);
        var incrementedSpellPower = currentSpellPower + (currentSpellPower * spellPowerIncreasePercentage);
        setMaxHealth(incrementedMaxHealth);
        setAttackPower(incrementedAttackPower);
        setCriticalStrikeChance(incrementedCriticalStrikeChance);
        setSpellPower(incrementedSpellPower);
    }
    private void setLevelUpLevel(){
        setLevel(getLevel() + 1);
    }
    private void setLevelUpExperience(){
        setExperience(getExperience() - getExperienceRequiredToNextLevel());
    }
    private void setLevelUpExperienceRequiredToNextLevel(){
        setExperienceRequiredToNextLevel(getExperienceRequiredToNextLevel() * 1.3);
    }
    private void balanceLevelUpExperience(){
        if (getExperience() < 0)
            setExperience(0.0);
    }
    public void addPendingDuel(Duel duel){
        pendingDuels.add(duel);
    }

    public void acceptDuel(Duel duel){
        setDuel(duel);
        this.pendingDuels.remove(duel);
    }

    public void declineDuel(Duel duel){
        pendingDuels.remove(duel);
    }

    public void surrender(){
        this.pendingDuels.remove(this.duel);
        endCurrentDuel();
    }


    public void basicAttack(Character opponent){
        dealDmg(opponent, getAttackPower());
    }

    public void weaponAttack(Character opponent){
        var weapon = getWeaponOrException();
        var weaponDmg = weapon.use(this);
        dealDmg(opponent, weaponDmg);
    }
    public void weaponAttackEnchanted(Character opponent) {
        var weapon = getWeaponOrException();
        weapon.useEnchant(this, opponent);
    }
    public void useItem(Character opponent){
        var item = getItemOrException();
        item.especialItem(opponent);
    }
    public void dealDmg(Character rival, double dmg){
        rival.reduceHealth(dmg);
    }
    public Boolean hasEmptyClaims(){
        return this.claims < 1;

    }
    public void reduceHealth(double dmg) {
        this.health = this.health - dmg;
        if (this.health <= 0)
            kill();
    }

    public void kill(){
        setHealth(0.0);
        dead = true;
    }

    private void applyArmorAttributes(Armor armor) {
        if (armor == null)
            return;
        this.maxHealth = getMaxHealth()+armor.getHealthBonus();
        this.health = getMaxHealth();
        this.criticalStrikeChance += armor.getCriticalBonus();
        this.spellPower += armor.getSpellPowerBonus();
    }
    private void removeArmorAttributes() {
        if (getArmor() == null)
            return;
        this.health -= this.armor.getHealthBonus();
        this.criticalStrikeChance -= this.armor.getCriticalBonus();
        this.spellPower -= this.armor.getSpellPowerBonus();
    }

    public void equipArmor(Armor armor){
        if(getDuel()!=null)
            throw new CannotEquipArmorInDuelException();
        setArmor(armor);
        applyArmorAttributes(armor);
    }

    public void unequipArmor(){
        removeArmorAttributes();
        setArmor(null);
    }

    public boolean hasArmor() {
        return getArmor() != null;
    }
    public boolean hasArmorClaim() {
        return getArmorClaim() != null;
    }
    public boolean hasItemClaim() {
        return getItemClaim() != null;
    }
    public boolean hasItem() {
        return getItem() != null;
    }
    public boolean hasWeaponClaim() {
        return getWeaponClaim() != null;
    }
    public boolean hasWeapon() {
        return getWeapon() != null;
    }

    public void changeArmor(Armor armor){
        if (this.armor != null)
            unequipArmor();
        equipArmor(armor);
    }
    public void equipClaimedWeapon(){
        this.weapon = this.weaponClaim;
        setWeaponClaim(null);
    }
    public void equipClaimedArmor(){
        equipArmor(this.armorClaim);
        setArmorClaim(null);
    }
    public void equipClaimedItem(){
        this.item = this.itemClaim;
        setItemClaim(null);
    }
    public void setDuelExperienceByOpponent(){
        // If opponent has different level, experience is calculated by level difference
        var levelDifference = this.duel.getOpponent(this).getLevel()/level;
        // Calculate experience by current level
        var calculatedExperience = calculateExperienceCurrentLevel() * levelDifference;
        setExperience(getExperience()+calculatedExperience);
    }
    public void setDuelExperienceByOpponentLoose(){
        // If opponent has different level, experience is calculated by level difference
        var levelDifference = this.duel.getOpponent(this).getLevel()/level;
        // Calculate experience by current level
        var calculatedExperience = calculateExperienceCurrentLevel() * levelDifference/5;
        setExperience(calculatedExperience);
    }
    private Double calculateExperienceCurrentLevel(){
        return calculateExperiencePerLevel(this.level);
    }
    private Double calculateExperiencePerLevel(Double levelToCalculate){
        // Base case
        if(levelToCalculate==1.0){
            return 3.0;
        }
        // Recursive case
        var previousExperience = calculateExperiencePerLevel(levelToCalculate - 1);
        return previousExperience * 1.2;
    }
    public void applyDebuff(){
        if (debuff == null)
            return;
        debuff.apply(this);

    }
    public void applyDot(){
        if (debuff.isDamageOnTime())
            debuff.apply(this);
    }
    public void removeDebuff(){
        if (debuff == null)
            return;
        debuff.removeDebuffEffect(this);
        setDebuff(null);
    }
    public void rest() {
        removeDebuff();
        setHealth(getMaxHealth());
        setDead(false);
    }
    public void endCurrentDuel() {
        setDuel(null);
        rest();
    }
    public Armor getArmorClaimOrException() {
        var armorClaim = getArmorClaim();
        if (armorClaim == null)
            throw new NoClaimedArmorException();
        return armorClaim;
    }
    public void tryToLevelUp() {
        if (this.getExperience() >= this.getExperienceRequiredToNextLevel())
            this.levelUp();
    }
    /*
        GETTERS & SETTERS
     */

    public List<Duel> getPendingDuels() {
        return pendingDuels;
    }

    public void setPendingDuels(List<Duel> duelosPendientes) {
        this.pendingDuels = duelosPendientes;
    }

    public Duel getDuel() {
        return duel;
    }

    public void setDuel(Duel duelActual) {
        this.duel = duelActual;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double nivel) {
        this.level = nivel;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getCriticalStrikeChance() {
        return criticalStrikeChance;
    }

    public void setCriticalStrikeChance(Double criticalStrikeChance) {
        this.criticalStrikeChance = criticalStrikeChance;
    }

    public Double getSpellPower() {
        return spellPower;
    }

    public void setSpellPower(Double spellPower) {
        this.spellPower = spellPower;
    }

    public Double getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(Double attackPower) {
        this.attackPower = attackPower;
    }

    public Weapon getEquippedWeapon() {
        return weapon;
    }

    public void setEquippedArmor(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getEquippedArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public double getHealth() {
        return health;
    }

    public Boolean getDead() {
        return dead;
    }
    public Boolean isDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public Item getItem() {
        return item;
    }
    private Item getItemOrException(){
        if(item == null)
            throw new NoEquippedItemException();
        return getItem();
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public void setDebuff(Debuff debuff) {
        this.debuff = debuff;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getClaims() {
        return claims;
    }

    public Item getItemClaim() {
        return itemClaim;
    }

    public void setItemClaim(Item itemClaim) {
        this.itemClaim = itemClaim;
    }

    public void setClaims(Integer claims) {
        this.claims = claims;
    }

    public Weapon getWeaponClaim() {
        return weaponClaim;
    }
    public Weapon getWeaponClaimOrException() {
        if (weaponClaim == null)
            throw new NoWeaponClaimException();
        return weaponClaim;
    }

    public void setWeaponClaim(Weapon weaponClaim) {
        this.weaponClaim = weaponClaim;
    }

    public Armor getArmorClaim() {
        return armorClaim;
    }

    public void setArmorClaim(Armor armorClaim) {
        this.armorClaim = armorClaim;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getExperienceRequiredToNextLevel() {
        return experienceRequiredToNextLevel;
    }

    public void setExperienceRequiredToNextLevel(Double experienceRequiredToNextLevel) {
        this.experienceRequiredToNextLevel = experienceRequiredToNextLevel;
    }

    //Optional
    public Optional<Weapon> getEquippedWeaponOp() {
        return Optional.ofNullable(weapon);
    }
    public Optional<Armor> getEquippedArmorOp() {
        return Optional.ofNullable(armor);
    }
    public Optional<Duel> getDuelOp() {
        return Optional.ofNullable(duel);
    }
    public Optional<Item> getItemOp() {
        return Optional.ofNullable(item);
    }
    public Optional<Debuff> getDebuffOp() {
        return Optional.ofNullable(debuff);
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public Weapon getWeaponOrException() {
        if (weapon == null)
            throw new NoWeaponEquippedException();
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    public void setClaimArmor(Armor armor){
        this.armorClaim = armor;
    }
    public void setClaimWeapon(Weapon weapon){
        this.weaponClaim = weapon;
    }

}
