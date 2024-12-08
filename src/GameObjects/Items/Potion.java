package GameObjects.Items;

import GameObjects.Entities.Entity;
import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.Scanner;

public class Potion extends Item implements Consumable {
    private final Effect potionEffect;

    public Potion(String name, String desc, Effect effect, int cost) {
        super(name, desc, cost);
        this.potionEffect = effect;
    }

    //region Display Methods
    public void displayItem() {
        System.out.println(
                "\n--" + getName() + "--" +
                "\nCost: " + getItemCost() +
                "\n" + potionEffect.getEffectType() + ": " + potionEffect.getValue() +
                "\nDescription: " + getDescription());
    }
    //endregion

    //region Using Potion Methods
    @Override
    public void consume(Entity target) {
        System.out.println();
        System.out.println("You used " + getName());
        potionEffect.apply(target);
    }

    @Override
    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem) {
        System.out.print("\nDo you want to use this item? (Y/N): ");
        boolean response = Utility.checkYesOrNo(sc);

        if (potionEffect instanceof HealingEffect && player.isFullHP()) {
            System.out.println("You are currently at full health and put the potion back in your inventory.");
            Utility.promptEnterKey(sc);
            return;
        }

        resolveUsage(response, player, player);
        Utility.promptEnterKey(sc);
    }

    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem, HostileCharacter enemy) {
        // TODO Make it clear for the player if it's used on THEMSELVES or ENEMY. - TT
        System.out.print("\nDo you want to use this item on "
                         + (potionEffect instanceof HealingEffect ? "yourself" : "the enemy") + "? (Y/N): ");
        boolean response = Utility.checkYesOrNo(sc);

        if (potionEffect instanceof HealingEffect) {
            if (player.isFullHP()) {
                System.out.println("You are currently at full health and put the potion back in your inventory.");
                return;
            }
            resolveUsage(response, player, player);
        } else {
            resolveUsage(response, enemy, player);
        }

    }

    private void resolveUsage(Boolean response, Entity target, PlayerCharacter playerInv) {
        if (response) {
            consume(target);
            playerInv.getInventory().removeItem(this);
        } else {
            System.out.println("You decided not to use the item.");
        }
    }
    //endregion
}
