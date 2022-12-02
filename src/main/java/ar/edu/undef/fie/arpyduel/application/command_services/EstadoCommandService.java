package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.domain.estado.Estado;
import ar.edu.undef.fie.arpyduel.infrastructure.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCommandService {
    private final EstadoRepository repository;

    public EstadoCommandService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<Estado> createAll(List<Estado> estados){
        return repository.saveAll(estados);
    }

}
