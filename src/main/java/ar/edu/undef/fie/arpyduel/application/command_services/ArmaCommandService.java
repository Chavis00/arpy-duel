package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaRepository;
import org.springframework.stereotype.Service;

@Service
public class ArmaCommandService {
    private final ArmaRepository repository;

    public ArmaCommandService(ArmaRepository repository) {
        this.repository = repository;
    }
    public Arma save(Arma arma){
        return repository.save(arma);
    }

    public void delete(Arma arma) {
         repository.delete(arma);
    }
}
