package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import ar.edu.undef.fie.arpyduel.infrastructure.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassCommandService {
    private final ClassRepository repository;

    public ClassCommandService(ClassRepository repository) {
        this.repository = repository;
    }

    public void createAll(List<CharacterClass> characterClasses){
        repository.saveAll(characterClasses);
    }

    public CharacterClass create(CharacterClass characterClass) {
        return repository.save(characterClass);
    }
}
