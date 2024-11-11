package GameObjects.Data;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;

public class HostileEntitiesRepository {

	public static HostileCharacter getStuntedMudTroll() {
		return new HostileCharacter("Stunted Mud Troll", 5, 1, 3, 1, 1, HostileEntityType.TROLLKIN);
	}

	public static HostileCharacter getRottenCrawler() {
		return new HostileCharacter("Rotten Crawler", 7, 1, 2, 1, 1, HostileEntityType.UNDEFINED);
	}

	public static HostileCharacter getDerangedTrapper() {
		return new HostileCharacter("Deranged Trapper", 3, 1, 2, 2, 1, HostileEntityType.HUMAN);
	}

	public static HostileCharacter getUndefinedCharacter() {
		return null;
	}
}
