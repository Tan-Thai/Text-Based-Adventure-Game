package GameObjects.Worlds;

import GameObjects.Entities.PlayerCharacter;
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

    public Zone (String name, String desc, boolean zoneCleared) { // constructor handles tavern, basement, enums.
        this.name = name;
        this.description = desc;
        this.zoneCleared = zoneCleared;
    }

    public Zone() { // called in main to initialize the zones
        this.traveableZones = new HashSet<>();
        initializeTravelableZones();
    }

    private void initializeTravelableZones() { // Zoneclear check and adds zones to travelable zones
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

    public void tavernMenu(PlayerCharacter pc) { // opnens up tavern menu for resting and shopping for items
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

    public void travelMenu(PlayerCharacter pc, Zone room) { // opens up travel menu for player.

        if (pc.getCurrentZone() == Area.BASEMENT) { // dirty bossfight check, ignores travelmenu and starts bossfight.
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

    public Area displayTraveableZones(PlayerCharacter pc) { // displays traveable zones and lets player choose where to travel
        Utility.clearConsole();
        int index = 1;

        System.out.println("Travelable Zones:");
        for (Area zone : traveableZones) { // display traveable zones
            System.out.println(index + ". " + zone.getName());
            index++;
        }

        System.out.println("Enter the number of the zone you want to travel to: ");
        int choice = sc.nextInt();
        if (choice > 0 && choice <= traveableZones.size()) {
            Area[] zonesArray = traveableZones.toArray(new Area[0]); // make array of traveablezones Set to be able to index it for selection
            Area selectedZone = zonesArray[choice - 1]; // select zone to travel to, index - 1.

            if (pc.getCurrentZone().equals(selectedZone)) { // check if player is already in the selected zone
                Utility.clearConsole();
                Slowprint.sp("You return to the " + selectedZone.getName());
                return pc.getCurrentZone(); // Put pc back in same zone if already there.
            }
            Utility.clearConsole();
            Slowprint.sp("You travel to the " + selectedZone.getName());
            return selectedZone;
        } else { // error handling for invalid choice
            System.out.println("Invalid choice. Please try again. Or: ");
            return displayTraveableZones(pc); // Recursively call the method again for a valid choice
        }
    }

    public void displayCurrentZone(PlayerCharacter pc) { // Just displays the current zone and its description + if it's cleared or not.
        Utility.clearConsole();
        Slowprint.sp("You are in " + pc.getCurrentZone().getName() + ". " + pc.getCurrentZone().getDescription() + " Zone cleared: " + pc.getCurrentZone().getZoneCleared());
    }
    
    public void travelInsideZone(PlayerCharacter pc) { // Wander/explore inside zone function. 
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
        pc.getCurrentZone().setZoneCleared(true); // sets the zone to cleared after wandering around and killing monster or clearing event.
    }

    public void zoneTravel(PlayerCharacter pc) { // Travel between zones method, 

        Utility.clearConsole();
        initializeTravelableZones();
        if (pc.getCurrentZone().getZoneCleared() == true) { // checks if currentzone is clrared, if not, player can't travel.
            switch (pc.getCurrentZone()) { // 
                case Area.TAVERN:
                    
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case Area.FOREST:
                    this.traveableZones.add(Area.SWAMP); 
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
    
        } else if (pc.getCurrentZone().getZoneCleared() == false && pc.getCurrentZone() != Area.TAVERN) { // allows player to backtrack to tavern if zone is not cleared.
            Slowprint.sp("You have not cleared this zone yet. However, do you want to backtrack to the tavern?");
            Utility.clearScanner(sc);
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

    public boolean checkGameOver() { // ############ TEMPORARY ############
        if (Basement.bossDefeated == true) {
            return true;
        } else {
            return false;
        }

    }
}
