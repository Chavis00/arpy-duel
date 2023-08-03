package ar.edu.undef.fie.arpyduel.interfaces.responses.interactions;

public class SurrenderInteractionResponse {
    private final String tittle = "Surrender";
    private final String description = "Surrender current Duel";
    private final String url = "/api/v1/duels/{duelId}/surrender";
    private final String message;

    private final Boolean canBeUsed;

    public SurrenderInteractionResponse(Boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
        if (canBeUsed) {
            this.message = "You can surrender just once per duel";
        } else {
            this.message = "You are not in a Duel";
        }
    }


    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
    public String getMessage() {
        return message;
    }
    public Boolean getCanBeUsed() {
        return canBeUsed;
    }
}
