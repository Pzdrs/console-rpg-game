package me.petr.rpg_game;

import me.petr.rpg_game.entities.*;
import me.petr.rpg_game.items.ItemStack;
import me.petr.rpg_game.items.consumables.*;
import me.petr.rpg_game.spells.*;

import java.util.*;

public class Main {
    private static Game game;
    public static List<Spell> spells;
    private static LinkedHashMap<Entity, Boolean> enemies;
    private static Shop shop;

    public static void main(String[] args) {
        spells = new ArrayList<>();
        // Register spells
        spells.addAll(Arrays.asList(
                new SpellBabyCrusher(),
                new SpellElectricFuneral(),
                new SpellFeminismRay(),
                new SpellInstantRegret(),
                new SpellMagisMissile(),
                new SpellManhattanProject(),
                new SpellMetalUpYourAss(),
                new SpellPersonalClimaxDelayer(),
                new SpellRockfall()
        ));

        List<ItemStack> inventory = new ArrayList<>();
        inventory.addAll(Reference.START_INVENTORY);

        // Register entities
        enemies = new LinkedHashMap<Entity, Boolean>() {{
            put(new EntityTill("entity:till", "Till the virgin", "*insert some quote here*", 50, 100, 250), false);
            put(new EntityOlsak("entity:olsak", "Olsak the thief", "Boutta snatch your physics notebook for not paying attention in my Java class kid.", 100, 150, 500), false);
            put(new EntityEsch("entity:esch", "Esch Almighty", "Kluci kluci, kdy vy dospejete tyvole..", 150, 200, 750), false);
            put(new EntityLiskova("entity:liskova", "Liskova the Mathemician", "Vezmete si papiry na proverky, na stole bude pouze pero, tuzka a pravitko.", 200, 250, 1000), false);
            put(new EntitySpagetak("entity:spagetak", "Dictator Spagetak", "Oh I remember you. You are the little annoying minecraft kid with glasses who's code I couldn't understand because it was all written in english and had too much OOP implemented.", 250, 500, Integer.MAX_VALUE), false);
        }};

        game = new Game(enemies, inventory);
        shop = new Shop(game);

        game.setShop(shop);
        // Register shop items
        shop.addItem(150, new ConsumableManaPotionSmall(), 1);
        shop.addItem(250, new ConsumableManaPotionBig(), 1);
        shop.addItem(50, new ConsumableHealingPotionSmall(), 1);
        shop.addItem(100, new ConsumableHealingPotionBig(), 1);
        shop.addItem(300, new ConsumableStrengthPotion(), 1);
        shop.addItem(500, new ConsumableGoldenApple(), 1);

        game.start();
    }
}
