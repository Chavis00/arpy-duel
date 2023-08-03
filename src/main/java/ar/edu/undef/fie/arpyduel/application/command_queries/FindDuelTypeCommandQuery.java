package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelEnum;
import ar.edu.undef.fie.arpyduel.domain.duel_types.DuelType;
import ar.edu.undef.fie.arpyduel.exception.notfound.DuelTypeNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.DuelTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class FindDuelTypeCommandQuery {
    private final DuelTypeRepository repository;

    public FindDuelTypeCommandQuery(DuelTypeRepository repository) {
        this.repository = repository;
    }

    public long countWeaponTypes() {
        return repository.count();
    }
    public DuelType getByType(DuelEnum type){
        return repository.findDuelTypeByType(type).orElseThrow(()-> new DuelTypeNotFoundException(type));
    }
}
