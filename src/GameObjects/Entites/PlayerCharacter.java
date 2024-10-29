package GameObjects.Entites;

import GameObjects.Items.Inventory;

public class PlayerCharacter extends Entity {
    private int experience;
    private final Inventory inventory;
    private String currentZone = "Tavern"; // stores the current zone you are in
    // private Room location = blabla
    // wondering if the player or even entity needs a room to "spawn" in let alone know where it's at in the world map.

    public PlayerCharacter(String name, int health, int str, int dex, int intel) {
        super(name, health, 1, str, dex, intel);
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(String zone) {
        this.currentZone = zone;
    }

}
