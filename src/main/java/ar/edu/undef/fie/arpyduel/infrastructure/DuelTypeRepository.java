package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DuelTypeRepository extends JpaRepository<DuelType, Long> {
    Optional<DuelType> findDuelTypeByType(DuelEnum type);
}
