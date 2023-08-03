package ar.edu.undef.fie.arpyduel.domain.character_class.classes;


import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import jakarta.persistence.*;

@Entity
public class Hunter extends CharacterClass {

    public Hunter(String name) {
        super(name);
    }

    public Hunter() {
    }

    @Override
    public Double getHealthPercentageIncreasePerLevel() {
        return 0.25;
    }

    @Override
    public Double getAttackPercentagePowerIncreasePerLevel() {
        return 0.26;
    }

    @Override
    public Double getCriticalStrikeChancePercentageIncreasePerLevel() {
        return  0.01;
    }

    @Override
    public Double getSpellPowerPercentageIncreasePerLevel() {
        return 0.0;
    }
}
