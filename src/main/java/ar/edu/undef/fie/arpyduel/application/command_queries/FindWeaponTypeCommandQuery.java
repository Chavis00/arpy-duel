package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import ar.edu.undef.fie.arpyduel.exception.notfound.WeaponTypeNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.WeaponTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindWeaponTypeCommandQuery {
    private final WeaponTypeRepository repository;

    public FindWeaponTypeCommandQuery(WeaponTypeRepository repository) {
        this.repository = repository;
    }

    public long countWeaponTypes() {
        return repository.count();
    }
    public List<WeaponType> getAll(){
        return repository.findAll();
    }

    public WeaponType get(Long id){
        return repository.findById(id).orElseThrow(()-> new WeaponTypeNotFoundException(id));
    }

}
