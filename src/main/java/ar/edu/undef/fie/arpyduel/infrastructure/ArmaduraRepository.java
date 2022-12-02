package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmaduraRepository extends JpaRepository<Armadura, Long> {
}
