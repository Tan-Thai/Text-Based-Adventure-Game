package GameObjects.Data;

import GameObjects.Items.*;

import java.util.HashMap;
import java.util.Map;

public class ItemRepository {
    private static final Map<String, Item> ITEMS = new HashMap<>();

    // example static final
    public static final String POISON_POTION = "poison_potion";

    static {
        /*
        add the items we want into the HashMap to act as our database of items generated.
        The ID will always be lowercase and use '_' as spacer.
        This can be sorted by either creating an itemID enum that we make use of to avoid typo.
        or we create a private static final variable = "string" to call on that variable instead of writing the string.
        */

        //region Equipments generated
        ITEMS.put("rusty_longsword", new Equipment(
                "Rusty Longsword",
                "An old and rusty longsword barely sharp enough to cut butter",
                EquipmentType.WEAPON,
                new DamageEffect(1),
                30));

        ITEMS.put("metioric_iron_longsword", new Equipment(
                "Metioric Iron longsword",
                "A longsword made of metioric iron, fallen from the stars, sharp as a razor",
                EquipmentType.WEAPON,
                new DamageEffect(2),
                50));

        ITEMS.put("great_axe", new Equipment(
                "Great-axe",
                "A Great-axe, its simply a great axe",
                EquipmentType.WEAPON,
                new DamageEffect(3),
                120));
        //endregion

        //region Potions generated
        ITEMS.put(POISON_POTION, new Potion(
                "Totally a Health Potion",
                "Chug for ouch",
                new DamageEffect(7),
                30));

        ITEMS.put("health_potion", new Potion(
                "Health Potion",
                "Chug when ouch",
                new HealingEffect(5),
                20));
        //endregion
    }

    public static Item getItemById(String id) {
        return ITEMS.get(id);
    }
}
