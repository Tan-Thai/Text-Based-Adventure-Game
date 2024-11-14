package Interactions;

import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.Basement;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import GameObjects.Worlds.Zone;
import java.util.Scanner;

public class InterZoneTravel {
    // Travel between zones method,
    public static void zoneTravel(PlayerCharacter pc, Zone zone, Scanner sc) {

        Utility.clearConsole();

        if (zone.getZoneCleared() == true) {
            pc.setCurrentZone(zone.displayTraveableZones(pc));
            // dirty bossfight check
            if (zone.getZoneType() == ZoneType.BASEMENT) {
                ((Basement) zone).adventureMenu(pc, sc);
            }
            // allows player to backtrack to tavern
        } else if (zone.getZoneCleared() == false
                && zone.getZoneType() != ZoneType.TAVERN) {
            Utility.slowPrint(
                    "You have not cleared this zone yet. However, do you want to backtrack to the tavern?\nPress Y for yes and N for no");
            if (Utility.checkYesOrNo(sc)) {
                pc.setCurrentZone(ZoneManager.getZone(ZoneType.TAVERN));
            }

        }

    }

}
