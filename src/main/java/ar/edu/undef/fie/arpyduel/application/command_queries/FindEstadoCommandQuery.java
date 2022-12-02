package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.infrastructure.EstadoRepository;
import org.springframework.stereotype.Service;

@Service
public class FindEstadoCommandQuery {
    private final EstadoRepository repository;

    public FindEstadoCommandQuery(EstadoRepository repository) {
        this.repository = repository;
    }

    public long countEstado() {
        return repository.count();
    }

}
