package GameObjects.Items;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.Scanner;

public class Equipment extends Item {
    private final Effect effect;
    private final EquipmentType eqSlot;

    public Equipment(String name, String desc, EquipmentType slot, Effect effect, int cost) {
        super(name, desc, cost);
        this.effect = effect;
        this.eqSlot = slot;
    }

    public EquipmentType getEQSlot() {
        return eqSlot;
    }

    public int getEffectValue() {
        return effect.getValue();
    }

    public void displayItem() {
        System.out.println(
                "\n--" + getName() + "--" +
                "\nCost: " + getItemCost() +
                "\n" + effect.getEffectType() + ": " + effect.getValue() +
                "\nDescription: " + getDescription());
    }

    @Override
    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem, HostileCharacter enemy) {
        handleUsage(sc, player, selectedItem);
    }

    public void promptUse(Scanner sc, PlayerCharacter player, Item selectedItem) {
        handleUsage(sc, player, selectedItem);
        Utility.promptEnterKey(sc);
    }

    private void handleUsage(Scanner sc, PlayerCharacter player, Item selectedItem) {
        System.out.print("\nDo you want to equip this item? (Y/N): ");
        if (Utility.checkYesOrNo(sc)) {
            System.out.println("You equipped " + getName());
            player.getInventory().removeItem(selectedItem);
            player.getInventory().addItem(player.getEquipmentList().equipItem(this, player));
        } else {
            System.out.println("You decided not to equip the item.");
        }
    }
}
