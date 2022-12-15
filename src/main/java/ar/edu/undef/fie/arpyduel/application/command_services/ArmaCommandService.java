package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.domain.arma.Arma;
import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;
import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.infrastructure.ArmaRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.EncantamientoRepository;
import ar.edu.undef.fie.arpyduel.infrastructure.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ArmaCommandService {
    private final ArmaRepository repository;
    private final EncantamientoRepository encantamientoRepository;
    private final PersonajeRepository personajeRepository;

    public ArmaCommandService(ArmaRepository repository, EncantamientoRepository encantamientoRepository, PersonajeRepository personajeRepository) {
        this.repository = repository;
        this.encantamientoRepository = encantamientoRepository;
        this.personajeRepository = personajeRepository;
    }
    public Arma save(Arma arma){
        return repository.save(arma);
    }

    public void delete(Arma arma) {
         repository.delete(arma);
    }

    public Arma generarClaim(Personaje personaje, ArmaTipo tipo) {
        if (personaje.claimsVacios())
            throw new RuntimeException("No tenes mas Claims");
        Arma arma = new Arma(tipo);
        Random rn = new Random();

        arma.construirArma(personaje.getNivel());

        List<Encantamiento> encantamientosDisponibles = encantamientoRepository.findAll();
        Encantamiento encantamientoRandom = encantamientosDisponibles.
                get(
                        rn.
                                nextInt(
                                        (int) encantamientoRepository.count()
                                )
                );
        arma.setEncantamiento(encantamientoRandom);

        personaje.setClaims(personaje.getClaims()-1);
        personaje.claimArma(arma);
        repository.save(arma);
        personajeRepository.save(personaje);
        return arma;
    }
}
