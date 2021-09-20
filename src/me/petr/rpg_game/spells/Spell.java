package me.petr.rpg_game.spells;

public interface Spell {
    String getId();
    String getName();
    String getDescription();
    int getCost();
    double getDamage();
}
