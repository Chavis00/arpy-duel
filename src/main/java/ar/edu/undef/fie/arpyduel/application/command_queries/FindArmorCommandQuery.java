package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.armor.Armor;
import ar.edu.undef.fie.arpyduel.exception.notfound.ArmorNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindArmorCommandQuery {
    private final ArmorRepository repository;

    public FindArmorCommandQuery(ArmorRepository repository) {
        this.repository = repository;
    }
    public Armor get(Long id) {
        return repository.findById(id).orElseThrow(()-> new ArmorNotFoundException(id));
    }
}
