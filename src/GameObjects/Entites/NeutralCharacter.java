package GameObjects.Entites;

public class NeutralCharacter extends Entity {

    public NeutralCharacter(String name, int health) {
        super(name, health, 1);
    }
    // neutral npc's such as shopkeeper, quest giver, non-combative people.
    // Functions such as interaction, shop, and the like will be in this file
}
