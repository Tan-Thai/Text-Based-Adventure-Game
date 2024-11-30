package GameObjects.Entities;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import Core.Config;

import java.util.Scanner;

public class PlayerCharacter extends Entity {
    private int experience;
    private Zone currentZone; // stores the current zone you are in.

    private String jobTitle = "";

    public PlayerCharacter(String name, String jobTitle, int health, int str, int dex, int intel) {
        super(name, health, Config.PC_STARTING_LEVEL, str, dex, intel, 150);
        currentZone = ZoneManager.getZone(ZoneType.TAVERN);
        this.jobTitle = jobTitle;
    }

    // region Getters and Setters
    public int getExperience() {
        return experience;
    }

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(Zone zone) {
        this.currentZone = zone;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    // endregion

    // region Temporary region for all methods player related
    public void gainExperience(int exp) {

        experience += exp;
        if (getLevel() < Config.PC_MAX_LEVEL) {
            System.out.println("You gained " + exp + " EXP!");
            while (experience >= Config.PC_EXPERIENCE_NEEDED_PER_LEVEL) {
                experience -= Config.PC_EXPERIENCE_NEEDED_PER_LEVEL;
                levelUp();
            }
            System.out.println(
                    "Your current experience is: " + experience + " / " + Config.PC_EXPERIENCE_NEEDED_PER_LEVEL);
        } else {
            if (experience >= Config.PC_EXPERIENCE_NEEDED_PER_LEVEL)
                experience = Config.PC_EXPERIENCE_NEEDED_PER_LEVEL;
            System.out.println("You're already as strong as you can be and cannot gain any more levels!");
        }
        checkGameClearCondition();
    }

    @Override
    public void inspectEntity(Scanner sc) {
        boolean inspecting = true;

        while (inspecting && !isDead()) {
            Utility.clearConsole();
            displayStats();
            System.out.println(
                    "\n" +
                    "1. Open inventory.\n" +
                    "2. Inspect your equipped gear.");

            System.out.print(Config.MENU_CHOICE_STRING);

            switch (Utility.checkIfNumber(sc)) {
                case 0:
                    inspecting = false;
                    break;
                case 1:
                    getInventory().inspectInventory(sc, this);
                    break;
                case 2:
                    getEquipmentList().inspectWornEquipment(sc, this);
                    break;
                default:
                    System.out.println("Please input one of the shown options.");
                    break;
            }
        }
    }

    @Override
    public void displayStats() {
        System.out.println("Name: " + getName());
        System.out.println("Class:" + getJobTitle());
        System.out.println("level: " + getLevel());
        System.out.println("Experience: " + getExperience() + " / " + Config.PC_EXPERIENCE_NEEDED_PER_LEVEL);
        System.out.println("Health: " + getHealth() + " / " + getMaxHealth());
        System.out.println("Armour: " + getArmour());
        System.out.println("Gold: " + getCurrency());

        System.out.println("\nStats:");
        System.out.println("Strength: " + strength);
        System.out.println("Dexterity: " + dexterity);
        System.out.println("Intelligence: " + intelligence);
    }

    public void checkGameClearCondition() {
        if (getLevel() == Config.PC_RETIREMENT_LEVEL) {
            System.out.println("\nYou have reached high enough level and can now retire in the Tavern!");
        }
    }

    // CURRENTLY not in use in combat at all and only connected to item usage.
    // Now also in use in combat
    // override method do include a game over check for when the player specifically
    // took damage.
    @Override
    public void takeDamage(int damageValue) {
        if (damageValue < 0) {
            throw new IllegalArgumentException("Damage value must be positive");
        }
        health -= damageValue;
        if (isDead()) {
            health = 0;
            System.out.println(getName() + " took " + damageValue + " damage. " + getName() + " is dead.");
            GameStateManager.getInstance().setCurrentState(GameState.GAME_OVER);
        } else {
            System.out.println(getName() + " took " + damageValue + " damage!");
        }
    }
    // endregion
}
