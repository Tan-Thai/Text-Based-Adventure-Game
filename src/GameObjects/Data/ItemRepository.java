package GameObjects.Data;

import GameObjects.Items.*;

import java.util.HashMap;
import java.util.Map;

public class ItemRepository {
        private static final Map<ItemId, Item> ITEMS = new HashMap<>();

        static {
                /*
                 * Items instanced within hashmap to be the main reference for the cloning of
                 * object.
                 * Reason being that the reference ID need to match for items to be able to
                 * stack due to Key values.
                 * 
                 * Adding an item is done through, as an example;
                 * getInventory().addItem(ItemRepository.getItemById(ItemId.HEALTH_POTION));
                 * 
                 * We call inventory, addItem gets the item object from this HashMap, with the
                 * enum as the KEY.
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
                // endregion

                // region Potions generated
                ITEMS.put(ItemId.POISON_POTION, new Potion(
                                "Totally a Health Potion",
                                "Chug for ouch",
                                new DamageEffect(7),
                                30));

                ITEMS.put(ItemId.HEALTH_POTION, new Potion(
                                "Health Potion",
                                "Chug when ouch",
                                new HealingEffect(5),
                                20));
                // endregion
        }

        public static Item getItemById(ItemId itemId) {
                return ITEMS.get(itemId);
        }
}
