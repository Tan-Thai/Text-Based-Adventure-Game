package Interactions;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Items.Item;
import Global.Utility;

import java.util.Scanner;

public class Transactions {
    /* methods for handling trading between 2 entities. this will be things such as
     * trading with the shop, both buying and selling items.
     * Other actions would also include giving currency from a defeated enemy to the player.
     * */


    //region Methods for Shopping, includes inventory full + affordable checks.
    // Main reason for the buy/sell methods being seperated is for clarity when calling upon said methods.
    public static void playerBuyItem(PlayerCharacter pc, Entity npc, Item item, Scanner sc) {

        if (checkIfUnaffordable(pc, item) || checkIfInventoryFull(pc)) {
            Utility.promptEnterKey(sc);
            return;
        }

        handleTransaction(pc, npc, item);
        Utility.promptEnterKey(sc);
    }

    public static void playerSellItem(PlayerCharacter pc, Entity npc, Item item, Scanner sc) {

        if (checkIfUnaffordable(npc, item) || checkIfInventoryFull(npc)) {
            Utility.promptEnterKey(sc);
            return;
        }

        handleTransaction(npc, pc, item);
        Utility.promptEnterKey(sc);
    }

    // Handle the transaction by transferring the item and adjusting currency. All heavy logic is here.
    private static void handleTransaction(Entity buyer, Entity seller, Item item) {
        boolean isBuyerPC = buyer instanceof PlayerCharacter;

        if (seller.getInventory().getItems().containsKey(item)) {
            buyer.setCurrency(buyer.getCurrency() - item.getItemCost());
            seller.setCurrency(seller.getCurrency() + item.getItemCost());
            seller.getInventory().removeItem(item);
            buyer.getInventory().addItem(item);

            if (isBuyerPC) {
                System.out.println("You just bought " + item.getName() + " for " + item.getItemCost() + " currency.");
            } else {
                System.out.println("You just sold " + item.getName() + " for " + item.getItemCost() + " currency.");
            }

        } else {
            // this else is our fallback if some odd bug appears.
            if (isBuyerPC)
                System.out.println("The shopkeeper does not have the item you are looking for.");
            else
                System.out.println("You do not have the item you are trying to sell.");
        }
    }

    private static boolean checkIfUnaffordable(Entity actor, Item item) {
        if (actor.getCurrency() < item.getItemCost()) {
            if (actor instanceof PlayerCharacter)
                System.out.println("You do not have enough currency to buy this item.");
            else
                System.out.println("The seller cannot afford to buy your item.");

            return true;
        }
        return false;
    }

    private static boolean checkIfInventoryFull(Entity actor) {
        if (actor.getInventory().checkIfInventoryFull()) {
            if (actor instanceof PlayerCharacter)
                System.out.println("You don't have enough inventory space to buy this item.");
            else
                System.out.println("The shopkeeper does not have enough space to buy this item.");

            return true;
        }
        return false;
    }
    //endregion

    public static void lootCurrency(Entity pc, Entity enemyNpc) {
        pc.setCurrency(pc.getCurrency() + enemyNpc.getCurrency());

        System.out.println("You just acquired " + enemyNpc.getCurrency() + " money");
        enemyNpc.setCurrency(0);
    }

    public static void acquireCurrency(Entity pc, int amount) {
        pc.setCurrency(pc.getCurrency() + amount);
    }
}
