package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.duelo.Duelo;
import ar.edu.undef.fie.arpyduel.infrastructure.DueloRepository;
import org.springframework.stereotype.Service;

@Service
public class DueloCommandService {
    private final DueloRepository repository;

    public DueloCommandService(DueloRepository repository) {
        this.repository = repository;
    }

    public Duelo save(Duelo duelo){
        return repository.save(duelo);
    }
    public void delete(Duelo duelo){
        repository.delete(duelo);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
