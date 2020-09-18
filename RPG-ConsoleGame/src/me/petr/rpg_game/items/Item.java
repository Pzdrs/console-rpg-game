package me.petr.rpg_game.items;

import me.petr.rpg_game.Reference;

public class Item {
    private String id, name, description;

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
        this.description = Reference.DEFAULT_ITEM_DESCRIPTION;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id;
    }
}
