package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.duel_queue.DuelQueue;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuelQueueRepository extends JpaRepository<DuelQueue, Long> {
    List<DuelQueue> findByType(DuelEnum type);
}
