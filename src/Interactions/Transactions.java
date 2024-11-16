package Interactions;

import GameObjects.Entities.Entity;
import GameObjects.Items.Item;
import Global.Utility;

import java.util.Scanner;

public class Transactions {
    /* methods for handling trading between 2 entities. this will be things such as
     * trading with the shop, both buying and selling items.
     * Other actions would also include giving currency from a defeated enemy to the player.
     * */

    public static void buyItem(Entity buyer, Entity seller, Item item, Scanner sc) {
        // checks, can potentially be their own method.
        if (buyer.getCurrency() < item.getItemCost()) {
            System.out.println("You do not have enough money to buy this item.");
            Utility.promptEnterKey(sc);
            return;
        }

        // checks for inventory space
        if (buyer.getInventory().checkIfInventoryFull()) {
            System.out.println("You don't have enough inventory space to buy this item.");
            Utility.promptEnterKey(sc);
            return;
        }

        // double-check if seller has the item and updates both entities currency/inventory.
        if (seller.getInventory().getItems().containsKey(item)) {
            buyer.setCurrency(buyer.getCurrency() - item.getItemCost());
            seller.setCurrency(seller.getCurrency() + item.getItemCost());
            System.out.println("You just bought " + item.getName() + " for " + item.getItemCost() + " money.");
            buyer.getInventory().addItem(item, sc);
            seller.getInventory().removeItem(item);
        } else {
            System.out.println("The shopkeeper does not have the item you are looking for.");
        }

        Utility.promptEnterKey(sc);
    }

    public static void sellItem(Entity buyer, Entity seller, Item item, Scanner sc) {
        // checks, can potentially be their own method.
        if (buyer.getCurrency() < item.getItemCost()) {
            System.out.println("The seller cannot afford to buy your item.");
            Utility.promptEnterKey(sc);
            return;
        }

        // checks for inventory space
        if (buyer.getInventory().checkIfInventoryFull()) {
            System.out.println("The shopkeeper does not have enough space to buy this item.");
            Utility.promptEnterKey(sc);
            return;
        }

        // double-check if seller has the item and updates both entities currency.
        if (seller.getInventory().getItems().containsKey(item)) {
            buyer.setCurrency(buyer.getCurrency() - item.getItemCost());
            seller.setCurrency(seller.getCurrency() + item.getItemCost());
            System.out.println("You just sold " + item.getName() + " for " + item.getItemCost() + " money.");

            // TODO fix the printing of adding/removing items.
            // currently spawning a new version of that item into shops inventory, bootleg solution for now.
            buyer.getInventory().spawnItem(item);
            seller.getInventory().removeItem(item);
        } else {
            // should never happen but that message is hella funny.
            System.out.println("You do not have the item you are trying to sell.");
        }

        Utility.promptEnterKey(sc);
    }

    public static void lootCurrency(Entity pc, Entity enemyNpc) {
        pc.setCurrency(pc.getCurrency() + enemyNpc.getCurrency());

        System.out.println("You just acquired " + enemyNpc.getCurrency() + " money");
        enemyNpc.setCurrency(0);
    }

    public static void acquireCurrency(Entity pc, int amount) {
        pc.setCurrency(pc.getCurrency() + amount);
    }
}
