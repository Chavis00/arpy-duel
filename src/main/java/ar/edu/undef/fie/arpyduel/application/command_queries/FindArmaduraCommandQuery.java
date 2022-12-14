package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaduraRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindArmaduraCommandQuery {
    private final ArmaduraRepository repository;

    public FindArmaduraCommandQuery(ArmaduraRepository repository) {
        this.repository = repository;
    }


    public Optional<Armadura> get(Long id) {
        return repository.findById(id);
    }
}
