package GameObjects.Worlds;

import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Interactions.Encounter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Zone {
    private String name;
    private String description;
    private boolean zoneCleared;
    public Scanner sc = new Scanner(System.in);
    private Set<Zone> traveableZones = new HashSet<>();

    private ZoneType zoneType;

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
    public Zone(String name, String desc, boolean zoneCleared, ZoneType zoneType, List<Encounter> encounters) {
        this.name = name;
        this.description = desc;
        this.zoneCleared = zoneCleared;
        this.zoneType = zoneType;
        this.encounters = encounters;
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

    public void setZoneCleared(boolean zoneCleared) {
        this.zoneCleared = zoneCleared;
    }

    public boolean getZoneCleared() {
        return zoneCleared;
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

    public void tavernMenu(PlayerCharacter pc) { // opnens up tavern menu for resting and shopping for items
        Tavern tavern = new Tavern(); // create tavern object outside?
        Utility.clearConsole();
        Utility.slowPrint("Choose an action:");
        System.out.println("1. Rest (restore health)\n2. Inspect self (character sheet)\n3. Open shop (buy items)\n4. Set out (Back to travel menu)");
        // talk to npcs? Listen to rumours? etc.
        // Temporarily using "inspect self" which brings up the character sheet.

        int choice = Utility.checkIfNumber(sc);

        switch (choice) {
            case 1:
                tavern.takeRest(pc);
                Utility.promptEnterKey(sc);
                tavernMenu(pc);
                break;
            case 2:
                pc.inspectEntity(sc);
                tavernMenu(pc);
            case 3:
                tavern.openShop(pc);
                Utility.promptEnterKey(sc);
                tavernMenu(pc);
                break;
            case 4:
                travelMenu(pc, tavern);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }

    public void travelMenu(PlayerCharacter pc, Zone room) { // opens up travel menu for player.

        if (pc.getCurrentZone().getZoneType() == ZoneType.BASEMENT) { // dirty bossfight check, ignores travelmenu and
                                                                      // starts
            // bossfight.
            Basement basement = new Basement();
            basement.bossfight();
            return;
        }

        Utility.clearConsole();
        Utility.slowPrint("You are in the " + pc.getCurrentZone().getName());
        Utility.slowPrint("Choose an action:");
        System.out.println(
                "1. Wander (travel inside zone)\n2. Look around (display current zone)\n3. Travel (travel between zones)\n4. Remind me how to play again.");
        if (pc.getCurrentZone().getZoneType() == ZoneType.TAVERN) {
            System.out.println("5. Tavern menu (to rest and shop for items)");
        }

        int choice = Utility.checkIfNumber(sc);

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
                Utility.howToPlay(sc);
                break;
            case 5:
                if (pc.getCurrentZone().getZoneType() == ZoneType.TAVERN) {
                    room.tavernMenu(pc);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

    }


    public Zone displayTraveableZones(PlayerCharacter pc) { // displays traveable zones and lets player choose where to
                                                            // travel
        Utility.clearConsole();
        int index = 1;

        System.out.println("Travelable Zones:");
        for (Zone zone : traveableZones) { // display traveable zones
            System.out.println(index + ". " + zone.getName());
            index++;
        }

        System.out.println("Enter the number of the zone you want to travel to: ");
        int choice = Utility.checkIfNumber(sc);
        if (choice > 0 && choice <= traveableZones.size()) {
            Zone[] zonesArray = traveableZones.toArray(new Zone[0]); // make array of traveablezones Set to be able to
                                                                     // index it for selection
            Zone selectedZone = zonesArray[choice - 1]; // select zone to travel to, index - 1.

            if (pc.getCurrentZone().equals(selectedZone)) { // check if player is already in the selected zone
                Utility.clearConsole();
                Utility.slowPrint("You return to the " + selectedZone.getName());
                Utility.promptEnterKey(sc);
                return pc.getCurrentZone(); // Put pc back in same zone if already there.
            }
            Utility.clearConsole();
            Utility.slowPrint("You travel to the " + selectedZone.getName());
            Utility.promptEnterKey(sc);
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

    public void travelInsideZone(PlayerCharacter pc) { // Wander/explore inside zone function.
        if (pc.getCurrentZone().getZoneType() == ZoneType.TAVERN
                || pc.getCurrentZone().getZoneType() == ZoneType.BASEMENT) { // maybe not
            // needed, just
            // remove option to travel
            // when inside those zones?
            Utility.clearConsole();
            Utility.slowPrint("You cannot travel inside the " + pc.getCurrentZone().getName());
            return;
        }

        Utility.clearConsole();

        Utility.slowPrint("You wander around the " + pc.getCurrentZone().getName());
        Utility.slowPrint(
                "A monster appears!\nHuzzah! You killed it, and on it you find a map leading to the next area!"); // sample
                                                                                                                  // text

        // fight?
        // EVENTS??
        pc.getCurrentZone().setZoneCleared(true); // sets the zone to cleared after wandering around and killing monster
                                                  // or clearing event.
    }

    public void zoneTravel(PlayerCharacter pc) { // Travel between zones method,

        Utility.clearConsole();

        if (pc.getCurrentZone().getZoneCleared() == true) { // checks if currentzone is clrared, if not, player can't
                                                            // travel.
            switch (pc.getCurrentZone().getZoneType()) { //
                case ZoneType.TAVERN:
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case ZoneType.FOREST:
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case ZoneType.SWAMP:
                    this.traveableZones.add(ZoneManager.getZone(ZoneType.CAVE));
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                case ZoneType.CAVE:
                    this.traveableZones.add(ZoneManager.getZone(ZoneType.BASEMENT));
                    pc.setCurrentZone(displayTraveableZones(pc));
                    break;
                default:
                    System.out.println("Unavailable to travel");
            }

        } else if (pc.getCurrentZone().getZoneCleared() == false
                && pc.getCurrentZone().getZoneType() != ZoneType.TAVERN) { // allows
            // player to
            // backtrack
            // to tavern
            // if zone is
            // not
            // cleared.

            Utility.slowPrint("You have not cleared this zone yet. However, do you want to backtrack to the tavern?");
            Utility.clearScanner(sc);
            if (Utility.checkYesOrNo(sc)) {
                pc.setCurrentZone(ZoneManager.getZone(ZoneType.TAVERN));
                tavernMenu(pc);
            }

        }

        else {
            Utility.slowPrint("You have not cleared this zone yet.");
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
