package GameObjects.Entities;

import GameObjects.Items.Inventory;

public class PlayerCharacter extends Entity {
    private int experience;
    // private Room location = blabla
    // wondering if the player or even entity needs a room to "spawn" in let alone know where it's at in the world map.

    public PlayerCharacter(String name, int health, int str, int dex, int intel) {
        super(name, health, 1, str, dex, intel);
    }

    public int getExperience() {
        return experience;
    }

    public void gainExperience(int exp) {
        experience += exp;
        System.out.println("You gained " + exp + " EXP!");
        if (experience > 100) {
            experience -= 100;
            levelUp();
            System.out.println("EXP after level up" + experience);
        }
    }

}
