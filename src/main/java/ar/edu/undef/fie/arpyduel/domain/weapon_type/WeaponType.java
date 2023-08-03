package ar.edu.undef.fie.arpyduel.domain.weapon_type;


import ar.edu.undef.fie.arpyduel.interfaces.responses.WeaponTypeResponse;

import jakarta.persistence.*;


@Entity
@Table(name = "weapon_types")
public abstract class WeaponType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    public WeaponType(String type) {
        this.type = type;
    }

    public WeaponType() {

    }
    public WeaponTypeResponse response(){
        return new WeaponTypeResponse(id, type);
    }

    public abstract double calculateWeaponDmgBonus();
    public abstract double calculateWeaponCriticalStrikeBonus();
    public abstract boolean hasWeaponMultipleHit();

    public abstract void weaponName();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
