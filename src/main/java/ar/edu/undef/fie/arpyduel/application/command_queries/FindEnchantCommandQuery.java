package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import ar.edu.undef.fie.arpyduel.infrastructure.EnchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindEnchantCommandQuery {
    final private EnchantRepository repository;

    public FindEnchantCommandQuery(EnchantRepository repository) {
        this.repository = repository;
    }

    public long countEnchants() {
        return repository.count();
    }
    public List<Enchant> getAll() {
        return repository.findAll();
    }
}
