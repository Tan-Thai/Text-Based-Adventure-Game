package GameObjects.Worlds;

import GameObjects.Entites.PlayerCharacter;
import Global.*;
import GameObjects.Worlds.Zones;
import GameObjects.Worlds.Zones.Area;

public class Zone extends World {
    private String name;
    private String description;
    private boolean zoneCleared;

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

    public void displayCurrentZone(PlayerCharacter pc, Zones.Area zone) {
        System.out.println("You are in " + pc.getCurrentZone() + ". " + zone.getDescription());
    }
    
    public void travelInsideZone(PlayerCharacter pc, Area zone) { // ONLY FOR FOREST, SWAMP, CAVE
            Slowprint.sp("You wander around the " + pc.getCurrentZone().getName());
            Slowprint.sp("A monster appears!"); // sample text
            // fight?
            zone.setZoneCleared(true);
            // EVENTS??
    }

    public void zoneTravel(PlayerCharacter pc, String zone, Area room) { // pc, next zone
 
        if (room.getZoneCleared() == true) {
            switch (pc.getCurrentZone()) {
                case null:
                    System.out.println("You are in the Tavern");
                    System.out.println("You travel to the " + "Forest");
                    pc.setCurrentZone(Zones.Area.FOREST);
                    break;
                case Zones.Area.FOREST:
                    displayCurrentZone(pc, Zones.Area.FOREST);
                    System.out.println("You travel to the " + "Swamp");
                    pc.setCurrentZone(Zones.Area.SWAMP);
                    break;
                case Zones.Area.SWAMP:
                    displayCurrentZone(pc, Zones.Area.SWAMP);
                    System.out.println("You travel to the " + "Cave");
                    pc.setCurrentZone(Zones.Area.CAVE);
                    break;
                case Zones.Area.CAVE:
                    displayCurrentZone(pc, Zones.Area.CAVE);
                    System.out.println("You travel to the " + "Basement");
                    // call on Basement class to display specific events ??
                    break;
                default:
                    System.out.println("Unavailable to travel");
            }
    
        } else {
            Slowprint.sp("You have not cleared this zone yet.");
        }
           
    }
}
