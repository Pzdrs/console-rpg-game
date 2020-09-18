package me.petr.rpg_game.items.consumables;

import me.petr.rpg_game.items.Item;

public class ConsumableStrengthPotion extends Item implements Consumable {
    public ConsumableStrengthPotion() {
        super("consumable:potion_of_strength", "Strength potion");
    }

    @Override
    public String getDescription() {
        return "A consumable item that sets the player's damage modifier to 2.5, thus dealing more damage to enemies.";
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public int getMana() {
        return 0;
    }

    @Override
    public double getDamageModifier() {
        return .5;
    }
}
