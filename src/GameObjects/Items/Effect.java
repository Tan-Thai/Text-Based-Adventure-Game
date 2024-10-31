package GameObjects.Items;
import GameObjects.Entities.*;

public interface Effect {
    void apply(Entity actor);
    int getValue();
}
