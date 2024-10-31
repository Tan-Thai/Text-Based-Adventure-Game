import GameObjects.Entites.HostileCharacter;
import GameObjects.Entites.PlayerCharacter;
import GameObjects.Items.Item;
import GameObjects.Items.Potion;
import GameObjects.Worlds.*;
import GameObjects.Worlds.Zones.Area;
import Global.*;
import Global.Utility.Slowprint;

import java.util.Scanner;
import java.text.MessageFormat; 

public class Main {
    public static void main(String[] args) {

        // currently just slapping everything into main - will create a "game" package later to put all game logic into.
        Scanner sc = new Scanner(System.in);
        PlayerCharacter pc = setupUser(sc);
        System.out.printf("Your name is %s and your current health is %d",
                pc.getName(), pc.getHealth());
        Utility.promptEnterKey(sc);

        Item sword = new Item("A Simple Sword", "Your standard blade as a new adventurer.");
        Potion potion = new Potion("Health Potion", "Chug when ouch", 5, true);
        Potion poison = new Potion("Totally a Health Potion", "Chug for ouch", 7, false);

    //      MESSING AROUND WITH ZONES AND TRAVEL
        Zone room = new Zone();
        room.displayCurrentZone(pc, pc.getCurrentZone());
        while (Utility.checkGameOver() == false) {
            Utility.clearConsole();

            Slowprint.sp("Choose an action:");
            System.out.println("1. Wander (travel inside zone)");
            System.out.println("2. Look around (display current zone)");
            System.out.println("3. Travel (travel between zones)");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    room.travelInsideZone(pc, pc.getCurrentZone());
                    break;
                case 2:
                    room.displayCurrentZone(pc, pc.getCurrentZone());
                    break;
                case 3:
                    room.zoneTravel(pc, pc.getCurrentZone(), sc);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


        HostileCharacter goblin = new HostileCharacter("Goblin", 6);
 /* 
        pc.getInventory().addItem(sword);
        pc.getInventory().addItem(potion);
        pc.getInventory().addItem(poison);

        pc.getInventory().inspectInventory(sc, pc);

        pc.getInventory().removeItem(sword);

        pc.setHealth(pc.getHealth() - 10);
        Utility.clearConsole();
        pc.displayHealth();
        pc.displayStats();
        pc.levelUp();
        Utility.promptEnterKey(sc);

        goblin.displayStats();
        goblin.displayHealth();
*/
        sc.close();
    }

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = Utility.checkIfValidString(sc);
        System.out.print("What is your health?: ");
        int healthInput = Utility.checkIfNumber(sc);

        // temp input of stats since i got no idea what we are basing it on.
        return new PlayerCharacter(nameInput, healthInput, 9, 12, 15);
    }
}