package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.infrastructure.DueloRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DueloCommandService {
    private final DueloRepository repository;
    private final PersonajeRepository personajeRepository;

    public DueloCommandService(DueloRepository repository, PersonajeRepository personajeRepository) {
        this.repository = repository;
        this.personajeRepository = personajeRepository;
    }

    public Duelo save(Duelo duelo){
        return repository.save(duelo);
    }
    public void delete(Duelo duelo){
        for(Personaje personaje: duelo.getPersonajes()){
            personaje.setDueloActual(null);
            personaje.getDuelosPendientes().removeIf(duelo1 -> duelo1 == duelo);
            personajeRepository.save(personaje);
        }
        repository.delete(duelo);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Duelo> getDuelosPendientes(Personaje personaje) {
        return personaje.getDuelosPendientes();
    }
}
