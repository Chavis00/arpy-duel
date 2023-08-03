package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.character_class.CharacterClass;
import ar.edu.undef.fie.arpyduel.exception.notfound.ClassNotFoundExceptionById;
import ar.edu.undef.fie.arpyduel.infrastructure.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindClassCommandQuery {
    private final ClassRepository repository;

    public FindClassCommandQuery(ClassRepository repository) {
        this.repository = repository;
    }

    public long countClasses() {
        return repository.count();
    }

    public CharacterClass getById(Long id){
        return repository.findById(id).orElseThrow(()->new ClassNotFoundExceptionById(id));
    }
    public List<CharacterClass> getAll(){
        return repository.findAll();
    }
}
