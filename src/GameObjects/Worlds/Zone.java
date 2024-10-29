package GameObjects.Worlds;

import GameObjects.Entites.PlayerCharacter;
import Global.*;

public abstract class Zone {
    private String name;
    private String description;
    private boolean zoneCleared;

    public Zone(String name, String desc) {
        this.name = name;
        this.description = desc;
        this.zoneCleared = false; 
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

    public void displayCurrentZone() {
        System.out.println("You are in " + name + ". " + description);
    }
    
    public void travelInsideZone(PlayerCharacter pc) {
        if (pc.getCurrentZone() == "Tavern" || pc.getCurrentZone() == "Basement") {
            Slowprint.sp("You look around the " + pc.getCurrentZone());
            // call on either Tavern or Basement class to display specific events

        } else {
            Slowprint.sp("You wander around the " + pc.getCurrentZone());
            Slowprint.sp("A monster appears!"); // sample text
            zoneCleared = true;
            // EVENTS??
        }
    }

    public void zoneTravel(PlayerCharacter pc, String zone) { // Travel should be modular!
 
        if (zoneCleared == true) {
            switch (pc.getCurrentZone()) {
                case "Tavern":
                    displayCurrentZone();
                    System.out.println("You travel to the " + "Forest");
                    pc.setCurrentZone("Forest");
                    break;
                case "Forest":
                    displayCurrentZone();
                    System.out.println("You travel to the " + "Swamp");
                    pc.setCurrentZone("Swamp");
                    break;
                case "Swamp":
                    displayCurrentZone();
                    System.out.println("You travel to the " + "Cave");
                    pc.setCurrentZone("Cave");
                    break;
                case "Cave":
                    displayCurrentZone();
                    System.out.println("You travel to the " + "Basement");
                    pc.setCurrentZone("Basement");
                    break;
                default:
                    System.out.println("Unavailable to travel");
            }
    
        } else {
            Slowprint.sp("You have not cleared this zone yet.");
        }
           
    }
}
