package ar.edu.undef.fie.arpyduel.interfaces.responses.interactions;

import ar.edu.undef.fie.arpyduel.interfaces.responses.ItemResponse;

public class ItemInteractionResponse {
    private final String tittle = "Item Use";
    private final String description = "Use equipped Item";
    private final String url = "/api/v1/characters/{characterId}/attack/item";
    private final Boolean canBeUsed;
    private final String message;
    private final ItemResponse item;


    public ItemInteractionResponse(ItemResponse item, Boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
        if (canBeUsed) {
            this.message = "You can use this item just once per duel";
        } else {
            this.message = "You don't have an item equipped - You are not in a Duel - You have already used it";
        }
        this.item = item;
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

    public Boolean getCanBeUsed() {
        return canBeUsed;
    }
    public String getMessage() {
        return message;
    }
    public ItemResponse getItem() {
        return item;
    }
}
