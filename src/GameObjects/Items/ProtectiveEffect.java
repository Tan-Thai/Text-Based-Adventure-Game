package GameObjects.Items;

import GameObjects.Entities.Entity;

public class ProtectiveEffect implements Effect {
	private final int protectiveValue;

	public ProtectiveEffect(int protectiveValue) {
		this.protectiveValue = protectiveValue;
	}

	@Override
	public int getValue() {
		return protectiveValue;
	}

	@Override
	public void apply(Entity actor) {
		System.out.println("You attempted to apply the effect of a items with armor type, this isn't possible.");
	}
}
