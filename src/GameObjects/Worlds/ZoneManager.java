package GameObjects.Worlds;

import GameObjects.Data.EncounterRepository;
import GameObjects.Data.ZoneRepository;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ZoneManager {

	private static HashMap<ZoneType, Zone> zones = new HashMap<>();

	private static ZoneManager instance;

	public ZoneManager() {
		zones = setupZones();
		setUpTravelableConnections(zones);
		setUpEncounters(zones);
	}

	private void setUpEncounters(HashMap<ZoneType, Zone> zones) {
		zones.get(ZoneType.FOREST).setEncounters(EncounterRepository.getForestEncounters());
	}

	/**
	 * Sets what zones should be travelable inbetween to begin with in the game.
	 * 
	 * @param zones
	 */
	private void setUpTravelableConnections(HashMap<ZoneType, Zone> zones) {
		zones.get(ZoneType.TAVERN).setTravelableZones(
				new HashSet<Zone>(Arrays.asList(
						zones.get(ZoneType.FOREST),
						zones.get(ZoneType.SWAMP))));
		zones.get(ZoneType.FOREST).setTravelableZones(
				new HashSet<Zone>(Arrays.asList(
						zones.get(ZoneType.TAVERN),
						zones.get(ZoneType.SWAMP))));
		zones.get(ZoneType.SWAMP).setTravelableZones(
				new HashSet<Zone>(Arrays.asList(
						zones.get(ZoneType.TAVERN),
						zones.get(ZoneType.FOREST))));
	}

	public static synchronized ZoneManager getInstance() {
		if (instance == null) {
			instance = new ZoneManager();
		}
		return instance;
	}

	/**
	 * Returns a list of all of the zone objects. The set of travelable zones isn't
	 * set here. And the encounters aren't set.
	 * 
	 * @return
	 */
	private static HashMap<ZoneType, Zone> setupZones() {
		return new HashMap<>() {
			{
				put(ZoneType.TAVERN, ZoneRepository.tavernZone());
				put(ZoneType.FOREST, ZoneRepository.forestZone());
				put(ZoneType.SWAMP, ZoneRepository.swampZone());
				put(ZoneType.CAVE, ZoneRepository.caveZone());
				put(ZoneType.BASEMENT, ZoneRepository.basementZone());
			}
		};
	}

	public static Zone getZone(ZoneType zoneType) {
		if (zones.get(zoneType) == null) {
			System.err.println("Zone not found");
			return null;
		}
		return zones.get(zoneType);
	}

}