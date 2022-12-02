package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmaTipoRepository extends JpaRepository<ArmaTipo, Long> {
}
