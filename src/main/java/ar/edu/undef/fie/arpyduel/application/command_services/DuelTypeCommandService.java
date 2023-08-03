package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import ar.edu.undef.fie.arpyduel.infrastructure.DuelTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuelTypeCommandService {
    private final DuelTypeRepository repository;

    public DuelTypeCommandService(DuelTypeRepository repository) {
        this.repository = repository;
    }

    public void createAll(List<DuelType> duelTypes) {
        repository.saveAll(duelTypes);
    }
}
