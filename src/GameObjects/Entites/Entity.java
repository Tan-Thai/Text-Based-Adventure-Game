package GameObjects.Entites;

public class Entity {
    private final String name;
    private int health;
    private int maxHealth;
    private int level;
    private int strength;
    private int dexterity;
    private int intelligence;

    // constructor for all entities that requires specific stats setups (enemies, or the player char)
    public Entity(String name, int health, int level, int str, int dex, int intelligence) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        this.strength = str;
        this.dexterity = dex;
        this.intelligence = intelligence;
    }

    // constructor for our npc's that don't need a stat (shopkeeper or random villager.)
    public Entity(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        this.strength = 10;
        this.dexterity = 10;
        this.intelligence = 10;
    }

    public String getName() {return name;}
    public int getHealth() {return health;}
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getLevel() {
        return level;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public boolean isFullHP() {
        return health >= maxHealth;
    }

    public void displayHealth() {
        System.out.println("Current hp is: " + this.health);
    }

    public void displayStats() {
        System.out.println(name + "'s stats: ");
        System.out.println("Strength: " + strength);
        System.out.println("Dexterity: " + dexterity);
        System.out.println("Intelligence: " + intelligence);
    }

    public void levelUp() {
        this.level++;
        System.out.println(this.getName() + " just reached level: " + this.level);
    }

    public void changeHealth(int incomingValue) {
        health += incomingValue;
    }
}
