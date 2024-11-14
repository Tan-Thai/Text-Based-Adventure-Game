package GameObjects.Items;

import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.Scanner;

public class Equipment extends Item {
    private final Effect weaponEffect;
    private final EquipmentSlot eqSlot;

    public Equipment(String name, String desc, EquipmentSlot slot, Effect effect) {
        super(name, desc);
        this.weaponEffect = effect;
        this.eqSlot = slot;
    }

    public EquipmentSlot getEQSlot() {
        return eqSlot;
    }

    public int getDamageValue() {
        return weaponEffect.getValue();
    }

    @Override
    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem) {
        //temp implementation to check if it works.

        System.out.print("Do you want to equip this item? (Y/N): ");
        if (Utility.checkYesOrNo(sc)) {
            System.out.println("You equipped " + getName());
            player.getInventory().addItem(player.equipItem(this));
            player.getInventory().removeItem(this);
        } else {
            System.out.println("You decided not to equip the item.");
        }
        // add some equip functionality; awaiting equipment function.
    }
}
