package GameObjects.Entities;

import GameObjects.Items.Inventory;

public class Entity {
    private static final int DEFAULT_STAT = 10;

    private final Inventory inventory;
    private final String name;
    protected int health;
    protected int maxHealth;
    protected int level;
    protected int strength;
    protected int dexterity;
    protected int intelligence;

    // constructor for all entities that requires specific stats setups (enemies, or the player char)
    public Entity(String name, int health, int level, int str, int dex, int intelligence) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        this.strength = str;
        this.dexterity = dex;
        this.intelligence = intelligence;
        this.inventory = new Inventory();
    }

    // Constructor with base stats (Standard NPC)
    public Entity(String name, int health, int level) {
        this(name, health, level,
                DEFAULT_STAT, DEFAULT_STAT, DEFAULT_STAT);
    }

    public Inventory getInventory() {return inventory;}
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getIntelligence() {
        return intelligence;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getLevel() {
        return level;
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

    public void changeHealth(int incomingValue) {
        health += incomingValue;
        if (health < 0) {
            health = 0;
        }
    }

    public void changeMaxHealth(int incomingValue) {
        maxHealth += incomingValue;
    }

    public void levelUp() {
        level++;
        changeMaxHealth(strength / 2);
        changeHealth(strength / 2);
        System.out.println(name + " just reached level: " + level);
        System.out.println(name + "'s max hp is now: " + maxHealth);
    }

    public void heal(int healingValue) {
        if (healingValue < 0) {
            throw new IllegalArgumentException("Healing value must be positive");
        }
        health += healingValue;
        if (health > maxHealth) {
            health = maxHealth;
        }
        System.out.println(name + " healed by " + healingValue + " points. Current health: " + health);
    }

    public void takeDamage(int damageValue) {
        if (damageValue < 0) {
            throw new IllegalArgumentException("Damage value must be positive");
        }
        health -= damageValue;
        if (!isAlive()) {
            health = 0;
            System.out.println(name + " took " + damageValue + " damage. " + name + " is dead.");
        } else {
            System.out.println(name + " took " + damageValue + " damage. Current health: " + health);
        }
    }
}
