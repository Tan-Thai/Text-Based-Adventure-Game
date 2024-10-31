package GameObjects.Worlds;

import GameObjects.Entites.PlayerCharacter;
import Global.*;
import Global.Utility.Slowprint;
import GameObjects.Worlds.Zones.Area;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Zone  {
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

    public Zone (String name, String desc, boolean zoneCleared) { // for Tavern and Basement
        this.name = name;
        this.description = desc;
        this.zoneCleared = zoneCleared;
    }

    public Zone() {
        this.traveableZones = new HashSet<>();
        initializeTravelableZones();
    }

    private void initializeTravelableZones() { // this is so dirty, but it works for now.
        this.traveableZones.add(Area.TAVERN);
        this.traveableZones.add(Area.FOREST);
        if (Area.FOREST.getZoneCleared() == true) {
            this.traveableZones.add(Area.SWAMP);
        }
        if (Area.SWAMP.getZoneCleared() == true) {
            this.traveableZones.add(Area.CAVE);
        }
        if (Area.CAVE.getZoneCleared() == true) {
            this.traveableZones.add(Area.BASEMENT);
        }
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

    public void tavernMenu(PlayerCharacter pc) {
        Tavern tavern = new Tavern();
        Utility.clearConsole();
  //      Slowprint.sp("You are in the " + pc.getCurrentZone().getName());
        Slowprint.sp("Choose an action:");
        System.out.println("1. Rest (restore health)");
        System.out.println("2. Open shop (buy items)");
        System.out.println("3. Set out (travel to a zone)");
        // talk to npcs? Listen to rumours? etc.

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                tavern.takeRest(pc);
                Utility.promptEnterKey(sc);
                tavernMenu(pc);
                break;
            case 2:
                tavern.openShop(pc);
                Utility.promptEnterKey(sc);
                tavernMenu(pc);
                break;
            case 3:
                travelMenu(pc, tavern);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        
    }

    public void travelMenu(PlayerCharacter pc, Zone room) {

        if (pc.getCurrentZone() == Area.BASEMENT) {
            Basement basement = new Basement();
            basement.bossfight();
            return;
        }

        Utility.clearConsole();
        Slowprint.sp("You are in the " + pc.getCurrentZone().getName());
        Slowprint.sp("Choose an action:");
        System.out.println("1. Wander (travel inside zone)");
        System.out.println("2. Look around (display current zone)");
        System.out.println("3. Travel (travel between zones)");
        if (pc.getCurrentZone() == Area.TAVERN) {
            System.out.println("4. Tavern menu (to rest and shop for items)");
        }

        int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    room.travelInsideZone(pc);
                    break;
                case 2:
                    room.displayCurrentZone(pc);
                    break;
                case 3:
                    room.zoneTravel(pc);
                    break;
                case 4:
                    tavernMenu(pc);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

    }

    public Area displayTraveableZones(PlayerCharacter pc) {
        Utility.clearConsole();
        int index = 1;

        System.out.println("Travelable Zones:");
        for (Area zone : traveableZones) {
            System.out.println(index + ". " + zone.getName());
            index++;
        }

        System.out.println("Enter the number of the zone you want to travel to:");
        int choice = sc.nextInt();
  //      Utility.clearScanner(sc); // consume the newline character
        if (choice > 0 && choice <= traveableZones.size()) {
            Area[] zonesArray = traveableZones.toArray(new Area[0]); // make array of traveablezones Set to be able to index it for selection
            Area selectedZone = zonesArray[choice - 1]; // select zone to travel to, index - 1.

            if (pc.getCurrentZone().equals(selectedZone)) {
                Utility.clearConsole();
                Slowprint.sp("You are already in " + selectedZone.getName() + ". Please choose a different zone.");
                Utility.promptEnterKey(sc);
                return displayTraveableZones(pc); // Recursively call the method again for a valid choice
            }
            Utility.clearConsole();
            Slowprint.sp("You travel to the " + selectedZone.getName());
            return selectedZone;
        } else {
            System.out.println("Invalid choice. Please try again.");
            return displayTraveableZones(pc); // Recursively call the method again for a valid choice
        }
    }

    public void displayCurrentZone(PlayerCharacter pc) { // REMOVE Area zone?? just take zone from pc class.
        Utility.clearConsole();
        Slowprint.sp("You are in " + pc.getCurrentZone().getName() + ". " + pc.getCurrentZone().getDescription() + " Zone cleared: " + pc.getCurrentZone().getZoneCleared());
    }
    
    public void travelInsideZone(PlayerCharacter pc) { // ONLY FOR FOREST, SWAMP, CAVE
        if (pc.getCurrentZone() == Area.TAVERN || pc.getCurrentZone() == Area.BASEMENT) { // maybe not needed, just remove option to travel when inside those zones?
            Utility.clearConsole();
            Slowprint.sp("You cannot travel inside the " + pc.getCurrentZone().getName());
            return;
        }

        Utility.clearConsole();
        Slowprint.sp("You wander around the " + pc.getCurrentZone().getName());
        Slowprint.sp("A monster appears!\nHuzzah! You killed it, and on it you find a map leading to the next area!"); // sample text
 
        
        // fight?
        // EVENTS??
        pc.getCurrentZone().setZoneCleared(true);
    }

    public void zoneTravel(PlayerCharacter pc) { 

        Utility.clearConsole();
        initializeTravelableZones();
        if (pc.getCurrentZone().getZoneCleared() == true) {
            switch (pc.getCurrentZone()) {
                case Area.TAVERN:
                    
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case Area.FOREST:
                    this.traveableZones.add(Area.SWAMP); // are these needed now? TODO test without them.
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case Area.SWAMP:
                    this.traveableZones.add(Area.CAVE);
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case Area.CAVE:
                    this.traveableZones.add(Area.BASEMENT);
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                default:
                    System.out.println("Unavailable to travel");
            }
    
        } else if (pc.getCurrentZone().getZoneCleared() == false && pc.getCurrentZone() != Area.TAVERN) {
            Slowprint.sp("You have not cleared this zone yet. However, do you want to backtrack to the tavern?");
            if (Utility.checkYesOrNo(sc)) {
                pc.setCurrentZone(Area.TAVERN);
                tavernMenu(pc);
            }
            
        }
        
        else {
            Slowprint.sp("You have not cleared this zone yet.");
            Utility.clearScanner(sc);
            
        }
        
    }
}
