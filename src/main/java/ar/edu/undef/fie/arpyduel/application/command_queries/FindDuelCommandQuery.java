package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.duel.Duel;
import ar.edu.undef.fie.arpyduel.exception.notfound.DuelNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.DuelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindDuelCommandQuery {
    private final DuelRepository repository;

    public FindDuelCommandQuery(DuelRepository repository) {
        this.repository = repository;
    }
    public Duel getById(Long id){
        return repository.findById(id).orElseThrow(()-> new DuelNotFoundException(id));
    }

}
