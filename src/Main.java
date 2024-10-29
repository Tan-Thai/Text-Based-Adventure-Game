import GameObjects.Entites.HostileCharacter;
import GameObjects.Entites.PlayerCharacter;
import GameObjects.Items.Item;
import GameObjects.Items.Potion;
import GameObjects.Worlds.*;
import GameObjects.Worlds.Zones.Area;
import Global.*;

import java.util.Scanner;
import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {

        // currently just slapping everything into main - will create a "game" package later to put all game logic into.
        Scanner sc = new Scanner(System.in);
        PlayerCharacter pc = setupUser(sc);
        System.out.println(MessageFormat.format("Your name is {0} and your current health is {1}",
                pc.getName(), pc.getHealth()));

        Item sword = new Item("A Simple Sword", "Your standard blade as a new adventurer.");
        Potion potion = new Potion("Health Potion", "Chug when ouch", 5, true);
        Potion poison = new Potion("Totally a Health Potion", "Chug for ouch", 7, false);

    //      MESSING AROUND WITH ZONES AND TRAVEL
        Tavern tavern = new Tavern();
        Basement basement = new Basement();
        Zone room = new Zone();
        System.out.println("Current Zone: " + pc.getCurrentZone());
        
        tavern.takeRest(pc);
        tavern.openShop(pc);
        tavern.setOut(pc, Area.FOREST);

        room.displayCurrentZone(pc, pc.getCurrentZone()); //TESTING IF TRAVEL WORKS
        
        room.travelInsideZone(pc, pc.getCurrentZone());

        room.displayCurrentZone(pc, pc.getCurrentZone()); //TESTING IF TRAVEL WORKS
        
        room.zoneTravel(pc, pc.getCurrentZone());

        room.displayCurrentZone(pc, pc.getCurrentZone());

        room.travelInsideZone(pc, pc.getCurrentZone());

        room.zoneTravel(pc, pc.getCurrentZone());

        room.displayCurrentZone(pc, pc.getCurrentZone());

        room.travelInsideZone(pc, pc.getCurrentZone());

   //     room.zoneTravel(pc, pc.getCurrentZone());
   //     room.travelInsideZone(pc, pc.getCurrentZone());
   //     room.zoneTravel(pc, pc.getCurrentZone());

   //     System.out.println("Current Zone: " + pc.getCurrentZone());

   //     room.zoneTravel(pc, pc.getCurrentZone());
        
        

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