package GameObjects.Items;

public class Weapon extends Equipment {

	private final WeaponType weaponType;

	public Weapon(String name, String desc, Effect effect, int cost, WeaponType weaponType) {
		super(name, desc, EquipmentType.WEAPON, effect, cost);
		this.weaponType = weaponType;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}
}
