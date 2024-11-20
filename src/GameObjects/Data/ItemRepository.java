package GameObjects.Data;

import GameObjects.Items.DamageEffect;
import GameObjects.Items.Equipment;
import GameObjects.Items.EquipmentType;

public class ItemRepository {
    public static Equipment getRustyLongsword() {
        return new Equipment("Rusty Longsword",
                "An old and rusty longsword barely sharp enough to cut butter",
                EquipmentType.WEAPON,
                new DamageEffect(1),
                30);
    }

    public static Equipment getMetioricIronLongsword() {
        return new Equipment(
                "Metioric Iron longsword",
                "A longsword made of metioric iron, fallen from the stars, sharp as a razor",
                EquipmentType.WEAPON,
                new DamageEffect(2),
                50);
    }

    public static Equipment getGreataxe() {
        return new Equipment(
                "Greataxe",
                "A Greataxe, its simply a great axe",
                EquipmentType.WEAPON,
                new DamageEffect(3),
                120);
    }

}
