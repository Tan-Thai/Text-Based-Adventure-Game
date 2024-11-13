package GameObjects.Worlds;

import GameObjects.Data.Info;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Interactions.Combat;
import Interactions.Encounter;
import Interactions.EncounterHandler;
import Interactions.ExploreZone;
import Interactions.InterZoneTravel;
import Resources.Config;

import java.util.*;

import Core.GameState;
import Core.GameStateManager;

public class Zone {
    public static Scanner sc = new Scanner(System.in);
    private final String name;
    private final String description;
    public final ZoneType zoneType;
        private boolean zoneCleared;
        public int zoneClearThreshold;
        private Set<Zone> traveableZones = new HashSet<>();
        private List<Encounter> encounters = new ArrayList<>();
    
        /**
         * Constructor for Zones, does not populate the set of travelable zones.
         *
         * @param name
         * @param desc
         * @param zoneCleared
         * @param zoneType
         * @param encounters
         */
        public Zone(String name, String desc, boolean zoneCleared, ZoneType zoneType, List<Encounter> encounters,
                int zoneClearThreshold) {
            this.name = name;
            this.description = desc;
            this.zoneCleared = zoneCleared;
            this.zoneType = zoneType;
        this.encounters = encounters;
        this.zoneClearThreshold = zoneClearThreshold;
    }

    public void setTravelableZones(Set<Zone> traveableZones) {
        this.traveableZones = traveableZones;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public ZoneType getZoneType() {
            return zoneType;
        }
    
        public void setEncounters(List<Encounter> encounters) {
            this.encounters = encounters;
        }
    
        public boolean getZoneCleared() {
            return zoneCleared;
        }
    
        public void setZoneCleared(boolean zoneCleared) {
            this.zoneCleared = zoneCleared;
        }
    
        public Set<Zone> getTraveableZones() {
            return traveableZones;
        }
    
        /**
         * Function returning true if any encounter isCleared returns false.
         *
         * @return
         */
        public boolean hasUnclearedEncounters() {
            for (Encounter encounter : encounters) {
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
        public Encounter getUnclearedEncounter() {
            for (Encounter encounter : encounters) {
                if (!encounter.isCleared()) {
                    return encounter;
                }
            }
            System.out.println("Couldn't find any uncleared encounters within zone: " + this.name + "." +
                    "Returned null for now.");
            return null;
        }
    
        public int getUnclearedEncountersAmount() {
    
            int i = 0;
    
            for (Encounter encounter : encounters) {
                if (!encounter.isCleared()) {
                    i++;
                }
            }
    
            return i;
        }
        
        public void adventureMenu(PlayerCharacter pc, Scanner sc) {
            System.out.println("Debug check PC currentzone:  " + pc.getCurrentZone().getZoneType());
            Utility.promptEnterKey(sc);
    
            Utility.clearConsole();
            Utility.slowPrint("You are in the " + pc.getCurrentZone().getName());
            Utility.slowPrint("Choose an action:");
            System.out.println(
                    "1. Wander (travel inside zone)" +
                            "\n2. Look around (display current zone)" +
                            "\n3. Inspect yourself" + // Character sheet.
                            "\n4. Travel (travel between zones)" +
                            "\n5. Remind me how to play again.");
            if (pc.getCurrentZone().getZoneType() == ZoneType.TAVERN) {
                System.out.println("6. Tavern menu (to rest and shop for items)");
            }
            int choice = Utility.checkIfNumber(sc);
    
            switch (choice) {
                case 1:
                    ExploreZone.exploreZone(pc, this, sc);
                    break;
                case 2:
                    displayCurrentZone(pc);
                    break;
                case 3:
                    pc.inspectEntity(sc);
                    break;
                case 4:
                    InterZoneTravel.zoneTravel(pc, this, sc);
                    break;
                case 5:
                    Info.howToPlay(sc);
                    break;
                case 6:
                    if (pc.getCurrentZone().getZoneType() == ZoneType.TAVERN) {
                        ((Tavern) ZoneManager.getZone(ZoneType.TAVERN)).tavernMenu(pc); // cast to tavern to access tavern
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
    
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
    
        }
    
        public Zone displayTraveableZones(PlayerCharacter pc) { 
                                                                
            Utility.clearConsole();
    
            int index = 1;
    
            System.out.println("Travelable Zones:");
            Iterator<Zone> zones = traveableZones.iterator();
            while (zones.hasNext()) { // display traveable zones
                System.out.println(index + ". " + zones.next().getName());
                index++;
            }
    
            System.out.println("Enter the number of the zone you want to travel to: ");
            int choice = Utility.checkIfNumber(sc);
    
            if (choice > 0 && choice <= traveableZones.size()) { // check if choice is valid
                Zone[] zonesArray = traveableZones.toArray(Zone[]::new); // make array of traveablezones Set to be able to
                // // index it for selection
                Zone selectedZone = zonesArray[choice - 1]; // select zone to travel to, index - 1.
                if (pc.getCurrentZone().equals(selectedZone)) { // check if player is already in the selected zone
                    Utility.clearConsole();
                    Utility.slowPrint("You return to the " + selectedZone.getName());
                    return pc.getCurrentZone(); // Put pc back in same zone if already there.
                }
                Utility.clearConsole();
                Utility.slowPrint("You travel to the " + selectedZone.getName());
                return selectedZone;
            } else { // error handling for invalid choice
                System.out.println("Invalid choice. Please try again. Or: ");
                Utility.promptEnterKey(sc);
                return displayTraveableZones(pc); // Recursively call the method again for a valid choice
            }
        }
    
        public void displayCurrentZone(PlayerCharacter pc) { // Just displays the current zone and its description + if it's
            // cleared or not.
            Utility.clearConsole();
            Utility.slowPrint("You are in " + pc.getCurrentZone().getName() + ". " + pc.getCurrentZone().getDescription()
                    + " Zone cleared: " + pc.getCurrentZone().getZoneCleared());
            Utility.promptEnterKey(sc);
        }
        
        public static void addTraveableZone(Zone zone, ZoneType zonetype) { // Adds traveable zones to a zone.
            if (zone != null) {
                zone.getTraveableZones().add(ZoneManager.getZone(zonetype));
            }
        }
    
        public void checkTraveableZones(PlayerCharacter pc) { // adds traveable zones based on cleared zones.
            
        if ((getZoneType() == ZoneType.FOREST && getZoneCleared()) || (getZoneType() == ZoneType.SWAMP && getZoneCleared())) {
            addTraveableZone(this, ZoneType.CAVE);
            addTraveableZone(ZoneManager.getZone(ZoneType.TAVERN), ZoneType.CAVE);
        }

        if (getZoneType() == ZoneType.CAVE && getZoneCleared()) {
            addTraveableZone(this, ZoneType.BASEMENT);
            addTraveableZone(ZoneManager.getZone(ZoneType.TAVERN), ZoneType.BASEMENT);
            addTraveableZone(ZoneManager.getZone(ZoneType.FOREST), ZoneType.BASEMENT);
            addTraveableZone(ZoneManager.getZone(ZoneType.SWAMP), ZoneType.BASEMENT);
        }

    }

}
