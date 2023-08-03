package ar.edu.undef.fie.arpyduel.interfaces.responses.interactions;

public class BasicAttackInteraction {
    private final String tittle = "Basic Attack";
    private final String description = "Attack with basic attack";
    private final String url = "/api/v1/characters/{characterId}/attack/basic";
    private final String message;
    private final Boolean canBeUsed;

    public BasicAttackInteraction(Boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
        if (canBeUsed) {
            this.message = "This attack scales with your Attack Power";
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
