package me.petr.rpg_game.spells;

public class SpellInstantRegret implements Spell {

    @Override
    public String getName() {
        return "Instant Regret";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:instant_regret";
    }

    @Override
    public int getCost() {
        return 200;
    }

    @Override
    public double getDamage() {
        return 69;
    }
}
