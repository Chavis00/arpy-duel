package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.weapon.Weapon;
import ar.edu.undef.fie.arpyduel.exception.notfound.WeaponNotFoundException;
import ar.edu.undef.fie.arpyduel.infrastructure.WeaponRepository;
import org.springframework.stereotype.Service;

@Service
public class FindWeaponCommandQuery {
    private final WeaponRepository repository;

    public FindWeaponCommandQuery(WeaponRepository repository) {
        this.repository = repository;
    }

    public Weapon get(Long id){
        return repository.findById(id).orElseThrow(()-> new WeaponNotFoundException(id));
    }

    public Weapon getById(Long id) {
        return repository.findById(id).orElseThrow(()-> new WeaponNotFoundException(id));
    }
}
