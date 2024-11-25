package Interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import GameObjects.Worlds.Basement;
import GameObjects.Worlds.Zone;

public class ExploreZone {
    // explore inside zone function.
    public static void exploreZone(PlayerCharacter pc, Zone zone, Scanner sc) {

        if (zone.getZoneType() == ZoneType.TAVERN) {
            Utility.slowPrint("You can't explore the tavern, you're already here.");
            return;
        }

        Utility.clearConsole();

        Utility.slowPrint("You explore the " + zone.getName());

        Encounter encounter = getUnclearedEncounter(zone);

        if (encounter == null) {
            Utility.slowPrint(
                    "You wander the area, but the roads are known to you, and the lands are peaceful, there are no more adventures to be had for you here.");
        } else if (encounter.isCombatEncounter()) {
            Combat.getInstance().initiateCombat(pc, encounter.getEnemy(), sc);

            encounter.updateClearedState();
            if (zone.getZoneType() == ZoneType.BASEMENT) {
                ((Basement) zone).checkIfBossDead(zone, pc, sc);
                ((Basement) zone).endGame();
            }
        } else {
            EncounterHandler.getInstance().runEncounter(pc, encounter, sc);
        }

        if (zone.zoneClearThreshold >= getUnclearedEncountersAmount(zone)) {
            zone.setZoneCleared(true);
            InterZoneTravel.checkTraveableZones(pc, zone);
        }
    }

    /**
     * Function returning true if any encounter isCleared returns false.
     *
     * @return
     */
    public boolean hasUnclearedEncounters(Zone zone) {
        for (Encounter encounter : zone.encounters) {
            if (!encounter.isCleared()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the first encounter where getIsCleared returns false. Otherwise
     * returns null.
     *
     * @return
     */
    public static Encounter getUnclearedEncounter(Zone zone) {
        if (zone.encounters == null) {
   //         System.out.println("No encounters in zone: " + zone.getName() + ". Returned null for now.");
            return null;
        }

        ArrayList<Encounter> unclearedEncounters = new ArrayList<>();

        for (Encounter encounter : zone.encounters) {
            if (!encounter.isCleared()) {
                unclearedEncounters.add(encounter);
            }
        }

        // If for-loop above couldn't find any uncleared encounters, the if-case returns
        // null.
        if (unclearedEncounters.size() < 1) {
            System.out.println("Couldn't find any uncleared encounters within zone: " + zone.getName() + "." +
                    "Returned null for now.");
            return null;
        } else {
            Random random = new Random();

            return unclearedEncounters.get(random.nextInt(unclearedEncounters.size()));
        }

    }

    public static int getUnclearedEncountersAmount(Zone zone) {

        int i = 0;

        for (Encounter encounter : zone.encounters) {
            if (!encounter.isCleared()) {
                i++;
            }
        }

        return i;
    }

}
