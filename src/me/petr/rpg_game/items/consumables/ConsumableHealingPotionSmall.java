package me.petr.rpg_game.items.consumables;

import me.petr.rpg_game.items.Item;

public class ConsumableHealingPotionSmall extends Item implements Consumable {
    public ConsumableHealingPotionSmall() {
        super("consumable:potion_of_healing_small", "Small Healing potion");
    }

    @Override
    public double getHealth() {
        return 25;
    }

    @Override
    public int getMana() {
        return 0;
    }

    @Override
    public double getDamageModifier() {
        return 0;
    }
}
