package GameObjects.Worlds;

import Global.Utility;
import Interactions.Encounter;
import Interactions.ExploreZone;
import Core.Config;

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

    //region Setters & Getters
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
    //endregion

    /**
     * Displays the current zone and its description + clear status
     *
     * @param zone
     */
    public static void displayCurrentZone(Zone zone) {

        Utility.clearConsole();
        System.out.println("You are in " + zone.getName() + ". " + zone.getDescription());

        if (ExploreZone.getUnclearedEncounter(zone) != null) {
            if (ExploreZone.getUnclearedEncountersAmount(zone) > Config.ZONE_CLEAR_THRESHOLD + 1) {
                System.out.println("You feel an extreme sense of danger in this area.");
            } else if (ExploreZone.getUnclearedEncountersAmount(zone) >= Config.ZONE_CLEAR_THRESHOLD - 1) {
                System.out.println("You feel quite unsafe in this area.");
            } else {
                System.out.println("You feel very safe in this area.");
            }
        } else {
            if (zone.getZoneType() == ZoneType.TAVERN) {
                System.out.println("You are in a safe space, no monsters are lurking here.");
            } else {
                System.out.println("You don't find any monsters or other dangers lurking in this area.");
            }
        }
        System.out.print("\n");
    }

    // Test zone - Reset zone.
    // for usage refer to zoneManager > resetZones
    public void clearEncounters() {
        if (this.encounters != null) {
            this.encounters.clear();
        }
    }

    public void clearTraveableZones() {
        if (this.traveableZones != null) {
            this.traveableZones.clear();
        }
    }
}