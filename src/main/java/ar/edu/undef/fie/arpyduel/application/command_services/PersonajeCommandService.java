package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.infrastructure.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeCommandService {
    private final PersonajeRepository repository;

    public PersonajeCommandService(PersonajeRepository repository) {
        this.repository = repository;
    }

    public Personaje save(Personaje personaje){
        return repository.save(personaje);
    }
    public List<Personaje> saveAll(List<Personaje> personajes){
        return repository.saveAll(personajes);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
}
