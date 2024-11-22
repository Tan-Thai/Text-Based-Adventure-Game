package GameObjects.Items;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;

public interface Consumable {
    void consume(Entity player);
}
