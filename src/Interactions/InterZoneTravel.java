package Interactions;

import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.Basement;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import GameObjects.Worlds.Zone;
import java.util.Scanner;

public class InterZoneTravel {

     public static void zoneTravel(PlayerCharacter pc, Zone zone, Scanner sc) { // Travel between zones method,

        Utility.clearConsole();
        
        if (zone.getZoneCleared() == true) { // checks if currentzone is cleared
            pc.setCurrentZone(zone.displayTraveableZones(pc));
            if (zone.getZoneType() == ZoneType.BASEMENT) { // dirty bossfight check
                ((Basement) zone).adventureMenu(pc, sc); 
            }

        } else if (zone.getZoneCleared() == false
                && zone.getZoneType() != ZoneType.TAVERN) { // allows player to backtrack to tavern
            Utility.slowPrint(
                    "You have not cleared this zone yet. However, do you want to backtrack to the tavern?\nPress Y for yes and N for no");
            if (Utility.checkYesOrNo(sc)) {
                pc.setCurrentZone(ZoneManager.getZone(ZoneType.TAVERN));
            }

        } 

    }
    
}
