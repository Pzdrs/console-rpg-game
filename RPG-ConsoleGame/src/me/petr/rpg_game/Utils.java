package me.petr.rpg_game;

import me.petr.rpg_game.entities.Entity;
import me.petr.rpg_game.items.ItemStack;
import me.petr.rpg_game.items.consumables.Consumable;
import me.petr.rpg_game.spells.Spell;

import java.util.*;

public class Utils {
    private static Game game = Game.getInstance();

    // Choose what item to consume from your inventory
    public static void consume(Scanner scanner) {
        while (true) {
            System.out.println("+--------------------------->>");
            for (int i = 0; i < game.getInventory().size(); i++) {
                if (game.getInventory().get(i).getItem() instanceof Consumable)
                    System.out.println("[{index}]  ".replace("{index}", String.valueOf(i)) + game.getInventory().get(i).getAmount() + "x " + game.getInventory().get(i).getItem().getName());
            }
            System.out.println("+--------------------------->>");
            System.out.print("What item to consume > ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) return;
            try {
                int item = Integer.parseInt(input);
                if (item <= game.getInventory().size() - 1 && item >= 0) {
                    ItemStack itemStack = game.getInventory().get(item);
                    deleteFromInventory(itemStack, 1);
                    Consumable consumable = (Consumable) itemStack.getItem();
                    System.out.println(" -> " + itemStack.getItem().getName() + " used.");

                    // Handle health
                    if (consumable.getHealth() != 0d) {
                        if (game.getHealth() + consumable.getHealth() <= game.getHealthCap())
                            game.setHealth(game.getHealth() + consumable.getHealth());
                        else game.setHealth(game.getHealthCap());
                        System.out.println("   +{healthAdded}HP (Current HP: {health})"
                                .replace("{health}", String.valueOf(game.getHealth()))
                                .replace("{healthAdded}", String.valueOf(consumable.getHealth())));
                    }
                    // Handle mana
                    if (consumable.getMana() != 0) {
                        if (game.getMana() + consumable.getMana() <= game.getManaCap())
                            game.setMana(game.getMana() + consumable.getMana());
                        else game.setMana(game.getManaCap());
                        System.out.println("   +{manaAdded} Mana (Current Mana: {mana})"
                                .replace("{mana}", String.valueOf(game.getMana()))
                                .replace("{manaAdded}", String.valueOf(consumable.getMana())));
                    }
                    //Handle damage modifier
                    if (consumable.getDamageModifier() != 0) {
                        game.setDamageModifier(game.getDamageModifier() + game.getDamageModifier() * consumable.getDamageModifier());
                        System.out.println("   +{damageModifierAdded}% (Current Damage modifier: {damageModifier})"
                                .replace("{damageModifier}", String.valueOf(game.getDamageModifier()))
                                .replace("{damageModifierAdded}", String.valueOf(consumable.getDamageModifier() * 100)));
                    }
                } else {
                    System.out.println("Invalid item.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Use numbers to specify what item to consume.");
            }
        }
    }

    // Display your inventory
    public static void inventory() {
        System.out.println("+--------------------------->>");
        System.out.println("Your Inventory:");
        System.out.println();
        game.getInventory().forEach(itemStack -> System.out.println("|" + itemStack.getAmount() + "x " + itemStack.getItem().getName()));
        System.out.println("+--------------------------->>");
    }

    // Print out all available commands
    public static void manual() {
        System.out.println("       +-----------------------+");
        System.out.println("       | G a m e   m a n u a l |");
        System.out.println("       +-----------------------+");
        System.out.println();
        System.out.println(" >> end - End the game");
        System.out.println(" >> exit - Exit from a shop/consume/etc..");
        System.out.println(" >> manual|man - Open the game manual");
        System.out.println(" >> statistics|stats - Show your statistics");
        System.out.println(" >> shop - Talk to the merchant");
        System.out.println(" >> inventory|inv|i - Open the inventory");
        System.out.println(" >> consume - Consume an item in your inventory");
        System.out.println(" >> fight - Go to a fight");
    }

    // Show stats
    public static void stats() {
        System.out.println("   Your stats: {health}HP/{healthCap}HP  |  {mana} Mana/{manaCap} Mana  |  {damageModifier}x Damage modifier  |  {coins} coins  |  {kills}/{killCap} kills"
                .replace("{health}", String.valueOf(game.getHealth()))
                .replace("{healthCap}", String.valueOf(game.getHealthCap()))
                .replace("{mana}", String.valueOf(game.getMana()))
                .replace("{manaCap}", String.valueOf(game.getManaCap()))
                .replace("{damageModifier}", String.valueOf(game.getDamageModifier()))
                .replace("{coins}", String.valueOf(game.getCoins()))
                .replace("{kills}", String.valueOf(Utils.defeatedEnemies()))
                .replace("{killCap}", String.valueOf(game.getEnemies().size())));
    }

    public static boolean fightRUSure(Scanner scanner) {
        while (true) {
            System.out.print("Are you sure you want to fight?(Y/N)> ");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "yes":
                case "y":
                    return true;
                case "no":
                case "n":
                    System.out.println("See ya!");
                    return false;
            }
        }
    }

    private static Spell promptChooseSpell(Scanner scanner) {
        System.out.println("+------------------------------->>");
        for (int i = 0; i < game.getSpells().length; i++) {
            Spell spell = game.getSpells()[i];
            if (spell != null)
                System.out.println("[{index}]  {spell} -> Deals {damage} damage, Costs {mana} Mana"
                        .replace("{index}", String.valueOf(i))
                        .replace("{spell}", spell.getName())
                        .replace("{damage}", String.valueOf(spell.getDamage()))
                        .replace("{mana}", String.valueOf(spell.getCost())));
        }
        System.out.println("+------------------------------->>");
        while (true) {
            System.out.print("Choose the spell you want to use (or 'pass' if not enough Mana)> ");
            String input = scanner.nextLine();
            try {
                int spell = Integer.parseInt(input);
                if (game.getSpells()[spell] != null) {
                    if (game.getSpells()[spell].getCost() <= game.getMana())
                        return game.getSpells()[spell];
                    System.out.println("You do not have enough Mana to cast this spell.");
                } else {
                    System.out.println("Invalid spell.");
                }
            } catch (NumberFormatException e) {
                if (input.equalsIgnoreCase("pass")) {
                    System.out.println("You chose not to cast any spells.");
                    return null;
                }
                System.out.println("Use numbers to choose.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid spell.");
            }
        }
    }

    private static boolean promptChooseAction(Scanner scanner, Entity enemy) {
        System.out.println("+--------------------------->>");
        System.out.println("| [0] - Cast a spell\n| [1] - Consume an item\n| [2] - Get me outta here! (Costs {mana} Mana)".replace("{mana}", String.valueOf(Reference.MANA_COST_TO_FLEE)));
        System.out.println("+--------------------------->>");
        while (true) {
            System.out.print("Choose the action> ");
            String input = scanner.nextLine();
            try {
                int inputNumber = Integer.parseInt(input);
                switch (inputNumber) {
                    case 0:
                        Spell spell = promptChooseSpell(scanner);
                        if (spell == null) return false;
                        Spell reward = getNewRandomSpell();
                        game.setMana(game.getMana() - spell.getCost());
                        enemy.dealDamage(spell.getDamage());
                        if (enemy.getHealth() <= 0) {
                            game.setCoins(game.getCoins() + enemy.getCoins());
                            game.getSpells()[(int) Arrays.asList(game.getSpells()).stream().filter(Objects::nonNull).count()] = reward;
                            for (Map.Entry<Entity, Boolean> entity : game.getEnemies().entrySet())
                                if (entity.getKey().getId().equals(enemy.getId())) entity.setValue(true);
                        }
                        System.out.println("----------------------------------------------");
                        System.out.println("You cast {spell} spell against your enemy and you deal {damage} damage. {result}.\nIt cost you {mana} Mana. You have {currentMana}/{manaCap} Mana remaining."
                                .replace("{result}", enemy.getHealth() <= 0 ? "They are now dead, you won the fight and claimed {coins} coins!\nThey dropped {spellDrop} spell." : "They now have {health}HP")
                                .replace("{spellDrop}", reward.getName())
                                .replace("{coins}", String.valueOf(enemy.getCoins()))
                                .replace("{spell}", spell.getName())
                                .replace("{damage}", String.valueOf(spell.getDamage()))
                                .replace("{health}", String.valueOf(enemy.getHealth()))
                                .replace("{mana}", String.valueOf(spell.getCost()))
                                .replace("{currentMana}", String.valueOf(game.getMana()))
                                .replace("{manaCap}", String.valueOf(game.getManaCap())));
                        if (enemy.getHealth() <= 0) return true;
                        return false;
                    case 1:
                        consume(scanner);
                        return false;
                    case 2:
                        if (game.getMana() < Reference.MANA_COST_TO_FLEE) {
                            System.out.println("You do not have enough Mana for this.");
                            return false;
                        }
                        game.subtractMana(Reference.MANA_COST_TO_FLEE);
                        System.out.println("You fled the area!");
                        return true;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Use numbers to choose.");
            }
        }
    }

    private static boolean enemyAttack(Scanner scanner, Entity enemy) {
        System.out.println("{enemy} has casted their spell, its plummeting towards you. You have few options here:".replace("{enemy}", enemy.getName()));
        System.out.println("+----------------------------------------------------------------------------------------------------->>");
        System.out.println("| [0] - Defend with your shield (You are gonna take a fraction of its full power)\n| [1] - Try to dodge the spell (!!!WARNING!!! A 50%/50% chance to dodge the spell or take its full power)");
        System.out.println("+----------------------------------------------------------------------------------------------------->>");
        while (true) {
            System.out.print("Choose your defence> ");
            String input = scanner.nextLine();
            try {
                int inputNumber = Integer.parseInt(input);
                boolean died;
                switch (inputNumber) {
                    case 0:
                        double damage = enemy.getDamage() * Math.random();
                        died = game.subtractHealth(damage);
                        System.out.println("You put your shield in front of you and it absorbs some of the spell's power.\nIt dealt {damage} damage to you. You now have {health}HP"
                                .replace("{damage}", String.valueOf(Math.round(damage)))
                                .replace("{health}", String.valueOf(Math.round(game.getHealth()))));
                        if (died) {
                            game.die();
                            return true;
                        }
                        return false;
                    case 1:
                        double dodge = Math.random();
                        if (dodge < .5) {
                            System.out.println("You dodged the spell completely!");
                        } else {
                            died = game.subtractHealth(enemy.getDamage());
                            System.out.println(("You tried to dodge the spell but didn't succeed, you took {damage} damage. " + (died ? "You are now dead." : "You now have {health}HP"))
                                    .replace("{damage}", String.valueOf(enemy.getDamage()))
                                    .replace("{health}", String.valueOf(game.getHealth())));
                            if (died) {
                                game.die();
                                return true;
                            }
                        }
                        return false;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Use numbers to choose.");
            }
        }
    }

    public static void fight(Scanner scanner) {
        if (!fightRUSure(scanner)) return;
        Entity enemy = (Entity) game.getEnemies().keySet().toArray()[defeatedEnemies()];
        System.out.println("You are slowly approaching the fighting pit. A drop of sweat is flowing down your forehead. And. \nThere. He. Is. {enemy}. As you approach him, he says, {quote}"
                .replace("{enemy}", enemy.getName())
                .replace("{quote}", enemy.getQuote()));
        while (enemy.getHealth() > 0) {
            if (promptChooseAction(scanner, enemy)) {
                break;
            } else {
                if (enemyAttack(scanner, enemy)) return;
            }
        }
    }

    // Solves the problem of duplicate item stacks
    public static void addToInventory(ItemStack itemStack) {
        for (ItemStack stack : game.getInventory()) {
            if (stack.getItem().getId().equals(itemStack.getItem().getId())) {
                stack.increaseAmount(itemStack.getAmount());
                return;
            }
        }
        // Had to create a new object instead of just passing in itemStack idk why it duplicated if i did so
        game.getInventory().add(new ItemStack(itemStack.getAmount(), itemStack.getItem()));
    }

    public static void deleteFromInventory(ItemStack itemStack, int amount) {
        ItemStack toDelete = null;
        for (ItemStack stack : game.getInventory()) {
            if (stack.getItem().getId().equals(itemStack.getItem().getId())) {
                if (stack.getAmount() == 1) {
                    toDelete = stack;
                    break;
                } else {
                    stack.decreaseAmountBy(amount);
                    return;
                }
            }
        }
        game.getInventory().remove(toDelete);
    }

    public static int defeatedEnemies() {
        int kills = 0;
        for (Map.Entry<Entity, Boolean> enemy : game.getEnemies().entrySet()) {
            if (enemy.getValue()) kills++;
        }
        return kills;
    }

    public static Spell getRandomSpell() {
        return Main.spells.get(new Random().nextInt(Main.spells.size()));
    }

    public static Spell getNewRandomSpell() {
        while (true) {
            Spell random = getRandomSpell();
            for (Spell spell : game.getSpells()) {
                if (spell != null)
                    if (spell.getId().equals(random.getId())) break;
            }
            return random;
        }
    }

    public static void spells() {
        System.out.println("+------------------------------------------------->>");
        System.out.println("Your Spells({spells}/{maxSpells}):"
                .replace("{spells}", String.valueOf(Arrays.stream(game.getSpells()).filter(Objects::nonNull).count()))
                .replace("{maxSpells}", String.valueOf(game.getSpells().length)));
        System.out.println();
        for (Spell spell : game.getSpells()) {
            if (spell != null)
                System.out.println("|" + spell.getName() + "\n - Damage -> {damage}, Cost -> {cost} Mana"
                        .replace("{damage}", String.valueOf(spell.getDamage()))
                        .replace("{cost}", String.valueOf(spell.getCost())));
        }
        System.out.println("+------------------------------------------------->>");
    }
}
