package me.petr.rpg_game;

import me.petr.rpg_game.items.Item;
import me.petr.rpg_game.items.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Shop {
    private HashMap<Integer, ItemStack> stock;

    public Shop() {
        this.stock = new HashMap<>();
    }

    public Shop(HashMap<Integer, ItemStack> stock) {
        this.stock = stock;
    }

    public HashMap<Integer, ItemStack> getStock() {
        return stock;
    }

    public void addItem(int price, Item item, int amount) {
        stock.put(price, new ItemStack(amount, item));
    }

    public void show() {
        System.out.println("+---------------------------------------------------------------->>");
        int index = 0;
        for (Map.Entry<Integer, ItemStack> item : stock.entrySet()) {
            System.out.println("[{index}]  ".replace("{index}", String.valueOf(index)) + item.getKey() + " Coins for " + item.getValue().getAmount() + "x " + item.getValue().getItem().getName());
            index++;
        }
        System.out.println("+---------------------------------------------------------------->>");
    }
}
