package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaduraRepository;
import org.springframework.stereotype.Service;

@Service
public class ArmaduraCommadnService {
    private final ArmaduraRepository repository;

    public ArmaduraCommadnService(ArmaduraRepository repository) {
        this.repository = repository;
    }

    public Armadura save(Armadura armadura){
        return repository.save(armadura);
    }
}
