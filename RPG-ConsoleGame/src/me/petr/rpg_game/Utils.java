package me.petr.rpg_game;

import me.petr.rpg_game.items.Item;
import me.petr.rpg_game.items.consumables.Consumable;

import java.util.Scanner;

public class Utils {
    private static Game game = Game.getInstance();

    public static Item itemSelectionPrompt(Scanner scanner) {
        Item toReturn = null;
        System.out.println("+--------------------------->>");
        for (int i = 0; i < game.getInventory().size(); i++) {
            System.out.println("[{index}]  ".replace("{index}", String.valueOf(i)) + game.getInventory().get(i).getAmount() + "x " + game.getInventory().get(i).getItem().getName());
        }
        System.out.println("+--------------------------->>");
        while (toReturn == null) {
            System.out.print("What item to consume > ");
            String input = scanner.nextLine();
            try {
                int item = Integer.parseInt(input);
                if (item <= game.getInventory().size() - 1 && item >= 0) {
                    if (game.getInventory().get(item).getItem() instanceof Consumable) {
                        toReturn = game.getInventory().get(item).getItem();
                    } else {
                        System.out.println("This item isn't consumable.");
                    }
                } else {
                    System.out.println("Invalid item.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Use numbers to specify what item to consume.");
            }
        }
        return toReturn;
    }

    public static boolean consume(Item item) {
        // Make sure that the item can be used
        if (!(item instanceof Consumable)) {
            System.out.println("This item isn't consumable.");
            return false;
        }
        System.out.println(" -> " + item.getName() + " used.");
        Consumable consumable = (Consumable) item;
        // Handle health
        if (consumable.getHealth() != 0d) {
            if (game.getHealth() + consumable.getHealth() <= game.getHealthCap())
                game.setHealth(game.getHealth() + consumable.getHealth());
            else game.setHealth(game.getHealthCap());
            System.out.println("   +{healthAdded}HP (Current HP: {health})"
                    .replace("{health}", String.valueOf(game.getHealth()))
                    .replace("{healthAdded}", String.valueOf(consumable.getHealth())));
        }
        // Handle mana
        if (consumable.getMana() != 0) {
            if (game.getMana() + consumable.getMana() <= game.getManaCap())
                game.setMana(game.getMana() + consumable.getMana());
            else game.setMana(game.getManaCap());
            System.out.println("   +{manaAdded} Mana (Current Mana: {mana})"
                    .replace("{mana}", String.valueOf(game.getMana()))
                    .replace("{manaAdded}", String.valueOf(consumable.getMana())));
        }
        //Handle damage modifier
        if (consumable.getDamageModifier() != 0) {
            game.setDamageModifier(game.getDamageModifier() + game.getDamageModifier() * consumable.getDamageModifier());
            System.out.println("   +{damageModifierAdded}% (Current Damage modifier: {damageModifier})"
                    .replace("{damageModifier}", String.valueOf(game.getDamageModifier()))
                    .replace("{damageModifierAdded}", String.valueOf(consumable.getDamageModifier() * 100)));
        }
        return true;
    }

    public static void inventory() {
        System.out.println("+--------------------------->>");
        game.getInventory().forEach(itemStack -> System.out.println("|" + itemStack.getAmount() + "x " + itemStack.getItem().getName()));
        System.out.println("+--------------------------->>");
    }
}
