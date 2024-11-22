package GameObjects.Data;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;

public class HostileEntitiesRepository {
	//the mud troll is a tanky creature, danger value high!
	public static HostileCharacter getStuntedMudTroll() {
		return new HostileCharacter("Stunted Mud Troll", 15, 1, 8, 1, 1, HostileEntityType.TROLLKIN, "Description");
	}

	public static HostileCharacter getRottenCrawler() {
		return new HostileCharacter("Rotten Crawler", 7, 1, 2, 1, 1, HostileEntityType.UNDEFINED, "Description");
	}

	public static HostileCharacter getDerangedTrapper() {
		return new HostileCharacter("Deranged Trapper", 3, 1, 2, 2, 1, HostileEntityType.HUMAN, "Description");
	}

	public static HostileCharacter getMudCroc() {
		return new HostileCharacter("Mud Croc", 6, 1, 2, 2, 1, HostileEntityType.UNDEFINED, "Description");
	}

	public static HostileCharacter getGiantRavenousTadpole() {
		return new HostileCharacter("Giant Ravenous Tadpole", 3, 1, 1, 3, 1, HostileEntityType.TOADKIN, "Description");
	}

	public static HostileCharacter getSlimyToadMan() {
		return new HostileCharacter("Slimy Toad Man", 5, 1, 2, 1, 1, HostileEntityType.TOADKIN, "Description");
	}

	public static HostileCharacter getMossDrenchedUndead() {
		return new HostileCharacter("Moss Drenched Undead", 8, 2, 5, 1, 1, HostileEntityType.UNDEAD, "Description");
	}

	public static HostileCharacter getDrownedCrawler() {
		return new HostileCharacter("Drowned Crawler", 6, 2, 4, 2, 1, HostileEntityType.UNDEAD, "Description");
	}

	public static HostileCharacter getGiantBloodsuckingBat() {
		return new HostileCharacter("Giant Bloodsucking Bat", 5, 2, 3, 5, 1, HostileEntityType.UNDEFINED,
				"Description");
	}

	public static HostileCharacter getTyrannicLizardKing() {
		return new HostileCharacter("Tyrannic Lizard King", 15, 3, 9, 5, 3, HostileEntityType.BOSS, "Description");
	}

	public static HostileCharacter getUndefinedCharacter() {
		return null;
	}
}
