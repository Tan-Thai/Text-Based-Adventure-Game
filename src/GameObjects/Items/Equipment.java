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

    @Override
    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem) {
        //temp implementation to check if it works.

        System.out.print("Do you want to equip this item? (Y/N): ");
        boolean response = Utility.checkYesOrNo(sc);
        if (response) {
            System.out.println("You equipped " + getName());
            System.out.println("You gained " + weaponEffect.getValue() + " more damage");
        } else {
            System.out.println("You decided not to use the item.");
        }
        // add some equip functionality; awaiting equipment function.
    }

    public EquipmentSlot getEQSlot() {
        return eqSlot;
    }
}
