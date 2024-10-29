package GameObjects.Items;

import GameObjects.Entites.PlayerCharacter;
import Global.Utility;

import java.util.Scanner;

public class Item {
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

    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem) {

        // will most likely make a switch case for this in case of other items like weapons.
        if (selectedItem instanceof Consumable) {
            System.out.print("Do you want to use this item? (Y/N): ");
            boolean response = Utility.checkYesOrNo(sc);

            if (response) {
                ((Consumable) selectedItem).consume(player);
            } else {
                System.out.println("You decided not to use the item.");
            }

        } else {
            System.out.println("This item cannot be used.");
        }

        Utility.promptEnterKey(sc);
    }
}
