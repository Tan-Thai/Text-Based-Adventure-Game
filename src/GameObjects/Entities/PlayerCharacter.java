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

}
