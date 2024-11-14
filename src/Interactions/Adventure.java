package Interactions;

import java.util.Scanner;

import GameObjects.Data.Info;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.Tavern;
import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;

public class Adventure {

    public static void adventureMenu(PlayerCharacter pc, Scanner sc, Zone zone) {
        System.out.println("Debug check PC currentzone:  " + pc.getCurrentZone().getZoneType());
        Utility.promptEnterKey(sc);

        Utility.clearConsole();
        Utility.slowPrint("You are in the " + zone.getName());
        Utility.slowPrint("Choose an action:");
        System.out.println(
                "1. Wander (travel inside zone)" +
                        "\n2. Look around (display current zone)" +
                        "\n3. Inspect yourself" + 
                        "\n4. Travel (travel between zones)" +
                        "\n5. Remind me how to play again.");
        if (zone.getZoneType() == ZoneType.TAVERN) {
            System.out.println("6. Tavern menu (to rest and shop for items)");
        }
        int choice = Utility.checkIfNumber(sc);

        switch (choice) {
            case 1:
                ExploreZone.exploreZone(pc, zone, sc);
                break;
            case 2:
                Zone.displayCurrentZone(zone);
                break;
            case 3:
                pc.inspectEntity(sc);
                break;
            case 4:
                InterZoneTravel.zoneTravel(pc, zone, sc);
                break;
            case 5:
                Info.howToPlay(sc);
                break;
            case 6:
                if (zone.getZoneType() == ZoneType.TAVERN) {
                    ((Tavern) ZoneManager.getZone(ZoneType.TAVERN)).tavernMenu(pc); 
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }
    
}
