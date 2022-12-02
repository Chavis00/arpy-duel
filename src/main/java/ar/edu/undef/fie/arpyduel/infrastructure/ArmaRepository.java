package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmaRepository extends JpaRepository<Arma, Long> {
}
