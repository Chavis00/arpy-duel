package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponTypeRepository extends JpaRepository<WeaponType, Long> {
}
