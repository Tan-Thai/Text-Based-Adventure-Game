package GameObjects.Items;

import Global.GlobalMethodLibrary;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " was put into your inventory");
    }

    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            System.out.println(item.getName() + " was removed from your inventory");
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

    public void inspectInventory(Scanner sc) {
        if (checkIfEmpty()) return;

        printInventory();
        while (true) {
            System.out.print("\nPress 0 to exit.\nEnter the number of the item to inspect: ");
            int input = GlobalMethodLibrary.checkIfNumber(sc);

            if (input == 0) {
                System.out.println("exiting inventory view"); //temp since i dunno how to phrase this.
                return;
            }

            if (input > 0 && input <= items.size()) {
                Item selectedItem = items.get(input - 1);
                selectedItem.displayItem();
            } else {
                System.out.println("Invalid choice. Please try again.");
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
