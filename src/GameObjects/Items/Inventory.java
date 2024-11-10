package GameObjects.Items;

import GameObjects.Entities.PlayerCharacter;
import Global.*;

import java.util.*;

public class Inventory {
    private final Map<Item, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void addItem(Item item) {
        if (item != null) {
            items.merge(item, 1, Integer::sum);
            System.out.println(item.getName() + " was put into your inventory.");
        }
    }

    public void removeItem(Item item) {
        if (item != null && items.containsKey(item)) {
            int count = items.get(item);
            if (count > 1) {
                items.put(item, count - 1);
            } else {
                items.remove(item);
            }
            System.out.println("\n" + item.getName() + " was removed from your inventory");
        } else {
            System.out.println("You do not posses a " + item.getName());
        }
    }

    public void printInventory() {
        if (checkIfEmpty()) return;

        System.out.println("Inventory:");
        int i = 1; // Kind of a cursed enhanced loop
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            System.out.println(i + ". " + entry.getKey().getName() + " x" + entry.getValue());
            i++;
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
                //temp since I don't know how to phrase this.
                System.out.println("You close your inventory.");
                Utility.promptEnterKey(sc);
                return;
            }

            if (input > 0 && input <= items.size()) {
                // generate a new arraylist to print an index with associated numbers.
                // input -1 is due to index starting at 0
                Item selectedItem = (new ArrayList<>(items.keySet())).get(input - 1);
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
