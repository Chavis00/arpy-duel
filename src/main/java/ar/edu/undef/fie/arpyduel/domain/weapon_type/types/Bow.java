package ar.edu.undef.fie.arpyduel.domain.weapon_type.types;

import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import jakarta.persistence.*;

@Entity
public class Bow extends WeaponType {

    public Bow() {
        super("Bow");
    }


    @Override
    public double calculateWeaponDmgBonus() {
        return 1.2;
    }

    @Override
    public double calculateWeaponCriticalStrikeBonus() {
        return 3.0;
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
