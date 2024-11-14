package GameObjects.Items;

import GameObjects.Entities.PlayerCharacter;

import java.util.Scanner;

public abstract class Item {
    private final String name;
    private final String description;
    private final int itemCost;

    public Item(String name, String desc, int cost) {
        this.name = name;
        this.description = desc;
        this.itemCost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getItemCost() {
        return itemCost;
    }

    // temp to check if item behaves right.
    public void displayItem() {
        System.out.println("\n--" + name + "--\nDescription: " + description + "\n");
    }

    public abstract void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem);

}
