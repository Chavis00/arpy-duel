package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.items.Item;
import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.ItemEfecto;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.infrastructure.ItemEfectoRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.ItemRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ItemCommandService {
    private final ItemRepository repository;
    private final ItemEfectoRepository itemEfectoRepository;
    private final PersonajeRepository personajeRepository;



    public ItemCommandService(ItemRepository repository, ItemEfectoRepository itemEfectoRepository, PersonajeRepository personajeRepository) {
        this.repository = repository;
        this.itemEfectoRepository = itemEfectoRepository;
        this.personajeRepository = personajeRepository;
    }

    public void save(Item item){
        this.repository.save(item);
    }

    public Item create(Personaje personaje) {
        if (personaje.claimsVacios())
            throw new RuntimeException("No tenes mas Claims");

        Random rn = new Random();
        List<ItemEfecto> efectosExistentes = itemEfectoRepository.
                findAll();
        ItemEfecto itemEfectoRandom = efectosExistentes.
                get(
                        rn.
                                nextInt(
                                        (int) itemEfectoRepository.count()
                                )
                );
        Item item = new Item(itemEfectoRandom);
        repository.save(item);
        personaje.setItemClaim(item);
        personaje.setClaims(personaje.getClaims()-1);
        personajeRepository.save(personaje);
        return item;
    }
}
