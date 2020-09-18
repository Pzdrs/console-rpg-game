package me.petr.rpg_game;

import me.petr.rpg_game.items.ItemStack;
import me.petr.rpg_game.items.consumables.ConsumableHealingPotionBig;
import me.petr.rpg_game.items.consumables.ConsumableManaPotionBig;
import me.petr.rpg_game.items.consumables.ConsumableStrengthPotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static Game instance;
    private Scanner scanner;

    private double health, healthCap;
    private int mana, manaCap;
    private double damageModifier;
    private int coins;
    private List<ItemStack> inventory;
    private Ability[] abilities;

    private boolean gameInProgress = false;
    private Shop shop;

    public Game(Shop shop) {
        instance = this;
        this.scanner = new Scanner(System.in);

        this.health = 100;
        this.healthCap = Reference.HEALTH_CAP;
        this.mana = 100;
        this.manaCap = Reference.MANA_CAP;
        this.damageModifier = Reference.START_DAMAGE_MODIFIER;
        this.coins = Reference.START_COINS;
        this.inventory = new ArrayList<>();
        this.abilities = new Ability[5];

        this.shop = shop;

        inventory.add(new ItemStack(2, new ConsumableStrengthPotion()));
        inventory.add(new ItemStack(3, new ConsumableHealingPotionBig()));
        inventory.add(new ItemStack(1, new ConsumableManaPotionBig()));
    }

    public void start() {
        this.gameInProgress = true;
        System.out.println(Reference.WELCOME);
        while (gameInProgress) {
            System.out.print(" > ");
            processCommand(scanner.nextLine());
        }
    }

    private void stop() {
        this.gameInProgress = false;
        System.out.println("Thanks for playing!");
    }

    private void processCommand(String input) {
        switch (input.toLowerCase()) {
            case "end":
                stop();
                break;
            case "i":
            case "inv":
            case "inventory":
                Utils.inventory();
                break;
            case "consume":
                Utils.consume(Utils.itemSelectionPrompt(scanner));
                break;
            case "battle":
            case "fight":
                // TODO: 9/18/2020 fighting mechanics
                break;
            case "shop":
                shop.show();
                // TODO: 9/19/2020 shop functionality
                break;
            default:
                System.out.println(Reference.UNKNOWN_COMMAND);
                break;
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static Game getInstance() {
        return instance;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealthCap() {
        return healthCap;
    }

    public void setHealthCap(double healthCap) {
        this.healthCap = healthCap;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getManaCap() {
        return manaCap;
    }

    public void setManaCap(int manaCap) {
        this.manaCap = manaCap;
    }

    public double getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(double damageModifier) {
        this.damageModifier = damageModifier;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<ItemStack> getInventory() {
        return inventory;
    }

    public void setInventory(List<ItemStack> inventory) {
        this.inventory = inventory;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }
}
