package GameObjects.Entities;

import GameObjects.Items.EquipmentManager;
import GameObjects.Items.ItemManager;
import Global.Utility;

import java.util.Scanner;

public class Entity {

    //region Constructors and Variables
    private static final int DEFAULT_STAT = 3;

    private final EquipmentManager equipmentManager;
    private final ItemManager itemManager;
    private final String name;
    protected int health;
    protected int maxHealth;
    protected int level;
    protected int strength;
    protected int dexterity;
    protected int intelligence;
    protected int currency;

    // constructor for all entities that requires specific stats setups (enemies, or
    // the player char)
    public Entity(String name, int health, int level, int str, int dex, int intelligence, int currency) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        this.strength = str;
        this.dexterity = dex;
        this.intelligence = intelligence;
        this.currency = currency;

        // Managers handles items equipments that this entity holds/wears.
        this.itemManager = new ItemManager();
        this.equipmentManager = new EquipmentManager();
    }

    // Constructor with base stats (Standard NPC)
    public Entity(String name, int health, int level) {
        this(name, health, level,
                DEFAULT_STAT, DEFAULT_STAT, DEFAULT_STAT, 0);
    }
    //endregion

    //region Getters and Setters
    public ItemManager getInventory() {
        return itemManager;
    }

    public String getName() {
        return name;
    }

    public EquipmentManager getEquipmentList() {
        return equipmentManager;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrency() {
        return currency;
    }

    public int setCurrency(int currency) {
        return this.currency = currency;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isFullHP() {
        return health >= maxHealth;
    }
    //endregion

    //region Methods used across many entities.
    // method is planned to be able to inspect any entity and behave differently if it's a PC
    // Idea is to bake in the access to inventory through this as well.
    public void inspectEntity(Scanner sc) {
        Utility.clearConsole();
        displayStats();
        Utility.promptEnterKey(sc);
    }

    // display health and stats can be merged into 1 if we don't use display health explicitly somewhere.
    public void displayHealth() {
        System.out.println("Health Points: " + this.health + "/" + this.maxHealth);
    }

    public void displayStats() {
        System.out.println("Name: " + this.name);
        System.out.println("level: " + this.level);
        System.out.println("Health: " + this.health + " / " + this.maxHealth);

        System.out.println("Stats:");
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
        strength++;
        dexterity++;
        intelligence++;
        // Temporarily set strength to be the stat for hp.
        changeMaxHealth(strength / 2);
        changeHealth(strength / 2);

        System.out.println("\n" + name + " just reached level: " + level);
        System.out.println(name + "'s max hp is now: " + maxHealth);
    }

    // TODO implement heal/take damage into combat since it's not making use of it currently.
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

    // used to deal damage to an entity with items. EX: Drink poison, throw bomb.
    public void takeDamage(int damageValue) {
        if (damageValue < 0) {
            throw new IllegalArgumentException("Damage value must be positive");
        }
        health -= damageValue;
        if (isDead()) {
            health = 0;
            System.out.println(name + " took " + damageValue + " damage. " + name + " is dead.");
        } else {
            System.out.println(name + " took " + damageValue + " damage. Current health: " + health);
        }
    }
    //endregion
}
