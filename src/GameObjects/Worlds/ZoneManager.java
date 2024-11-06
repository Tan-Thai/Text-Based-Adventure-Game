package GameObjects.Worlds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import GameObjects.Data.EncounterRepository;

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
				put(ZoneType.TAVERN, new Zone("Tavern",
						"""
								A lively tavern filled with patrons and the smell of ale.
								""",
						true,
						ZoneType.TAVERN,
						null));
				put(ZoneType.FOREST, new Zone("Wraithwood depths",
						"""
								A dark tranquil old forest where every step taken sinks into the deep soft moss muffling all sound,
								walking here feels timeless and keeping a direction is impossible, becoming lost among the giant timbers
								is only a matter of time. In this sea of green flashes of yellowing white can be seen poking up from
								beneath the autumn foliage.
								""",
						false,
						ZoneType.FOREST,
						null));
				put(ZoneType.SWAMP, new Zone("Mistmoor Marsh",
						"""
								A foul-smelling yellow brown morass with slow moving stagnant water. Ever shrouded in smothering fog
								that steals away all sound giving the marsh an eerie otherworldly feel. Scattered throughout are huge
								floating tussocks, small islands of wild grass and small shrubs. Beneatha the murky surface large shadows
								moves creating soft ripples and swirls.
								""",
						false,
						ZoneType.SWAMP,
						null));
			}
		};
	}

	public static Zone getZone(ZoneType zoneType) {
		return zones.get(zoneType);
	}
}