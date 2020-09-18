package me.petr.rpg_game.items.consumables;

import me.petr.rpg_game.items.Item;

public class ConsumableGoldenApple extends Item implements Consumable {
    public ConsumableGoldenApple() {
        super("consumable:golden_apple", "Golden Apple");
    }

    @Override
    public double getHealth() {
        return 75;
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
