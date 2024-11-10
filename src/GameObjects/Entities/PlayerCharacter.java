package GameObjects.Entities;

import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;

import java.util.Scanner;

public class PlayerCharacter extends Entity {
    private int experience;
    private Zone currentZone; // stores the current zone you are in
    // private Room location = blabla
    // wondering if the player or even entity needs a room to "spawn" in let alone know where it's at in the world map.

    public PlayerCharacter(String name, int health, int str, int dex, int intel) {
        super(name, health, 1, str, dex, intel);

        currentZone = ZoneManager.getZone(ZoneType.TAVERN);
    }

    public int getExperience() {
        return experience;
    }

    public void gainExperience(int exp) {
        experience += exp;
        System.out.println("You gained " + exp + " EXP!");
        while (experience >= 100) {
            experience -= 100;
            levelUp();
            checkGameClearCondition();
        }
        System.out.println("\nYour current experience is: " + experience + "/100");
    }

    @Override
    public void inspectEntity(Scanner sc) {
        boolean inspecting = true;

        while (inspecting) {
            Utility.clearConsole();
            displayStats();
            System.out.println("\n1. Open inventory.\n0. Exit inspection.");
            switch (Utility.checkIfNumber(sc)) {
                case 0:
                    inspecting = false;
                    break;
                case 1:
                    getInventory().inspectInventory(sc, this);
                    break;
                default:
                    System.out.println("Please input one of the shown options.");
                    break;
            }
        }
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(Zone zone) {
        this.currentZone = zone;
    }

    public void checkGameOverCondition() {
        if (!isAlive()) {
            System.out.println("you died blabla.");
            //Call on a method within Game file due to not having access to scanner
            //Would also be more fitting due to it handling the game flow and not the player itself.
        } else {
            System.out.println("You died to idk, rolling a 1 on an event or something.");
        }
    }

    public void checkGameClearCondition() {
        if (getLevel() == 10) { //or x boss is dead, but we can prolly add a specific method for when it dies.
            System.out.println("Ya won woop.");
        }
    }

    // override method do include a game over check for when the player specifically took damage.
    @Override
    public void takeDamage(int damageValue) {
        if (damageValue < 0) {
            throw new IllegalArgumentException("Damage value must be positive");
        }
        health -= damageValue;
        if (!isAlive()) {
            health = 0;
            System.out.println(getName() + " took " + damageValue + " damage. " + getName() + " is dead.");
            checkGameOverCondition();
        } else {
            System.out.println(getName() + " took " + damageValue + " damage. Current health: " + health);
        }
    }

}
