package ar.edu.undef.fie.arpyduel.domain.weapon_type.types;


import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import jakarta.persistence.*;

@Entity
public class Sword extends WeaponType {
    public Sword() {
        super("Sword");
    }

    @Override
    public double calculateWeaponDmgBonus() {
        return 1.25;
    }

    @Override
    public double calculateWeaponCriticalStrikeBonus() {
        return 2.0;
    }

    @Override
    public boolean hasWeaponMultipleHit() {
        return false;
    }

    @Override
    public void weaponName() {
        return;
    }
}
