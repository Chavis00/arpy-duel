package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.ItemEfecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEfectoRepository extends JpaRepository<ItemEfecto, Long> {
}
