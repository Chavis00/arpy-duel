package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.ItemEfecto;
import ar.edu.undef.fie.arpyduel.infrastructure.ItemEfectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemEfectoCommandService {
    private final ItemEfectoRepository repository;

    public ItemEfectoCommandService(ItemEfectoRepository repository) {
        this.repository = repository;
    }

    public List<ItemEfecto> createAll(List<ItemEfecto> items){
        return repository.saveAll(items);
    }

}
