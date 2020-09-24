package me.petr.rpg_game;

import me.petr.rpg_game.items.ItemStack;
import me.petr.rpg_game.items.consumables.ConsumableHealingPotionBig;
import me.petr.rpg_game.items.consumables.ConsumableManaPotionBig;
import me.petr.rpg_game.items.consumables.ConsumableStrengthPotion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reference {
    public static final int START_COINS = 750;
    public static final int HEALTH_CAP = 500;
    public static final int MANA_CAP = 200;
    public static final int MANA_COST_TO_FLEE = 25;
    public static final int START_DAMAGE_MODIFIER = 1;
    public static final List<ItemStack> START_INVENTORY = new ArrayList<>(Arrays.asList(
            new ItemStack(2, new ConsumableStrengthPotion()),
            new ItemStack(3, new ConsumableHealingPotionBig()),
            new ItemStack(1, new ConsumableManaPotionBig()))
    );

    public static final String WELCOME =
            "+----------------------------------------+\n" +
                    "|  S P A G E T A K ' S    R E V E N G E  |\n" +
                    "|        m a d e   by   P y c r s        |\n" +
                    "+----------------------------------------+";
}
