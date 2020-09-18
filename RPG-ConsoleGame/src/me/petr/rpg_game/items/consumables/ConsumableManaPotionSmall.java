package me.petr.rpg_game.items.consumables;

import me.petr.rpg_game.items.Item;

public class ConsumableManaPotionSmall extends Item implements Consumable {
    public ConsumableManaPotionSmall() {
        super("consumable:potion_of_mana_small", "Small Mana potion");
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public int getMana() {
        return 25;
    }

    @Override
    public double getDamageModifier() {
        return 0;
    }
}
