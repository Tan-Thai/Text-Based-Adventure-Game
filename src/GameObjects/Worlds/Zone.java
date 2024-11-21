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

    // Just displays the current zone and its description + clear status
    public static void displayCurrentZone(Zone zone) {

        Utility.clearConsole();
        System.out.println("You are in " + zone.getName() + ". " + zone.getDescription()
                           + " Zone cleared: " + zone.getZoneCleared());
        if (ExploreZone.getUnclearedEncounter(zone) != null) {
            System.out.println("Uncleared encounters: " + ExploreZone.getUnclearedEncountersAmount(zone));
        } else {
            System.out.println("No uncleared encounters.");
        }
        Utility.promptEnterKey(sc);
    }

    // Test zone - Reset zone.
    // for usage refer to zoneManager > resetZones
    public void clearEncounters() {
        if (this.encounters != null) {
            this.encounters.clear();
        }
    }
}
