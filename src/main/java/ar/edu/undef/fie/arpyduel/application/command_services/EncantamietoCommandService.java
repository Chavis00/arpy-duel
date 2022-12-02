package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.infrastructure.EncantamientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncantamietoCommandService {

    private final EncantamientoRepository repository;


    public EncantamietoCommandService(EncantamientoRepository repository) {
        this.repository = repository;
    }

    public List<Encantamiento> createAll(List<Encantamiento> encantamientos){
        return repository.saveAll(encantamientos);
    }
}
