package ar.edu.undef.fie.arpyduel.interfaces.responses.interactions;

public record AvailableDuelInteractionsResponse(BasicAttackInteraction basicAttack,
                                                WeaponAttackInteractionResponse weaponAttack,
                                                ItemInteractionResponse itemUse,
                                                SurrenderInteractionResponse surrender) {
}
