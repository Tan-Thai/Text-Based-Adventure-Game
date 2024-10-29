package GameObjects.Worlds;

import GameObjects.Entites.PlayerCharacter;
import Global.*;
import GameObjects.Worlds.Zones.Area;
import java.util.Scanner;

public class Zone extends World {
    private String name;
    private String description;
    private boolean zoneCleared;
    public Scanner sc = new Scanner(System.in);
 

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

    public void displayCurrentZone(PlayerCharacter pc, Area zone) {
        Utility.clearConsole();
        Slowprint.sp("You are in " + pc.getCurrentZone() + ". " + zone.getDescription() + " Zone cleared: " + zone.getZoneCleared());
        Utility.promptEnterKey(sc);

    }
    
    public void travelInsideZone(PlayerCharacter pc, Area zone) { // ONLY FOR FOREST, SWAMP, CAVE
            Utility.clearConsole();
            Slowprint.sp("You wander around the " + pc.getCurrentZone().getName());
            Slowprint.sp("A monster appears!\nHuzzah! You killed it, and on it you find a map to the Swamp!"); // sample text
            Utility.promptEnterKey(sc);
            // fight?
            // EVENTS??
            zone.setZoneCleared(true);
    }

    public void zoneTravel(PlayerCharacter pc, Area room) { // pc, next zone

        Utility.clearConsole();
        
        if (room.getZoneCleared() == true) {
            switch (pc.getCurrentZone()) {
                case null: // THIS WILL BE REMOVED, RIGHT?!? RIGHT?!?!?!?... Yes, I think so.. Use other means to set pc currentzone Forest at start after Tavern.
                    System.out.println("You are in the Tavern");
                    Slowprint.sp("You travel to the " + "Forest");
                    pc.setCurrentZone(Area.FOREST);
                    break;
                case Zones.Area.FOREST:
          //          displayCurrentZone(pc, Area.FOREST);
                    Slowprint.sp("You travel to the " + "Swamp");
                    pc.setCurrentZone(Area.SWAMP);
                    break;
                case Zones.Area.SWAMP:
         //           displayCurrentZone(pc, Area.SWAMP);
                    Slowprint.sp("You travel to the " + "Cave");
                    pc.setCurrentZone(Area.CAVE);
                    break;
                case Zones.Area.CAVE:
            //        displayCurrentZone(pc, Area.CAVE);
                    Slowprint.sp("You travel to the " + "Basement");
                    // call on Basement class to display specific events ??
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
