package ar.edu.undef.fie.arpyduel.domain.weapon_type.types;

import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import jakarta.persistence.*;

@Entity
public class Staff extends WeaponType {
    public Staff() {
        super("Staff");
    }

    @Override
    public double calculateWeaponDmgBonus() {
        return 2.0;
    }

    @Override
    public double calculateWeaponCriticalStrikeBonus() {
        return 2.0;
    }

    @Override
    public boolean hasWeaponMultipleHit() {
        return true;
    }

    @Override
    public void weaponName() {

    }
}
