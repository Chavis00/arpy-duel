package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.infrastructure.EncantamientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindEncantamientoCommandQuery {
    final private EncantamientoRepository repository;

    public FindEncantamientoCommandQuery(EncantamientoRepository repository) {
        this.repository = repository;
    }

    public long countEncantamientos() {
        return repository.count();
    }
    public List<Encantamiento> getAll() {
        return repository.findAll();
    }
}
