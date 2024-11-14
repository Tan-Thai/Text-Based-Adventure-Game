package GameObjects.Entities;

public class NeutralCharacter extends Entity {

    public NeutralCharacter(String name, int health) {
        super(name, health, 1);
    }

    // neutral npc's such as shopkeeper, quest giver, non-combative people.
    // Functions such as interaction, shop, and the like will be in this file
    private void setupShopkeeper() {
        setCurrency(999);
        setHealth(1000);

        // populate inventory.
    }
}
