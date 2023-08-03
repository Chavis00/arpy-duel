package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.duel.Duel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuelRepository extends JpaRepository<Duel, Long> {
}
