package GameObjects.Items;

import GameObjects.Entities.Entity;

public class HealingEffect implements Effect {
    private final int healingValue;

    public HealingEffect(int healingValue) {
        this.healingValue = healingValue;
    }

    @Override
    public void apply(Entity actor) {
        actor.heal(healingValue);
    }

    @Override
    public int getValue() {
        return healingValue;
    }
}