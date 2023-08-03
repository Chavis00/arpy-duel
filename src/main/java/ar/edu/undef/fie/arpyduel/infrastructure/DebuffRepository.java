package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebuffRepository extends JpaRepository<Debuff, Long> {
}
