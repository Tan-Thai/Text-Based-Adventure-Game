package GameObjects.Items;

import GameObjects.Entities.HostileCharacter;
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

    public void displayItem() {
        System.out.println(
                "\n--" + name + "--" +
                "\nCost: " + itemCost +
                "\nDescription: " + description);
    }

    // Currently making use of an overload of promptUse to make it work with an enemy or without.
    // (Hideous solution according to myself who made it - TT)
    public abstract void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem, HostileCharacter enemy);

    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem) {
        promptUse(sc, player, selectedItem, null);
    }
}
