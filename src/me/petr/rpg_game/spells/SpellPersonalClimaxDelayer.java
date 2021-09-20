package me.petr.rpg_game.spells;

public class SpellPersonalClimaxDelayer implements Spell {

    @Override
    public String getName() {
        return "Personal Climax Delayer";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:personal_climax_delayer";
    }

    @Override
    public int getCost() {
        return 100;
    }

    @Override
    public double getDamage() {
        return 120;
    }
}
