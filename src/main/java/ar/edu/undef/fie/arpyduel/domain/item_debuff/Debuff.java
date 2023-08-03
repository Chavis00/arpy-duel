package ar.edu.undef.fie.arpyduel.domain.item_debuff;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.interfaces.responses.DebuffResponse;

import jakarta.persistence.*;


@Entity
@Table(name = "debuffs")
public abstract class Debuff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String description;


    public Debuff(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Debuff() {
    }

    public DebuffResponse response(){
        return new DebuffResponse(id, name, description);
    }

    public abstract void applyDebuffEffect(Character character);
    public abstract void removeDebuffEffect(Character character);
    public abstract Boolean isDamageOnTime();

    public void apply(Character character){
        applyDebuffEffect(character);
    }

    /*
        GETTERS & SETTERS
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
