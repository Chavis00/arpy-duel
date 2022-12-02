package ar.edu.undef.fie.arpyduel.infrastructure;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
}
