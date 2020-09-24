package me.petr.rpg_game;

import me.petr.rpg_game.items.Item;
import me.petr.rpg_game.items.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

    public void show(int coins) {
        System.out.println("+---------------------------------------------------------------->>");
        System.out.println("Hey there, mighty warrior... Check out what I have in stock.");
        System.out.println("You have {coins} coins in your purse."
                .replace("{coins}", String.valueOf(coins)));
        System.out.println();
        int index = 0;
        for (Map.Entry<Integer, ItemStack> item : stock.entrySet()) {
            System.out.println("[{index}]  ".replace("{index}", String.valueOf(index)) + item.getKey() + " Coins for " + item.getValue().getAmount() + "x " + item.getValue().getItem().getName());
            index++;
        }
        System.out.println("+---------------------------------------------------------------->>");
    }

    public int size() {
        return stock.size();
    }
}
