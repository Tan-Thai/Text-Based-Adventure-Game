package GameObjects.Items;

import GameObjects.Entities.PlayerCharacter;
import Global.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

public class Inventory {
    private final List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Item item) {
        if (item != null) {
            items.add(item);
            System.out.println(item.getName() + " was put into your inventory");
        }
    }

    public void removeItem(Item item) {
        if (item != null && items.contains(item)) {
            items.remove(item);
            System.out.println("\n" + item.getName() + " was removed from your inventory");
        } else {
            System.out.println("You do not posses a " + item.getName());
        }
    }

    public void printInventory() {
        if (checkIfEmpty()) return;

        System.out.println("Inventory:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }

    }

    public void inspectInventory(Scanner sc, PlayerCharacter player) {
        if (checkIfEmpty()) return;
        // Forced loop until user have exited their inventory or chosen to display a specific item.
        while (true) {
            Utility.clearConsole();
            printInventory();
            System.out.print("\nPress 0 to exit.\nEnter the number of the item to inspect: ");
            int input = Utility.checkIfNumber(sc);

            if (input == 0) {
                System.out.println("exiting inventory view");//temp since i dunno how to phrase this.
                Utility.promptEnterKey(sc);
                return;
            }

            if (input > 0 && input <= items.size()) {
                Item selectedItem = items.get(input - 1);
                selectedItem.displayItem();
                selectedItem.promptUse(sc, player, selectedItem);
            } else {
                System.out.println("Invalid choice. Please try again.");
                Utility.promptEnterKey(sc);
            }
        }

    }

    private boolean checkIfEmpty() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return true;
        }
        return false;
    }
}
