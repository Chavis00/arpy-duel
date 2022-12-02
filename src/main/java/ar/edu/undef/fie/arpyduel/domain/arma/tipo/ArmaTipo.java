package ar.edu.undef.fie.arpyduel.domain.arma.tipo;


import ar.edu.undef.fie.arpyduel.domain.personaje.Personaje;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ArmaTipoResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class ArmaTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;

    public ArmaTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArmaTipo() {

    }
    public ArmaTipoResponse response(){
        return new ArmaTipoResponse(id, tipo);
    }

    public abstract double efectoArma(Personaje me, Personaje adversario);
    public abstract void nombreArma();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
