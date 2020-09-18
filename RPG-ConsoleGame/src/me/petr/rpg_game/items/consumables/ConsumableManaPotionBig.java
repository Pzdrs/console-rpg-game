package me.petr.rpg_game.items.consumables;

import me.petr.rpg_game.items.Item;

public class ConsumableManaPotionBig extends Item implements Consumable {
    public ConsumableManaPotionBig() {
        super("consumable:potion_of_mana_big", "Big Mana potion");
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public int getMana() {
        return 50;
    }

    @Override
    public double getDamageModifier() {
        return 0;
    }
}
