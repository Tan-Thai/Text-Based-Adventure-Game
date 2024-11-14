package Interactions;

import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.Basement;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.Utility;
import GameObjects.Worlds.Zone;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class InterZoneTravel {
    // Travel between zones method,
    public static void zoneTravel(PlayerCharacter pc, Zone zone, Scanner sc) {

        Utility.clearConsole();

        if (zone.getZoneCleared() == true) {
            pc.setCurrentZone(displayTraveableZones(pc, zone.getTraveableZones(), sc));
            // dirty bossfight check
            if (zone.getZoneType() == ZoneType.BASEMENT) {
                Adventure.adventureMenu(pc, sc, ((Basement) zone));
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

    public static Zone displayTraveableZones(PlayerCharacter pc, Set<Zone> traveableZones, Scanner sc) {

        Utility.clearConsole();

        int index = 1;

        System.out.println("Travelable Zones:");
        Iterator<Zone> zones = traveableZones.iterator(); 
        while (zones.hasNext()) {
            System.out.println(index + ". " + zones.next().getName());
            index++;
        }

        System.out.println("Enter the number of the zone you want to travel to, Or 0 to go back: ");
        int choice = Utility.checkIfNumber(sc);
        // check if choice is valid, make array of traveable zones to be able to index it for selection
        if (choice > 0 && choice <= traveableZones.size()) {
            Zone[] zonesArray = traveableZones.toArray(Zone[]::new); 
            Zone selectedZone = zonesArray[choice - 1]; 
            Utility.clearConsole();
            Utility.slowPrint("You travel to the " + selectedZone.getName());
            return selectedZone;
        } else if (choice == 0) {
            return pc.getCurrentZone();
        // error handling for invalid choice
        } else { 
            System.out.println("Invalid choice. Please try again.");
            Utility.promptEnterKey(sc);
            // Recursively call the method again for a valid choice
            return displayTraveableZones(pc, traveableZones, sc); 
        }
        
        
    }

     // Adds traveable zones to a zone.
     public static void addTraveableZone(Zone zone, ZoneType zonetype) { 
        if (zone != null) {
            zone.getTraveableZones().add(ZoneManager.getZone(zonetype));
        }
    }
    // Adds traveable zones based on cleared zones.
    public static void checkTraveableZones(PlayerCharacter pc, Zone zone) { 

        if ((zone.getZoneType() == ZoneType.FOREST && zone.getZoneCleared())
                || (zone.getZoneType() == ZoneType.SWAMP && zone.getZoneCleared())) {
            addTraveableZone(zone, ZoneType.CAVE);
            addTraveableZone(ZoneManager.getZone(ZoneType.TAVERN), ZoneType.CAVE);
        }

        if (zone.getZoneType() == ZoneType.CAVE && zone.getZoneCleared()) {
            addTraveableZone(zone, ZoneType.BASEMENT);
            addTraveableZone(ZoneManager.getZone(ZoneType.TAVERN), ZoneType.BASEMENT);
            addTraveableZone(ZoneManager.getZone(ZoneType.FOREST), ZoneType.BASEMENT);
            addTraveableZone(ZoneManager.getZone(ZoneType.SWAMP), ZoneType.BASEMENT);
        }

    }

}
