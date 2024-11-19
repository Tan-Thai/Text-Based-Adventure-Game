package GameObjects.Data;

import GameObjects.Items.DamageEffect;
import GameObjects.Items.Equipment;
import GameObjects.Items.EquipmentType;
import GameObjects.Items.ProtectiveEffect;
import GameObjects.Items.Weapon;
import GameObjects.Items.WeaponType;

public class ItemRepository {
    public static Equipment getRustyLongsword() {
        return new Weapon("Rusty Longsword",
                "An old and rusty longsword barely sharp enough to cut butter",
                new DamageEffect(1),
                30,
                WeaponType.DEFAULT);
    }

    public static Weapon getMetioricIronLongsword() {
        return new Weapon(
                "Metioric Iron longsword",
                "A longsword made of metioric iron, fallen from the stars, sharp as a razor",
                new DamageEffect(2),
                50,
                WeaponType.DEFAULT);

    }

    public static Weapon getGreataxe() {
        return new Weapon(
                "Greataxe",
                "A Greataxe, its simply a great axe",
                new DamageEffect(3),
                120,
                WeaponType.DEFAULT);
    }

    public static Equipment getUndefinedArmour() {
        return new Equipment(
                "Armour Name Placeholder",
                "Description of the armour.",
                EquipmentType.ARMOUR,
                new ProtectiveEffect(0),
                20);
    }
}
