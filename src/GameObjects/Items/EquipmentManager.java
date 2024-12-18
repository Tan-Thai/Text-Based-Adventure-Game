package GameObjects.Items;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EquipmentManager {
    private final Map<EquipmentType, Equipment> equipmentCollection;

    public EquipmentManager() {
        this.equipmentCollection = new HashMap<>();
        for (EquipmentType slot : EquipmentType.values()) {
            equipmentCollection.put(slot, null);
        }
    }

    public Equipment getEquipment(EquipmentType slot) {
        return equipmentCollection.get(slot);
    }

    // region Equip/Un-Equip handling
    public Equipment equipItem(Equipment eq, PlayerCharacter pc) {
        EquipmentType eqSlot = eq.getEQSlot();
        Equipment previousEQ = equipmentCollection.get(eqSlot);
        equipmentCollection.put(eqSlot, eq);

        if (eq.getEQSlot() == EquipmentType.ARMOUR) {
            // We were unable to get apply to work as intended here, so instead we use
            // setArmour.
            pc.setArmour(eq.getEffectValue());
        }

        if (previousEQ != null) {
            System.out.println("You put " + previousEQ.getName() + " in your inventory.");
        }
        return previousEQ;
    }

    // removes item from equipment, and puts it back into players inventory.
    public Equipment unequipEquipment(EquipmentType slot, Entity pc) {
        if (equipmentCollection.containsKey(slot)) {
            Equipment removedItem = equipmentCollection.get(slot);
            equipmentCollection.put(slot, null);

            System.out.println("You unequipped " + removedItem.getName() + ".");

            if (removedItem.getEQSlot() == EquipmentType.ARMOUR) {
                pc.setArmour(0);
            }

            return removedItem;
        }
        return null;
    }

    // the equivalent of prompting to use an item. but in this case un-equipping it.
    private void promptUnequip(Scanner sc, EquipmentType selectedType, PlayerCharacter pc) {
        System.out.print("\nDo you want to put this item into your inventory? (Y/N): ");
        boolean response = Utility.checkYesOrNo(sc);

        if (response) {
            pc.getInventory().acquireItem(unequipEquipment(selectedType, pc), sc);
        } else {
            System.out.println("You decided not to.");
        }
    }
    // endregion

    // region Printing and visual interaction of Equipments
    public void printEquipment() {
        System.out.println("Equipment:");
        int i = 1;

        /*
         * Starts on one and loops through each type, printing each type we have
         * Prints out none if its empty, otherwise the name of said item.
         */
        for (EquipmentType type : EquipmentType.values()) {
            Equipment eq = equipmentCollection.get(type);
            String eqPrint = (eq == null) ? "None" : eq.getName();

            System.out.println(i + ". " + type.name() + ": " + eqPrint);
            i++;
        }
    }

    public void inspectWornEquipment(Scanner sc, PlayerCharacter pc) {
        // Forced loop until user have exited their inventory or chosen to display a
        // specific item.
        while (true) {
            Utility.clearConsole();
            printEquipment();
            System.out.print("\nPress 0 to exit. \nEnter the number of the item to inspect: ");

            int input = Utility.checkIfNumber(sc);

            if (input == 0) {
                // temp since I don't know how to phrase this.
                System.out.println("--- exiting inspection of gear ---");
                Utility.promptEnterKey(sc);
                return;
            }

            if (input > 0 && input <= equipmentCollection.size()) {
                // selects the list based on the order of printed equipments.
                EquipmentType selectedType = EquipmentType.values()[input - 1];
                Equipment selectedEquipment = equipmentCollection.get(selectedType);

                if (selectedEquipment == null) {
                    System.out.println("No item equipped in " + selectedType.name() + ".");
                    Utility.promptEnterKey(sc);
                    continue;
                }

                selectedEquipment.displayItem();
                promptUnequip(sc, selectedType, pc);
                Utility.promptEnterKey(sc);

            } else {
                System.out.println("Invalid choice. Please try again.");
                Utility.promptEnterKey(sc);
            }
        }
    }
    // endregion
}
