package Interactions;

import GameObjects.Entities.Entity;
import GameObjects.Items.Item;

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
            return;
        }

        // checks for inventory space
        if (buyer.getInventory().checkForInventorySpace()) {
            System.out.println("You don't have enough inventory space to buy this item.");
            return;
        }

        // double-check if seller has the item.
        if (!seller.getInventory().getItems().containsKey(item)) {
            System.out.println("The shopkeeper does not have the item you are looking for.");
        }

        // Remove money from buyer
        buyer.setCurrency(buyer.getCurrency() - item.getItemCost());
        seller.setCurrency(seller.getCurrency() + item.getItemCost());

        // change name of currency.
        System.out.println("You just bought " + item.getName() + " for " + item.getItemCost() + " money.");
    }

    public static void lootCurrency(Entity pc, Entity enemyNpc) {
        pc.setCurrency(pc.getCurrency() + enemyNpc.getCurrency());
    }

    public static void acquireCurrency(Entity pc, int amount) {

    }
}
