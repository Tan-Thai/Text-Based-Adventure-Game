package GameObjects.Items;

import java.util.HashMap;

// Really temporary classname due to me not being sure how to work with this.
public class EquipmentManager {
    private final HashMap<EquipmentSlot, Equipment> equipment;

    public EquipmentManager() {
        this.equipment = new HashMap<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            equipment.put(slot, null);
        }
    }

    public Equipment getEquipment(EquipmentSlot slot) {
        return equipment.get(slot);
    }

    public Equipment equipItem(Equipment eq) {
        EquipmentSlot eqSlot = eq.getEQSlot();
        Equipment previousEQ = equipment.get(eqSlot);
        equipment.put(eqSlot, eq);

        if (previousEQ != null) {
            System.out.println("You put " + previousEQ.getName() + " in your inventory.");
        }
        return previousEQ;
    }

    public Equipment unequipItem(EquipmentSlot slot) {
        if (equipment.containsKey(slot)) {
            Equipment removedItem = equipment.get(slot);
            equipment.put(slot, null);
            return removedItem;
        }
        return null;
    }
}
