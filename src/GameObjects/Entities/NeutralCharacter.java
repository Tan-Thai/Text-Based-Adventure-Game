package GameObjects.Entities;

import GameObjects.Data.ItemId;
import GameObjects.Data.ItemRepository;
import GameObjects.Items.Item;
import Global.Utility;
import Interactions.Transactions;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NeutralCharacter extends Entity {

    public NeutralCharacter(String name, int health) {
        super(name, health, 1);
    }

    // neutral npc's such as shopkeeper, quest giver, non-combative people.
    // Functions such as interaction, shop, and the like will be in this file
    public void setupShopkeeper() {
        setCurrency(999);
        setHealth(1000);
        getInventory().setCapacity(100);

            getInventory().addItem(ItemRepository.getItemById(ItemId.RAIDIANT_WARHAMMER));
            getInventory().addItem(ItemRepository.getItemById(ItemId.PATCHY_LEATHER_ARMOUR));
        for (int i = 0; i <= 3; i++) {
            getInventory().addItem(ItemRepository.getItemById(ItemId.POISON_POTION));
            getInventory().addItem(ItemRepository.getItemById(ItemId.HEALTH_POTION));
        }
    }

    // Currently naming the 2 methods buy/sell from shop.
    public void buyFromShop(PlayerCharacter pc, Scanner sc) {

        while (!pc.isDead()) {
            Utility.clearConsole();
            List<Map.Entry<Item, Integer>> sortedItems = getInventory().getSortedInventory();
            getInventory().printInventory(sortedItems);

            System.out.print(
                    "\nPress 0 to exit the shop" +
                    "\nPick an item to inspect or purchase: ");

            int choice = Utility.checkIfNumber(sc);

            // exit method
            if (choice == 0) {
                System.out.println("You leave the shop.");
                Utility.promptEnterKey(sc);
                return;
            }

            // Selection of item.
            if (choice > 0 && choice <= getInventory().getItems().size()) {
                Item selectedItem = sortedItems.get(choice - 1).getKey();
                selectedItem.displayItem();

                System.out.print("\nDo you want to purchase this item? (Y/N): ");
                if (Utility.checkYesOrNo(sc)) {
                    Transactions.playerBuyItem(pc, this, selectedItem, sc);
                } else {
                    System.out.println("You decided to not purchase this item.");
                    Utility.promptEnterKey(sc);
                }
            }
        }
    }

    public void sellToShop(PlayerCharacter pc, Scanner sc) {

        while (!pc.isDead()) {
            Utility.clearConsole();
            List<Map.Entry<Item, Integer>> sortedItems = pc.getInventory().getSortedInventory();
            pc.getInventory().printInventory(sortedItems);

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
                Item selectedItem = sortedItems.get(choice - 1).getKey();
                selectedItem.displayItem();

                System.out.print("\nDo you want to sell this item? (Y/N): ");
                if (Utility.checkYesOrNo(sc)) {
                    Transactions.playerSellItem(pc, this, selectedItem, sc);
                } else {
                    System.out.println("You decided to not sell this item.");
                    Utility.promptEnterKey(sc);
                }
            }
        }
    }
}
