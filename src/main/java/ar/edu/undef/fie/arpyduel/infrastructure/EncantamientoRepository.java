package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncantamientoRepository extends JpaRepository<Encantamiento, Long> {
}
