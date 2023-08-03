package ar.edu.undef.fie.arpyduel.interfaces.responses.interactions;

import ar.edu.undef.fie.arpyduel.interfaces.responses.WeaponResponse;

public class WeaponAttackInteractionResponse {
    private final String tittle = "Weapon Attack";
    private final String description = "Attack with equipped weapon";
    private final String url = "/api/v1/characters/{characterId}/attack/weapon?enchant={boolean}";
    private final Boolean canBeUsed;
    private final String message;
    private final WeaponResponse weapon;

    public WeaponAttackInteractionResponse(WeaponResponse weapon, Boolean canBeUsed) {
        this.weapon = weapon;
        this.canBeUsed = canBeUsed;
        if (canBeUsed) {
            this.message = "Every weapon deal damage in a different way, you can choose to attack with your weapon normally or with an enchantment";
        } else {
            this.message = "You are not in a Duel - You don't have a weapon equipped";
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

    public Boolean getCanBeUsed() {
        return canBeUsed;
    }

    public String getMessage() {
        return message;
    }

    public WeaponResponse getWeapon() {
        return weapon;
    }

}
