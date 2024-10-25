package Entities;

public class PlayerCharacter extends Entity {
    private int level = 0;
    private int experience;
    private Inventory inventory;

    public PlayerCharacter(String name, int health) {
        super(name, health);
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void levelUp(){
        this.level++;
        System.out.println(this.getName() + " just reached level: " + this.level);
    }
}
