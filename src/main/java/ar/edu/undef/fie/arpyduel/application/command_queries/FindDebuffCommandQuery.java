package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import ar.edu.undef.fie.arpyduel.infrastructure.DebuffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindDebuffCommandQuery {
    private final DebuffRepository repository;

    public FindDebuffCommandQuery(DebuffRepository repository) {
        this.repository = repository;
    }

    public long countDebuffs() {
        return repository.count();
    }

    public List<Debuff> getAll() {
        return repository.findAll();
    }
}
