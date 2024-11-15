package GameObjects.Items;

import GameObjects.Entities.PlayerCharacter;
import Global.*;

import java.util.*;

public class ItemManager {
    private final Map<Item, Integer> itemCollection;
    // TODO make a flexible capacity for the entities.
    private final int capacity = 10;

    public ItemManager() {
        this.itemCollection = new HashMap<>();
    }

    private boolean checkIfEmpty() {
        if (itemCollection.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return true;
        }
        return false;
    }

    //region Getters
    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(itemCollection);
    }

    public int getTotalItemCount() {
        return itemCollection.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getCapacity() {
        return capacity;
    }
    //endregion

    public boolean checkForInventorySpace() {
        return getTotalItemCount() < capacity;
    }

    //region Adding and removal of items in ItemCollections
    // Currently have no good term for spawning items into inventory without text
    // quite the temporary method due to us not shoving a lot into the constructors as off yet.
    public void spawnItem(Item item) {
        if (item != null) {
            if (getTotalItemCount() < capacity) {
                itemCollection.merge(item, 1, Integer::sum);
            } else {
                System.err.println("Entity's inventory is full.");
            }
        }
    }

    public void addItem(Item item, Scanner sc) {
        if (item != null) {

            if (checkForInventorySpace()) {
                itemCollection.merge(item, 1, Integer::sum);
                System.out.println(item.getName() + " was put into your inventory.");
            } else {
                System.out.print("Your inventory is full, do you wish to swap " + item.getName()
                                 + " with another item in your inventory?\n" +
                                 "(Y/N): ");
                if (Utility.checkYesOrNo(sc)) {
                    discardItem(sc);
                    itemCollection.merge(item, 1, Integer::sum);
                    System.out.println(item.getName() + " was put into your inventory.");
                }
            }
        }
    }

    private void discardItem(Scanner sc) {
        while (true) {
            printInventory();
            System.out.print("Choose an item to discard: ");
            int choice = Utility.checkIfNumber(sc);

            if (choice > 0 && choice <= itemCollection.size()) {
                Item removedItem = new ArrayList<>(itemCollection.keySet()).get(choice - 1);
                removeItem(removedItem);
                System.out.println(removedItem.getName() + " was discarded.");
                return;
            } else {
                // prolly a try catch here tbh.
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public void removeItem(Item item) {
        if (item != null && itemCollection.containsKey(item)) {
            int count = itemCollection.get(item);
            if (count > 1) {
                itemCollection.put(item, count - 1);
            } else {
                itemCollection.remove(item);
            }
        } else {
            assert item != null;
            System.out.println("You do not posses a " + item.getName());
        }
    }
    //endregion

    //region Printing and visual interaction of inventory
    public void printInventory() {
        if (checkIfEmpty()) return;

        System.out.println("Inventory:");
        int i = 1; // Kind of a cursed enhanced loop
        for (Map.Entry<Item, Integer> entry : itemCollection.entrySet()) {
            System.out.println(i + ". " + entry.getKey().getName() + " x" + entry.getValue());
            i++;
        }
    }

    public void inspectInventory(Scanner sc, PlayerCharacter player) {
        if (checkIfEmpty()) return;
        // Forced loop until user have exited their inventory or chosen to display a specific item.
        while (!player.isDead()) {
            Utility.clearConsole();
            printInventory();
            System.out.print("""
                    
                    Press 0 to exit.\
                    
                    Pick an item to inspect:\s""");
            int input = Utility.checkIfNumber(sc);

            if (input == 0) {
                //temp since I don't know how to phrase this.
                System.out.println("You close your inventory.");
                Utility.promptEnterKey(sc);
                return;
            }

            if (input > 0 && input <= itemCollection.size()) {
                // generate a new arraylist to print an index with associated numbers.
                // input -1 is due to index starting at 0
                Item selectedItem = (new ArrayList<>(itemCollection.keySet())).get(input - 1);
                selectedItem.displayItem();
                selectedItem.promptUse(sc, player, selectedItem);
            } else {
                System.out.println("Invalid choice. Please try again.");
                Utility.promptEnterKey(sc);
            }
        }
    }
    //endregion
}
