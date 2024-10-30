package GameObjects.Items;

import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.Scanner;

public class Potion extends Item implements Consumable {
    private final Effect potionEffect;


    public Potion(String name, String desc, Effect effect) {
        super(name, desc);
        this.potionEffect = effect;
    }

    @Override
    public void consume(PlayerCharacter player) {
        System.out.println();
            System.out.println("You consumed " + getName());
            potionEffect.apply(player);
            player.getInventory().removeItem(this);
    }

    @Override
    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem) {
        System.out.print("Do you want to use this item? (Y/N): ");
        boolean response = Utility.checkYesOrNo(sc);

        if (response) {
            consume(player);
        } else {
            System.out.println("You decided not to use the item.");
        }

        Utility.promptEnterKey(sc);
    }

}
