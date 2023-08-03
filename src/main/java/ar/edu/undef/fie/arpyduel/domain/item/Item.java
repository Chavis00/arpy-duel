package ar.edu.undef.fie.arpyduel.domain.item;

import ar.edu.undef.fie.arpyduel.domain.item_debuff.Debuff;
import ar.edu.undef.fie.arpyduel.domain.character.Character;
import ar.edu.undef.fie.arpyduel.exception.interactions.NoEffectInItemException;
import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;

import jakarta.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Debuff debuff;
    @Column(nullable = false)
    private Boolean used;


    public Item(Debuff effect) {
        this.debuff = effect;
        this.used = false;
    }

    public Item() {

    }
    public ItemResponse response(){
        return new ItemResponse(id, used, getDebuffOp().map(Debuff::response).orElse(null));
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void especialItem(Character character) {
        var debuff = getDebuffOrException();
        character.setDebuff(debuff);
        character.applyDebuff();
    }

    private Debuff getDebuffOrException() {
        return getDebuffOp().orElseThrow(NoEffectInItemException::new);
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public void setDebuff(Debuff debuff) {
        this.debuff = debuff;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Optional<Debuff> getDebuffOp() {
        return Optional.ofNullable(debuff);
    }


}
