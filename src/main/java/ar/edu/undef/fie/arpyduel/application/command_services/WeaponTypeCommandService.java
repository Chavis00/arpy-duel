package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.weapon_type.WeaponType;
import ar.edu.undef.fie.arpyduel.infrastructure.WeaponTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeaponTypeCommandService {
    private final WeaponTypeRepository repository;

    public WeaponTypeCommandService(WeaponTypeRepository repository) {
        this.repository = repository;
    }

    public List<WeaponType> createAll(List<WeaponType> weaponTypes){
        return repository.saveAll(weaponTypes);
    }
    public WeaponType save(WeaponType weaponType){
        return repository.save(weaponType);
    }

}
