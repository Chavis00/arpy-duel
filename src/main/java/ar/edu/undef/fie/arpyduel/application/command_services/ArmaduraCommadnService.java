package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.armadura.Armadura;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaduraRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.PersonajeRepository;
import org.springframework.stereotype.Service;

@Service
public class ArmaduraCommadnService {
    private final ArmaduraRepository repository;
    private final PersonajeRepository personajeRepository;

    public ArmaduraCommadnService(ArmaduraRepository repository, PersonajeRepository personajeRepository) {
        this.repository = repository;
        this.personajeRepository = personajeRepository;
    }

    public Armadura save(Armadura armadura){
        return repository.save(armadura);
    }

    public Armadura generarClaim(Personaje personaje) {
        if (personaje.claimsVacios())
            throw new RuntimeException("No tenes mas Claims");
        Armadura armadura = new Armadura();
        armadura.construirArmadura(personaje.getNivel());
        repository.save(armadura);
        personaje.setClaims(personaje.getClaims()-1);
        personaje.claimArmadura(armadura);
        personajeRepository.save(personaje);
        return armadura;
    }
}
