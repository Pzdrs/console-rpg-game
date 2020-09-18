package me.petr.rpg_game;

import me.petr.rpg_game.items.consumables.*;

public class Main {
    private static Game game;

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.addItem(150, new ConsumableManaPotionSmall(), 1);
        shop.addItem(250, new ConsumableManaPotionBig(), 1);
        shop.addItem(50, new ConsumableHealingPotionSmall(), 1);
        shop.addItem(100, new ConsumableHealingPotionBig(), 1);
        shop.addItem(300, new ConsumableStrengthPotion(), 1);
        shop.addItem(500, new ConsumableGoldenApple(), 1);

        game = new Game(shop);
        game.start();
    }
}
