package me.petr.rpg_game.spells;

public class SpellMagisMissile implements Spell {

    @Override
    public String getName() {
        return "Magic Missile";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:magic_missile";
    }

    @Override
    public int getCost() {
        return 20;
    }

    @Override
    public double getDamage() {
        return 35;
    }
}
