package me.petr.rpg_game.spells;

public class SpellManhattanProject implements Spell {

    @Override
    public String getName() {
        return "Manhattan Project";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:manhattan_project";
    }

    @Override
    public int getCost() {
        return 200;
    }

    @Override
    public double getDamage() {
        return 350;
    }
}
