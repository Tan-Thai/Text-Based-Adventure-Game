package GameObjects.Entities;

public class HostileCharacter extends Entity {

    public HostileCharacter(String name, int health) {
        super(name, health, 1);
    }

    // random attack method for now.
    public void attack(Entity target) {
        int damage = (int) (this.strength * 1.5);  // Example damage calculation
        target.changeHealth(-damage);
        System.out.println(this.getName() + " attacks " + target.getName() + " for " + damage + " damage!");
    }

    //Expand logic
}