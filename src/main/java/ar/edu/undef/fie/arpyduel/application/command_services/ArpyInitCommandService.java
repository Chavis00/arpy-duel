package ar.edu.undef.fie.arpyduel.application.command_services;

import ar.edu.undef.fie.arpyduel.application.command_queries.*;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.ArmaTipo;
import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Basico;
import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.DobleJugada;
import ar.edu.undef.fie.arpyduel.domain.arma.encantamientos.Encantamiento;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.Arco;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.Baston;
import ar.edu.undef.fie.arpyduel.domain.arma.tipo.Espada;
import ar.edu.undef.fie.arpyduel.domain.clases.Cazador;
import ar.edu.undef.fie.arpyduel.domain.clases.Clase;
import ar.edu.undef.fie.arpyduel.domain.clases.Guerrero;
import ar.edu.undef.fie.arpyduel.domain.clases.Mago;
import ar.edu.undef.fie.arpyduel.domain.estado.Confuso;
import ar.edu.undef.fie.arpyduel.domain.estado.ErrorDePunteria;
import ar.edu.undef.fie.arpyduel.domain.estado.Estado;
import ar.edu.undef.fie.arpyduel.domain.estado.QuemarMagia;
import ar.edu.undef.fie.arpyduel.domain.items.efectosItem.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArpyInitCommandService implements CommandLineRunner {
    private final FindEncantamientoCommandQuery findEncantamiento;
    private final EncantamietoCommandService saveEncantamiento;
    private final FindClaseCommandQuery findClase;
    private final ClaseCommandService saveClase;
    private final FindEstadoCommandQuery findEstado;
    private final EstadoCommandService saveEstado;
    private final FindItemEfectoCommandQuery findItem;
    private final ItemEfectoCommandService saveItem;
    private final FindArmaTipoCommandQuery findArmaTipo;
    private final ArmaTipoCommandService saveArmaTipo;




    public ArpyInitCommandService(FindEncantamientoCommandQuery findEncantamiento, EncantamietoCommandService saveEncantamiento, FindClaseCommandQuery findClase, ClaseCommandService saveClase, FindEstadoCommandQuery findEstado, EstadoCommandService saveEstado, FindItemEfectoCommandQuery findItem, ItemEfectoCommandService saveItem, FindArmaTipoCommandQuery findArmaTipo, ArmaTipoCommandService saveArmaTipo) {
        this.findEncantamiento = findEncantamiento;
        this.saveEncantamiento = saveEncantamiento;
        this.findClase = findClase;
        this.saveClase = saveClase;
        this.findEstado = findEstado;
        this.saveEstado = saveEstado;
        this.findItem = findItem;
        this.saveItem = saveItem;
        this.findArmaTipo = findArmaTipo;
        this.saveArmaTipo = saveArmaTipo;
    }


    @Override
    public void run(String... args) throws Exception {
        if (findEncantamiento.countEncantamientos() == 0){
            //INIT de Encantamientos
            List<Encantamiento> encantamientosInitList = new ArrayList<Encantamiento>();
            encantamientosInitList.add(new Basico("Basico"));
            encantamientosInitList.add(new DobleJugada("Doble Jugada"));

            saveEncantamiento.createAll(encantamientosInitList);
        }

        if (findClase.countClases() == 0){
            //INIT de Clases
            List<Clase> clasesInitList = new ArrayList<Clase>();
            clasesInitList.add(new Cazador("Cazador"));
            clasesInitList.add(new Guerrero("Guerrero"));
            clasesInitList.add(new Mago("Mago"));

            saveClase.createAll(clasesInitList);
        }

        if (findEstado.countEstado() == 0){
            //INIT de Estados
            List<Estado> estadosInitList = new ArrayList<Estado>();
            estadosInitList.add(new Confuso());
            estadosInitList.add(new ErrorDePunteria());
            estadosInitList.add(new QuemarMagia());

            saveEstado.createAll(estadosInitList);
        }

        if (findItem.count() == 0){
            //INIT de Items
            List<ItemEfecto> ItemInitList = new ArrayList<ItemEfecto>();
            ItemInitList.add(new AplicarQuemarMagia());
            ItemInitList.add(new AplicarConfusion());
            ItemInitList.add(new AplicarErrorDePunteria());
            ItemInitList.add(new Cura());

            saveItem.createAll(ItemInitList);
        }

        if (findArmaTipo.countArmaTipos() == 0){

            List<ArmaTipo> armaTiposInitList = new ArrayList<ArmaTipo>();
            armaTiposInitList.add(new Arco());
            armaTiposInitList.add(new Espada());
            armaTiposInitList.add(new Baston());

            saveArmaTipo.createAll(armaTiposInitList);
        }

    }

}
