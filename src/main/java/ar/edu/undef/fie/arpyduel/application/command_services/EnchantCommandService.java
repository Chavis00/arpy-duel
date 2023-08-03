package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.weapon_enchants.Enchant;
import ar.edu.undef.fie.arpyduel.infrastructure.EnchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchantCommandService {

    private final EnchantRepository repository;


    public EnchantCommandService(EnchantRepository repository) {
        this.repository = repository;
    }

    public List<Enchant> createAll(List<Enchant> enchants){
        return repository.saveAll(enchants);
    }
}
