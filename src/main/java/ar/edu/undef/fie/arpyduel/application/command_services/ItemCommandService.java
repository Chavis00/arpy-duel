package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.items.Item;
import ar.edu.undef.fie.arpyduel.infrastructure.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemCommandService {
    private final ItemRepository repository;


    public ItemCommandService(ItemRepository repository) {
        this.repository = repository;
    }

    public void save(Item item){
        this.repository.save(item);
    }
}
