package GameObjects.Data;

import GameObjects.Items.*;

import java.util.HashMap;
import java.util.Map;

public class ItemRepository {
    private static final Map<ItemId, Item> ITEMS = new HashMap<>();

    static {
        /*
          Items instanced within hashmap to be the main reference for the cloning of
          object.
          Reason being that the reference ID need to match for items to be able to
          stack due to Key values.

          Adding an item is done through, as an example;
          getInventory().addItem(ItemRepository.getItemById(ItemId.HEALTH_POTION));

          We call inventory, addItem gets the item object from this HashMap, with the
          enum as the KEY. - TT
         */

        // region Equipments generated
        ITEMS.put(ItemId.RUSTY_LONGSWORD, new Weapon(
                "Rusty Longsword",
                "An old and rusty longsword barely sharp enough to cut butter",
                new DamageEffect(1),
                30,
                WeaponType.DEFAULT));

        ITEMS.put(ItemId.METIORIC_IRON_LONGSWORD, new Weapon(
                "Metioric Iron longsword",
                "A longsword made of metioric iron, fallen from the stars, sharp as a razor",
                new DamageEffect(2),
                50,
                WeaponType.DEFAULT));

        ITEMS.put(ItemId.GREAT_AXE, new Weapon(
                "Great-axe",
                "A Great-axe, its simply a great axe",
                new DamageEffect(3),
                120,
                WeaponType.DEFAULT));

        ITEMS.put(ItemId.FLAMING_BASTARDSWORD, new Weapon(
                "Flaming bastardsword",
                "When drawn blue flames lick the blade of this sword",
                new DamageEffect(3),
                200,
                WeaponType.FIRE));

        ITEMS.put(ItemId.BLESSED_WHISPERBLADE, new Weapon(
                "The blessed whisperblade",
                "A blade blessed by the singing saints, that whispers with their voices as it cuts",
                new DamageEffect(3),
                200,
                WeaponType.HOLY));
        ITEMS.put(ItemId.RAIDIANT_WARHAMMER, new Weapon(
                "The radiant warhammer",
                "In the head of this warhammer a emberprism raidiates the light of the true sun",
                new DamageEffect(3),
                300,
                WeaponType.SUNLIGHT));
        ITEMS.put(ItemId.PATCHY_LEATHER_ARMOUR, new Equipment(
                "Patchy leather armour",
                "A broken and worn leather armor",
                EquipmentType.ARMOUR,
                new ProtectiveEffect(1),
                50));
        ITEMS.put(ItemId.TWISTED_CHAINMAIL, new Equipment(
                "Twisted chainmail",
                "a strange chainmail,were the links twist and turn in strange patterns",
                EquipmentType.ARMOUR,
                new ProtectiveEffect(2),
                100));
        ITEMS.put(ItemId.HEIRLOOM_BATTLEPLATE, new Equipment(
                "Heirloom battleplate",
                "A worn but beautful battleplate that has served many warriors before you",
                EquipmentType.ARMOUR,
                new ProtectiveEffect(3),
                200));

        // endregion

        // region Potions generated
        ITEMS.put(ItemId.POISON_POTION, new Potion(
                "Totally a 'Health Potion'",
                "Chug/Throw for ouch",
                new DamageEffect(7),
                30));

        ITEMS.put(ItemId.HEALTH_POTION, new Potion(
                "Health Potion",
                "Chug when ouch",
                new HealingEffect(5),
                20));
        ITEMS.put(ItemId.GREATER_HEALTH_POTION, new Potion(
                "Greater health Potion",
                "Chug when big ouch",
                new HealingEffect(10),
                30));
        // endregion
    }

    public static Item getItemById(ItemId itemId) {
        return ITEMS.get(itemId);
    }
}
