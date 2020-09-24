package me.petr.rpg_game.spells;

public class SpellBabyCrusher implements Spell {

    @Override
    public String getName() {
        return "Baby Crusher";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:baby_crusher";
    }

    @Override
    public int getCost() {
        return 25;
    }

    @Override
    public double getDamage() {
        return 50;
    }
}
