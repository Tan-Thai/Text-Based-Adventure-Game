package GameObjects.Items;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.PlayerCharacter;
import Global.*;

import java.util.*;

public class ItemManager {
    private final Map<Item, Integer> itemCollection;
    // TODO make a flexible capacity for the entities.
    private int capacity = 10;

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

    public int setCapacity(int newCapacity) {
        return capacity = newCapacity;
    }
    //endregion

    public boolean checkIfInventoryFull() {
        return getTotalItemCount() >= capacity;
    }

    //region Adding and removal of items in ItemCollections
    // difference between acquire is when an item is presented to the player without warning such as drops.
    public void acquireItem(Item item, Scanner sc) {
        if (item != null) {

            if (!checkIfInventoryFull()) {
                itemCollection.merge(item, 1, Integer::sum);
                System.out.println(item.getName() + " was put into your inventory.");
            } else {
                System.out.print("Your inventory is full, do you wish to swap " + item.getName()
                                 + " with another item in your inventory?\n" +
                                 "(Y/N): ");
                if (Utility.checkYesOrNo(sc)) {
                    discardItem(sc);
                    itemCollection.merge(item, 1, Integer::sum);
                    System.out.println(item.getName() + " was put into your inventory.\n");
                }
            }

        }
    }

    // is used when we make transactions and already have confirmed that the user have space.
    // im keeping the checks for now though to make sure it works with no issues.
    public void addItem(Item item) {
        if (item != null) {
            if (!checkIfInventoryFull())
                itemCollection.merge(item, 1, Integer::sum);
            else
                System.err.println("Entity's inventory is full. - 'Add' mishap.");
        }
    }

    private void discardItem(Scanner sc) {
        while (true) {
            System.out.println();
            printInventory();
            System.out.print("\nChoose an item to discard: ");
            int choice = Utility.checkIfNumber(sc);

            if (choice > 0 && choice <= itemCollection.size()) {
                Item removedItem = new ArrayList<>(itemCollection.keySet()).get(choice - 1);
                removeItem(removedItem);
                System.out.println("\n" + removedItem.getName() + " was discarded.");
                return;
            } else {
                // prolly a try catch here tbh.
                System.out.println("\nInvalid input. Try again.");
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

    public void inspectInventory(Scanner sc, PlayerCharacter player, HostileCharacter enemy) {
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
                // TODO attack with item.
                if (selectedItem instanceof Equipment)
                    selectedItem.promptUse(sc, player, selectedItem);
                else
                    selectedItem.promptUse(sc, player, selectedItem, enemy);
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
                Utility.promptEnterKey(sc);
            }
        }
    }
    //endregion
}
