package GameObjects;

public class Entity {
    private final String name;
    private int health;
    private int level;

    public Entity(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    public String getName() {return name;}
    public int getHealth() {return health;}

    public int getLevel() {
        return level;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void displayHealth() {
        System.out.println("Current hp is: " + this.health);
    }

    public void levelUp() {
        this.level++;
        System.out.println(this.getName() + " just reached level: " + this.level);
    }
}
