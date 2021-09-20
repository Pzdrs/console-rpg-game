package me.petr.rpg_game.items.consumables;

import me.petr.rpg_game.items.Item;

public class ConsumableHealingPotionBig extends Item implements Consumable {
    public ConsumableHealingPotionBig() {
        super("consumable:potion_of_healing_big", "Big Healing potion");
    }

    @Override
    public double getHealth() {
        return 50;
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
