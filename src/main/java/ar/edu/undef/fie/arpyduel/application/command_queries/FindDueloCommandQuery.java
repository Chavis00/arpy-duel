package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.infrastructure.DueloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindDueloCommandQuery {
    private final DueloRepository repository;

    public FindDueloCommandQuery(DueloRepository repository) {
        this.repository = repository;
    }
    public Optional<Duelo> get(Long id){
        return repository.findById(id);
    }

}
