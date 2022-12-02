package ar.edu.undef.fie.arpyduel.interfaces.responses;

public class ArmaduraResponse {
    private Long id;
    private Double bonusVida;
    private Double bonusCritico;
    private Double bonusPoderMagico;

    public ArmaduraResponse(Long id, Double bonusVida, Double bonusCritico, Double bonusPoderMagico) {
        this.id = id;
        this.bonusVida = bonusVida;
        this.bonusCritico = bonusCritico;
        this.bonusPoderMagico = bonusPoderMagico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getBonusVida() {
        return bonusVida;
    }

    public Double getBonusCritico() {
        return bonusCritico;
    }

    public Double getBonusPoderMagico() {
        return bonusPoderMagico;
    }
}
