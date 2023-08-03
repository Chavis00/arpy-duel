package ar.edu.undef.fie.arpyduel.domain.weapon_enchants;


import ar.edu.undef.fie.arpyduel.domain.character.Character;

import ar.edu.undef.fie.arpyduel.interfaces.responses.EnchantResponse;
import jakarta.persistence.*;


@Entity
@Table(name = "enchants")
public abstract class Enchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Enchant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Enchant() {

    }
    public EnchantResponse response() {
        return new EnchantResponse(id, name, description);
    }

    public abstract void enchantUse(Character me, Character rival);

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
