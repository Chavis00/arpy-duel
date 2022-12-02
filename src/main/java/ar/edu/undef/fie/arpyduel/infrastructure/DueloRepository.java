package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DueloRepository extends JpaRepository<Duelo, Long> {
}
