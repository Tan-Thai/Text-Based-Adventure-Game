package GameObjects.Data;

import GameObjects.Items.DamageEffect;
import GameObjects.Items.Equipment;
import GameObjects.Items.EquipmentType;
import GameObjects.Items.ProtectiveEffect;
import GameObjects.Items.Weapon;
import GameObjects.Items.WeaponType;

public class EquipmentRepository {

	public static Weapon getShortSpear() {
		return new Weapon(
				"Short Spear",
				"A short spear, typically used when hunting boar.",
				new DamageEffect(1),
				20,
				WeaponType.DEFAULT);
	}

	public static Weapon getUndefinedWeapon() {
		return new Weapon("Weapon Name Placeholder",
				"Description of a weapon",
				new DamageEffect(
						0),
				0,
				WeaponType.DEFAULT);
	}
}
