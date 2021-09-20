package me.petr.rpg_game.items;

public class ItemStack {
    private int amount;
    private Item item;

    public ItemStack(int amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public Item getItem() {
        return item;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public String toString() {
        return "ItemStack{" +
                "amount=" + amount +
                ", item=" + item +
                '}';
    }

    public void decreaseAmountBy(int amount) {
        this.amount -= amount;
    }
}
