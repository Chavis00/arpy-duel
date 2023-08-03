package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.item.Item;
import ar.edu.undef.fie.arpyduel.exception.notfound.ItemNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class FindItemCommandQuery {
    private final ItemRepository repository;

    public FindItemCommandQuery(ItemRepository repository) {
        this.repository = repository;
    }

    public Long count(){
        return repository.count();
    }

    public Item get(Long id){
        return repository.findById(id).orElseThrow(()-> new ItemNotFoundException(id));
    }
}
