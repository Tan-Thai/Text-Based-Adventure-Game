package Interactions;

import java.util.Scanner;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Data.Info;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.Tavern;
import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import Resources.Config;

public class Adventure {

    public static void adventureMenu(PlayerCharacter pc, Scanner sc, Zone zone) {

        Utility.clearConsole();
        Zone.displayCurrentZone(zone);
        System.out.println(
                "\n1. Wander (travel inside zone)" +
                "\n2. Inspect yourself" + 
                "\n3. Travel (travel between zones)" +
                "\n4. Remind me how to play again.");
        if (zone.getZoneType() == ZoneType.TAVERN) {
            System.out.println("5. Tavern menu (to rest and shop for items)");
        }   // ADD 0 option
        System.out.println("0. Exit to main menu");
        System.out.print(Config.MENU_CHOICE_STRING);
        int choice = Utility.checkIfNumber(sc);

        switch (choice) {
            case 1:
                ExploreZone.exploreZone(pc, zone, sc);
                break;
            case 2:
                pc.inspectEntity(sc);
                break;
            case 3:
                InterZoneTravel.zoneTravel(pc, zone, sc);
                break;
            case 4:
                Info.howToPlay(sc);
                break;
            case 5:
                if (zone.getZoneType() == ZoneType.TAVERN) {
                    ((Tavern) ZoneManager.getZone(ZoneType.TAVERN)).tavernMenu(pc); 
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
                break;
            case 0:
                System.out.println("Are you sure you want to exit to main menu? ALL YOUR PROGRESS WILL BE LOST (Y/N)");
                if (Utility.checkYesOrNo(sc)) {
                    System.out.println("Exiting to main menu...");
                    GameStateManager.getInstance().setCurrentState(GameState.EXIT);
                } else {
                    System.out.println("Returning to adventure...");
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }
    
}
