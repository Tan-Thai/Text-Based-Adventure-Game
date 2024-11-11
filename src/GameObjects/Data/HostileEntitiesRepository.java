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

	public static HostileCharacter getMudCroc() {
		return new HostileCharacter("Mud Croc", 6, 1, 2, 2, 1, HostileEntityType.UNDEFINED);
	}

	public static HostileCharacter getGiantRavenousTadpole() {
		return new HostileCharacter("Giant Ravenous Tadpole", 3, 1, 1, 3, 1, HostileEntityType.TOADKIN);
	}

	public static HostileCharacter getSlimyToadMan() {
		return new HostileCharacter("Slimy Toad Man", 5, 1, 2, 1, 1, HostileEntityType.TOADKIN);
	}

	public static HostileCharacter getUndefinedCharacter() {
		return null;
	}
}
