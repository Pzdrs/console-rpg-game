package me.petr.rpg_game.spells;

public class SpellRockfall implements Spell {

    @Override
    public String getName() {
        return "Rockfall";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:rock_fall";
    }

    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public double getDamage() {
        return 30;
    }
}
