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
        actor.setArmour(protectiveValue);
    }

    @Override
    public String getEffectType() {
        return "Armour";
    }
}
