package me.petr.rpg_game.spells;

public class SpellElectricFuneral implements Spell {

    @Override
    public String getName() {
        return "Electric Funeral";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getId() {
        return "spell:electric_funeral";
    }

    @Override
    public int getCost() {
        return 80;
    }

    @Override
    public double getDamage() {
        return 125;
    }
}
