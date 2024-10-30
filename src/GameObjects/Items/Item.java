package GameObjects.Items;

import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.Scanner;

public abstract class Item {
    private final String name;
    private final String description;

    public Item(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // temp to check if item behaves right.
    public void displayItem() {
        System.out.println("\n--" + name + "--\nDescription: " + description + "\n");
    }

    public abstract void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem);

}
