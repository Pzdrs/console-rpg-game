package me.petr.rpg_game.entities;

public class Entity {
    private String id, name, quote;
    private double damage, health;
    private int coins;

    public Entity(String id, String name, String quote, double damage, double health, int coins) {
        this.id = id;
        this.name = name;
        this.quote = quote;
        this.damage = damage;
        this.health = health;
        this.coins = coins;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuote() {
        return quote;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public int getCoins() {
        return coins;
    }

    public void dealDamage(double dmg) {
        this.health -= dmg;
    }
}
