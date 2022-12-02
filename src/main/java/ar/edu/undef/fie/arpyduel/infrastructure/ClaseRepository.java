package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaseRepository extends JpaRepository<Clase, Long> {
}
