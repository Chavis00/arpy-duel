package ar.edu.undef.fie.arpyduel.application.command_queries;

import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaTipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindArmaTipoCommandQuery {
    private final ArmaTipoRepository repository;

    public FindArmaTipoCommandQuery(ArmaTipoRepository repository) {
        this.repository = repository;
    }

    public long countArmaTipos() {
        return repository.count();
    }
    public List<ArmaTipo> getAll(){
        return repository.findAll();
    }

    public Optional<ArmaTipo> get(long id){
        return repository.findById(id);
    }

}
