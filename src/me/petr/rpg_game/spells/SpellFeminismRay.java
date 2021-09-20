package me.petr.rpg_game.spells;

public class SpellFeminismRay implements Spell {

    @Override
    public String getName() {
        return "Feminism Ray";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:feminism_ray";
    }

    @Override
    public int getCost() {
        return 50;
    }

    @Override
    public double getDamage() {
        return 75;
    }
}
