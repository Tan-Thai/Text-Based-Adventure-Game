package Entites;

public class PlayerCharacter extends Entity {
    private int level = 0;
    private int experience = 0;
    private Inventory inventory;

    public PlayerCharacter(String name, int health) {
        super(name, health);
        this.inventory = new Inventory();
        this.experience = 0;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void levelUp(){
        this.level++;
        System.out.println(this.getName() + " has leveled to " + this.level + "!");
    }
}
