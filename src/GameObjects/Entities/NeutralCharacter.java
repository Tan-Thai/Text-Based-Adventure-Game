package GameObjects.Entities;

import GameObjects.Items.Item;
import Global.Utility;
import Interactions.Transactions;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class NeutralCharacter extends Entity {

    public NeutralCharacter(String name, int health) {
        super(name, health, 1);
    }

    // neutral npc's such as shopkeeper, quest giver, non-combative people.
    // Functions such as interaction, shop, and the like will be in this file
    private void setupShopkeeper() {
        setCurrency(999);
        setHealth(1000);

        // populate inventory.
    }

    // Currently naming the 2 methods buy/sell from shop.
    public void buyFromShop(PlayerCharacter pc, Scanner sc) {

        while (!pc.isDead()) {
            Utility.clearConsole();
            getInventory().printInventory();

            System.out.print(
                    "\nPress 0 to exit the shop" +
                    "\nPick an item to inspect or purchase: ");

            int choice = Utility.checkIfNumber(sc);

            // Exit method
            if (choice == 0) {
                System.out.println("You leave the shop.");
                Utility.promptEnterKey(sc);
                return;
            }

            // Selection of item.
            if (choice > 0 && choice <= getInventory().getItems().size()) {
                Item selectedItem = new ArrayList<>(getInventory().getItems().keySet()).get(choice - 1);
                selectedItem.displayItem();

                System.out.print("\nDo you want to purchase this item? (Y/N): ");
                if (Utility.checkYesOrNo(sc)) {
                    Transactions.buyItem(pc, this, selectedItem);
                } else {
                    System.out.println("Invalid choice, please try again.");
                    Utility.promptEnterKey(sc);
                }
            }
        }
    }

    public void sellToShop(PlayerCharacter pc, Scanner sc) {

        while (!pc.isDead()) {
            Utility.clearConsole();
            pc.getInventory().printInventory();

            System.out.print(
                    "\nPress 0 to exit the shop" +
                    "\nPick an item to inspect or sell: ");

            int choice = Utility.checkIfNumber(sc);

            if (choice == 0) {
                System.out.println("You stop selling items.");
                Utility.promptEnterKey(sc);
                return;
            }

            if (choice > 0 && choice <= pc.getInventory().getItems().size()) {
                Item selectedItem = new ArrayList<>(pc.getInventory().getItems().keySet()).get(choice - 1);
                selectedItem.displayItem();

                // Prompt to sell the item
                int sellPrice = selectedItem.getItemCost() / 2; // Example: Sell for 50% of buy price
                System.out.println("\nThis item can be sold for " + sellPrice + " money.");
                System.out.print("Do you want to sell this item? (Y/N): ");

                if (Utility.checkYesOrNo(sc)) {
                    // Perform the transaction
                    if (pc.getInventory().getItems().containsKey(selectedItem)) {
                        pc.getInventory().removeItem(selectedItem);
                        pc.setCurrency(pc.getCurrency() + sellPrice);
                        setCurrency(getCurrency() - sellPrice);
                        System.out.println("You sold " + selectedItem.getName() + " for " + sellPrice + " money.");
                    } else {
                        System.out.println("Error: Item not found in your inventory.");
                    }
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                Utility.promptEnterKey(sc);
            }
        }
    }
}
