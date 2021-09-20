package me.petr.rpg_game;

import me.petr.rpg_game.entities.Entity;
import me.petr.rpg_game.items.ItemStack;
import me.petr.rpg_game.spells.Spell;

import java.util.LinkedHashMap;
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
    private Spell[] spells;
    private LinkedHashMap<Entity, Boolean> enemies;

    private boolean gameInProgress = false;
    private Shop shop;

    public Game(LinkedHashMap<Entity, Boolean> enemies, List<ItemStack> inventory) {
        instance = this;
        this.scanner = new Scanner(System.in);

        this.health = 100;
        this.healthCap = Reference.HEALTH_CAP;
        this.mana = 100;
        this.manaCap = Reference.MANA_CAP;
        this.damageModifier = Reference.START_DAMAGE_MODIFIER;
        this.coins = Reference.START_COINS;
        this.inventory = inventory;
        this.spells = new Spell[5];
        spells[0] = Utils.getRandomSpell();
        this.enemies = enemies;
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
                Utils.consume(scanner);
                break;
            case "fight":
                Utils.fight(scanner);
                break;
            case "shop":
                shop.open(scanner);
                break;
            case "man":
            case "manual":
                Utils.manual();
                break;
            case "stats":
            case "statistics":
                Utils.stats();
                break;
            case "spells":
                Utils.spells();
                break;
            default:
                System.out.println("Unknown command.");
                break;
        }
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

    public boolean subtractHealth(double health) {
        if (this.health - health <= 0) {
            this.health = 100;
            return true;
        }
        this.health -= health;
        return false;
    }

    public void die() {
        System.out.println("You died and lost a 100 coins!");
        this.coins -= 100;
    }

    public double getHealthCap() {
        return healthCap;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void subtractMana(int mana) {
        this.mana -= mana;
    }

    public int getManaCap() {
        return manaCap;
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

    public Shop getShop() {
        return shop;
    }

    public LinkedHashMap<Entity, Boolean> getEnemies() {
        return enemies;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<ItemStack> getInventory() {
        return inventory;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public void setSpells(Spell[] spells) {
        this.spells = spells;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
