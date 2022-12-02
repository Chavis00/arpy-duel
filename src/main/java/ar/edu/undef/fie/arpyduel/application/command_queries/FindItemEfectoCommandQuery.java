package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.ItemEfecto;
import ar.edu.undef.fie.arpyduel.infrastructure.ItemEfectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindItemEfectoCommandQuery {
    private final ItemEfectoRepository repository;

    public FindItemEfectoCommandQuery(ItemEfectoRepository repository) {
        this.repository = repository;
    }

    public long count() {
        return repository.count();
    }
    public List<ItemEfecto> getAll() {
        return repository.findAll();
    }
}
