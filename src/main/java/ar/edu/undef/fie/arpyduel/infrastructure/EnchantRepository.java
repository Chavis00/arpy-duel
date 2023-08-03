package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnchantRepository extends JpaRepository<Enchant, Long> {
}
