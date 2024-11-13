package Interactions;

import java.util.Scanner;

import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import GameObjects.Worlds.Zone;

public class ExploreZone {

     public static void exploreZone(PlayerCharacter pc, Zone zone, Scanner sc) { // Wander/explore inside zone function.

        if (zone.getZoneType() == ZoneType.TAVERN) {
            Utility.slowPrint("You can't explore the tavern, you're already here.");
            return;
        }
        
        Utility.clearConsole();

        Utility.slowPrint("You wander around the " + zone.getName());

        if (zone.getUnclearedEncounter() == null) {
            Utility.slowPrint(
                    "You wander the area, but the roads are known to you, and the lands are peaceful, there are no more adventures to be had for you here.");
        } else if (zone.getUnclearedEncounter().isCombatEncounter()) {
            Combat.getInstance().initiateCombat(pc, zone.getUnclearedEncounter().getEnemy(), sc);
            zone.getUnclearedEncounter().checkClearedState();
        } else {
            EncounterHandler.getInstance().runEncounter(pc, zone.getUnclearedEncounter(), sc);
        }

        if (zone.zoneClearThreshold >= zone.getUnclearedEncountersAmount()) {
            zone.setZoneCleared(true);
            zone.checkTraveableZones(pc);
        }
    }


}
