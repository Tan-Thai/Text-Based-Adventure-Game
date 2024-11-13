package GameObjects.Entities;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import Resources.Config;

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

    public Zone getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(Zone zone) {
        this.currentZone = zone;
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
            System.out.println(
                    "\n1. Open inventory." +
                            "\n2. Inspect your equipped gear." +
                            "\n0. Exit inspection.");

            switch (Utility.checkIfNumber(sc)) {
                case 0:
                    inspecting = false;
                    break;
                case 1:
                    getInventory().inspectInventory(sc, this);
                    break;
                case 2:
                    getEquipmentList().inspectWornEquipment(sc, this);
                default:
                    System.out.println("Please input one of the shown options.");
                    break;
            }
        }
    }

    // TODO: Streamline location for level. and implement "retire" from tavern.
    public void checkGameClearCondition() {
        if (getLevel() == Config.PC_RETIREMENT_LEVEL) {
            System.out.println("You have reached high enough level and can now retire in the Tavern!");
            // easy to create an if check of printing the option if player is x(max) level inside the tavern.
        }
        // "else if" x boss is dead, but we can prolly add a specific method for when it dies.
    }

    // CURRENTLY not in use in combat at all and only connected to item usage.
    // override method do include a game over check for when the player specifically took damage.
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
            System.out.println(getName() + " took " + damageValue + " damage. Current health: " + health);
        }
    }

}
