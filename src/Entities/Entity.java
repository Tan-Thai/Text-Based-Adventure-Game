package Entities;

public class Entity {
    private String name;
    private int health;

    public Entity(String name, int health){
        this.name = name;
        this.health = health;
    }

    public String getName() {return name;}
    public int getHealth() {return health;}

    public void setName(String name){
        this.name = name;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void display() {
        System.out.println("Current hp is: " + this.health);
    }
}
