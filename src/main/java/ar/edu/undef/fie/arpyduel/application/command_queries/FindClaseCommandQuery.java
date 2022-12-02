package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.infrastructure.ClaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindClaseCommandQuery {
    private final ClaseRepository repository;

    public FindClaseCommandQuery(ClaseRepository repository) {
        this.repository = repository;
    }

    public long countClases() {
        return repository.count();
    }

    public Optional<Clase> get(Long id){
        return repository.findById(id);
    }
    public List<Clase> getAll(){
        return repository.findAll();
    }
}
