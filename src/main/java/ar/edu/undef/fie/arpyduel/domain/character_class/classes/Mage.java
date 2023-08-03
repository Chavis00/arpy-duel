package ar.edu.undef.fie.arpyduel.domain.character_class.classes;


import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import jakarta.persistence.*;

@Entity
public class Mage extends CharacterClass {
    public Mage(String name) {
        super(name);
    }

    public Mage() {
    }

    @Override
    public Double getHealthPercentageIncreasePerLevel() {
        return 0.245;
    }

    @Override
    public Double getAttackPercentagePowerIncreasePerLevel() {
        return 0.0;
    }

    @Override
    public Double getCriticalStrikeChancePercentageIncreasePerLevel() {
        return 0.005;
    }

    @Override
    public Double getSpellPowerPercentageIncreasePerLevel() {
        return 1.27;
    }
}
