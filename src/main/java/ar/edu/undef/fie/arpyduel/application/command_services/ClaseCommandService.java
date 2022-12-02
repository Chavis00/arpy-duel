package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.infrastructure.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseCommandService {
    private final ClaseRepository repository;

    public ClaseCommandService(ClaseRepository repository) {
        this.repository = repository;
    }

    public List<Clase> createAll(List<Clase> clases){
        return repository.saveAll(clases);
    }

    public Clase create(Clase clase) {
        return repository.save(clase);
    }
}
