package ar.edu.undef.fie.arpyduel.domain.character_class.classes;

import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import jakarta.persistence.*;

@Entity
public class Warrior extends CharacterClass {
    public Warrior(String name) {
        super(name);
    }

    public Warrior() {
    }

    @Override
    public Double getHealthPercentageIncreasePerLevel() {
        return 0.28;
    }

    @Override
    public Double getAttackPercentagePowerIncreasePerLevel() {
        return 0.26;
    }

    @Override
    public Double getCriticalStrikeChancePercentageIncreasePerLevel() {
        return -0.05;
    }

    @Override
    public Double getSpellPowerPercentageIncreasePerLevel() {
        return 0.0;
    }
}
