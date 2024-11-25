package GameObjects.Worlds;

import Global.Utility;
import Interactions.Encounter;
import Interactions.ExploreZone;

import java.util.*;

public class Zone {
    public static Scanner sc = new Scanner(System.in);
    private final String name;
    private final String description;
    public final ZoneType zoneType;
    private boolean zoneCleared;
    public int zoneClearThreshold;
    private Set<Zone> traveableZones = new HashSet<>();
    public List<Encounter> encounters = new ArrayList<>();

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
     * Displays the current zone and its description + clear status
     * 
     * @param zone
     */
    public static void displayCurrentZone(Zone zone) {

        Utility.clearConsole();
        System.out.println("You are in " + zone.getName() + ". " + zone.getDescription());
        // Changed these to check amount of uncleared encounters and display message
        // related to that.

        if (ExploreZone.getUnclearedEncounter(zone) != null) {
            System.out.println("DEBUG CHECK: Uncleared encounters: " + ExploreZone.getUnclearedEncountersAmount(zone));
            if (ExploreZone.getUnclearedEncountersAmount(zone) >= 4) {
                System.out.println("You feel an extreme sense of danger in this area.");
            } else if (ExploreZone.getUnclearedEncountersAmount(zone) > 0
                    && ExploreZone.getUnclearedEncountersAmount(zone) <= 3) {
                System.out.println("You feel quite unsafe in this area.");
            } else {
                System.out.println("You feel very safe in this area.");
            }
        } else {
            System.out.println("You don't find any monsters or other dangers lurking in this area.");
        }
        System.out.print("\n");
    }
}