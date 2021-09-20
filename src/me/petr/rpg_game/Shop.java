package me.petr.rpg_game;

import me.petr.rpg_game.items.Item;
import me.petr.rpg_game.items.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop {
    private Game game;
    private LinkedHashMap<Integer, ItemStack> stock;

    public Shop(Game game) {
        this(game, new LinkedHashMap<>());
    }

    public Shop(Game game, LinkedHashMap<Integer, ItemStack> stock) {
        this.game = game;
        this.stock = stock;
    }

    public HashMap<Integer, ItemStack> getStock() {
        return stock;
    }

    public void addItem(int price, Item item, int amount) {
        stock.put(price, new ItemStack(amount, item));
    }

    public void open(Scanner scanner) {
        System.out.println("+---------------------------------------------------------------->>");
        System.out.println("Hey there, mighty warrior... Check out what I have in stock.");
        System.out.println("You have {coins} coins in your purse."
                .replace("{coins}", String.valueOf(game.getCoins())));
        System.out.println();
        int index = 0;
        for (Map.Entry<Integer, ItemStack> item : stock.entrySet()) {
            System.out.println("[{index}]  ".replace("{index}", String.valueOf(index)) + item.getKey() + " Coins for " + item.getValue().getAmount() + "x " + item.getValue().getItem().getName());
            index++;
        }
        System.out.println("+---------------------------------------------------------------->>");
        // Prompt
        while (true) {
            System.out.print("What item to buy > ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) return;
            try {
                int item = Integer.parseInt(input);
                // Check if valid item
                if (item <= game.getShop().size() - 1 && item >= 0) {
                    ItemStack itemStack = (ItemStack) game.getShop().getStock().values().toArray()[item];
                    int price = (int) game.getShop().getStock().keySet().toArray()[item];
                    // Enough coins?
                    if (game.getCoins() >= price) {
                        game.setCoins(game.getCoins() - price);
                        System.out.println("You purchased {amount}x {item} for {coins} coins. You now have {currentCoins} coins."
                                .replace("{amount}", String.valueOf(itemStack.getAmount()))
                                .replace("{item}", itemStack.getItem().getName())
                                .replace("{coins}", String.valueOf(price))
                                .replace("{currentCoins}", String.valueOf(game.getCoins())));
                        Utils.addToInventory(itemStack);
                    } else {
                        System.out.println("You do not posses the required amount of coins.");
                    }
                } else {
                    System.out.println("Invalid item.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Use numbers to specify what item to consume.");
            }
        }
    }

    public int size() {
        return stock.size();
    }
}
