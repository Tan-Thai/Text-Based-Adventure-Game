package GameObjects.Worlds;

import GameObjects.Entites.PlayerCharacter;
import Global.*;
import Global.Utility.Slowprint;
import GameObjects.Worlds.Zones.Area;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Zone extends World {
    private String name;
    private String description;
    private boolean zoneCleared;
    public Scanner sc = new Scanner(System.in);
    private Set<Area> traveableZones = new HashSet<>();
 

    public Zone(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.zoneCleared = false; 
    }

    public Zone() {
        this.name = "DEFAULT";
        this.description = "DEFAULT DESCRIPTION";
        this.zoneCleared = true;
    }

    public Zone (String name, String desc, boolean zoneCleared) { // for Tavern and Basement
        this.name = name;
        this.description = desc;
        this.zoneCleared = zoneCleared;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setZoneCleared(boolean zoneCleared) {
        this.zoneCleared = zoneCleared;
    }

    public boolean getZoneCleared() {
        return zoneCleared;
    }

    public Area displayTraveableZones(PlayerCharacter pc) {
        int index = 1;

        System.out.println("Travelable Zones:");
        for (Area zone : traveableZones) {
            System.out.println(index + ". " + zone.getName());
            index++;
        }

        System.out.println("Enter the number of the zone you want to travel to:");
        int choice = sc.nextInt();
        String clear = sc.nextLine(); // consume the newline character
        if (choice > 0 && choice <= traveableZones.size()) {
            Area[] zonesArray = traveableZones.toArray(new Area[0]);
            Area selectedZone = zonesArray[choice - 1];

            if (pc.getCurrentZone().equals(selectedZone)) {
                Utility.clearConsole();
                Slowprint.sp("You are already in " + selectedZone.getName() + ". Please choose a different zone.");
                return displayTraveableZones(pc); // Recursively call the method again for a valid choice
            }

            Slowprint.sp("You travel to the " + selectedZone.getName());
            Utility.promptEnterKey(sc);
            return selectedZone;
        } else {
            System.out.println("Invalid choice. Please try again.");
            return displayTraveableZones(pc); // Recursively call the method again for a valid choice
        }
    }

    public void displayCurrentZone(PlayerCharacter pc, Area zone) {
        Utility.clearConsole();
        Slowprint.sp("You are in " + pc.getCurrentZone().getName() + ". " + zone.getDescription() + " Zone cleared: " + zone.getZoneCleared());
        Utility.promptEnterKey(sc);
    }
    
    public void travelInsideZone(PlayerCharacter pc, Area zone) { // ONLY FOR FOREST, SWAMP, CAVE
        if (pc.getCurrentZone() == Area.TAVERN || pc.getCurrentZone() == Area.BASEMENT) { // maybe not needed, just remove option to travel when inside those zones?
            Slowprint.sp("You cannot travel inside the " + pc.getCurrentZone().getName());
            Utility.promptEnterKey(sc);
            return;
        }

        Utility.clearConsole();
        Slowprint.sp("You wander around the " + pc.getCurrentZone().getName());
        Slowprint.sp("A monster appears!\nHuzzah! You killed it, and on it you find a map leading to the next area!"); // sample text
        Utility.promptEnterKey(sc);
        
        // fight?
        // EVENTS??
        zone.setZoneCleared(true);
    }

    public void zoneTravel(PlayerCharacter pc, Area room, Scanner sc) { // pc IMPLEMENT BACKTRACKING TO TAVERN HERE PLZ

        Utility.clearConsole();

        if (room.getZoneCleared() == true) {
            switch (pc.getCurrentZone()) {
                case Area.TAVERN:
                    traveableZones.add(Area.FOREST);
    //                Slowprint.sp("You travel to the Forest");
                    traveableZones.add(pc.getCurrentZone());
                    pc.setCurrentZone(displayTraveableZones(pc));

                    break;
                case Area.FOREST:
                    traveableZones.add(Area.SWAMP);
          //          displayCurrentZone(pc, Area.FOREST);
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case Area.SWAMP:
                    traveableZones.add(Area.CAVE);
         //           displayCurrentZone(pc, Area.SWAMP);
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case Area.CAVE:
                    traveableZones.add(Area.BASEMENT);
            //        displayCurrentZone(pc, Area.CAVE);
                    pc.setCurrentZone(displayTraveableZones(pc));
                    Utility.promptEnterKey(sc);
                    if (pc.getCurrentZone() == Area.BASEMENT) {
                        Basement basement = new Basement();
                        basement.bossfight();
                    }
                    break;
                default:
                    System.out.println("Unavailable to travel");
            }
    
        } else {
            Slowprint.sp("You have not cleared this zone yet.");
        }
        Utility.promptEnterKey(sc);
        
    }
}
