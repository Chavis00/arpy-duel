package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaTipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmaTipoCommandService {
    private final ArmaTipoRepository repository;

    public ArmaTipoCommandService(ArmaTipoRepository repository) {
        this.repository = repository;
    }

    public List<ArmaTipo> createAll(List<ArmaTipo> tipos){
        return repository.saveAll(tipos);
    }
    public ArmaTipo save(ArmaTipo tipo){
        return repository.save(tipo);
    }

}
