package GameObjects.Items;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;

public class DamageEffect implements Effect {
    private final int damageValue;

    public DamageEffect(int damageValue) {
        this.damageValue = damageValue;
    }

    @Override
    public void apply(Entity actor) {
        actor.takeDamage(damageValue);
    }
}