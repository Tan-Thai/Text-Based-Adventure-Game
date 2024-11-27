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
        if (zone.getZoneType() == ZoneType.TAVERN) {
            Info.adventureMenuTavernPrint();
        } else {
            Info.adventureMenuPrint();
        }
        System.out.print(Config.MENU_CHOICE_STRING);
        int choice = Utility.checkIfNumber(sc);

        switch (choice) {
            case 1:
                if (zone.getZoneType() == ZoneType.TAVERN) {
                    ((Tavern) ZoneManager.getZone(ZoneType.TAVERN)).tavernMenu(pc);
                    break;
                } else {ExploreZone.exploreZone(pc, zone, sc);}            
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
            case 0:
                System.out.println("Are you sure you want to exit to main menu? ALL YOUR PROGRESS WILL BE LOST (Y/N)");
                if (Utility.checkYesOrNo(sc)) {
                    System.out.println("Exiting to main menu...");
                    GameStateManager.getInstance().setCurrentState(GameState.EXIT);
                    Utility.clearConsole();
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
