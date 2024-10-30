package GameObjects.Entites;

import GameObjects.Items.Inventory;

public class PlayerCharacter extends Entity {
    private int experience;
    private final Inventory inventory;
    // private Room location = blabla
    // wondering if the player or even entity needs a room to "spawn" in let alone know where it's at in the world map.

    public PlayerCharacter(String name, int health, int str, int dex, int intel) {
        super(name, health, 1, str, dex, intel);
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void gainExperience(int exp) {
        experience += exp;
        System.out.println(experience);
        if (experience > 100) {
            experience -= 100;
            levelUp();
            System.out.println("EXP after level up" + experience);
        }
    }

}
