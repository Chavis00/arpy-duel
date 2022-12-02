package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.infrastructure.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class FindItemCommandQueryt {
    private final ItemRepository repository;

    public FindItemCommandQueryt(ItemRepository repository) {
        this.repository = repository;
    }

    public Long count(){
        return repository.count();
    }
}
