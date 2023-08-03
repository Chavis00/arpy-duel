package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import ar.edu.undef.fie.arpyduel.infrastructure.DebuffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebuffCommandService {
    private final DebuffRepository repository;

    public DebuffCommandService(DebuffRepository repository) {
        this.repository = repository;
    }

    public List<Debuff> createAll(List<Debuff> debuffs){
        return repository.saveAll(debuffs);
    }

}
