package ar.edu.undef.fie.arpyduel.interfaces.responses;

import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;

public class WeaponResponse {
    private final Long id;
    private final Double dmg;
    private final Double attackPowerBonus;
    private final Double spellPowerBonus;
    private final Double criticalStrikeChanceBonus;
    private final EnchantResponse enchant;

    public WeaponResponse(Long id, Double dmg, Double attackPowerBonus, Double spellPowerBonus,
                            Double criticalStrikeChanceBonus, EnchantResponse enchant) {
        this.id = id;
        this.dmg = dmg;
        this.attackPowerBonus = attackPowerBonus;
        this.spellPowerBonus = spellPowerBonus;
        this.criticalStrikeChanceBonus = criticalStrikeChanceBonus;
        this.enchant = enchant;
    }

    public Long getId() {
        return id;
    }

    public Double getDmg() {
        return dmg;
    }

    public Double getAttackPowerBonus() {
        return attackPowerBonus;
    }

    public Double getSpellPowerBonus() {
        return spellPowerBonus;
    }

    public Double getCriticalStrikeChanceBonus() {
        return criticalStrikeChanceBonus;
    }

    public EnchantResponse getEnchant() {
        return enchant;
    }
}
