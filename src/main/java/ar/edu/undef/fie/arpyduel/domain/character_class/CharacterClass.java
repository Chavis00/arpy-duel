package ar.edu.undef.fie.arpyduel.domain.character_class;

import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ClassResponse;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "class")
public abstract class CharacterClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    public ClassResponse response(){
        return new ClassResponse(id, name);
    }

    public CharacterClass(String name) {
        this.name = name;
    }

    public CharacterClass() {
    }

    public abstract Double getHealthPercentageIncreasePerLevel();
    public abstract Double getAttackPercentagePowerIncreasePerLevel();
    public abstract Double getCriticalStrikeChancePercentageIncreasePerLevel();
    public abstract Double getSpellPowerPercentageIncreasePerLevel();

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
}
