package me.petr.rpg_game.spells;

public class SpellMetalUpYourAss implements Spell {

    @Override
    public String getName() {
        return "Metal Up Your Ass";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:metal_up_your_ass";
    }

    @Override
    public int getCost() {
        return 25;
    }

    @Override
    public double getDamage() {
        return 30;
    }
}
