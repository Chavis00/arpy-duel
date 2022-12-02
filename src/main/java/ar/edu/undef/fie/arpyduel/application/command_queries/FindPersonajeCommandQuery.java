package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.infrastructure.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindPersonajeCommandQuery {
    private final PersonajeRepository repository;

    public FindPersonajeCommandQuery(PersonajeRepository repository) {
        this.repository = repository;
    }

    public List<Personaje> getAll(){
        return repository.findAll();
    }
    public Optional<Personaje> get(Long id){
        return repository.findById(id);
    }
}
