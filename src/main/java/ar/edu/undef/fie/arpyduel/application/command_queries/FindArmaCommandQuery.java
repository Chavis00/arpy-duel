package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindArmaCommandQuery {
    private final ArmaRepository repository;

    public FindArmaCommandQuery(ArmaRepository repository) {
        this.repository = repository;
    }

    public Optional<Arma> get(Long id){
        return repository.findById(id);
    }
}
