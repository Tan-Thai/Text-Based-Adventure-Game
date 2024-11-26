package GameObjects.Data;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;

public class HostileEntitiesRepository {
	// the mud troll is a tanky creature, intended to be dangorus
	public static HostileCharacter getStuntedMudTroll() {

		return new HostileCharacter("Stunted Mud Troll", 15, 1, 8, 1, 1, HostileEntityType.TROLLKIN,
				"""
						Out from under the moss a monstrous mud-covered troll appears, roots hang from its huge back,
						and it snorts foul smelling air from between its rotting yellow teeth as it charges you.
						""");
	}

	// very weak, intended to be easily beaten.
	public static HostileCharacter getRottenCrawler() {
		return new HostileCharacter("Rotten Crawler", 3, 1, 2, 1, 1, HostileEntityType.UNDEFINED,
				"""
						You smell rot in the air and as you look up fat armlong pale maggots with sharp mandibles
						fall onto you from the tree canopy and begins to try and eat through your cloths to get
						at your flesh.""");
	}

	// meant to be hard to hit and a medium difficulty
	public static HostileCharacter getDerangedTrapper() {
		return new HostileCharacter("Deranged Trapper", 8, 1, 2, 3, 3, HostileEntityType.HUMAN,
				"""
						You hear the insane mumbling, and the smell of unwashed body hits your nose
						as you come face to face with a disheveled human being with a bush of a beard,
						wild eyes and a foaming mouth.""");
	}

	// also a very tanky and dangorus enemy
	public static HostileCharacter getMudCroc() {
		return new HostileCharacter("Mud Croc", 20, 1, 6, 1, 1, HostileEntityType.UNDEFINED,
				"""
						In the water a giant white crocodile covered in scars and with one red eye
						moves towards you at incredible speed, mouth opening and displaying three rows
						of hooked teeth ready to sink into your flesh """);
	}

	// very weak, intended to be easily beaten.
	public static HostileCharacter getGiantRavenousTadpole() {
		return new HostileCharacter("Giant Ravenous Tadpole", 3, 1, 1, 3, 1, HostileEntityType.TOADKIN,
				"""
						You slips into the murky water and a swarm of fist sized black-grey tadpoles with piranha
						mouths immediately come at you from all angles.""");
	}

	// meant to be a medium threat to low level characters
	public static HostileCharacter getSlimyToadMan() {
		return new HostileCharacter("Slimy Toad Man", 10, 1, 4, 2, 1, HostileEntityType.TOADKIN,
				"""
						You feel eyes upon you in the water, you first see what looks to be the head of a giant toad
						but as it stands up you see its some form of human toad hybrid with webbed hands holding a
						rusted boat hook and a filth net that it throws at you.""");
	}

	// very dangorus meant to be hard for leveled up characters
	public static HostileCharacter getMossDrenchedUndead() {
		return new HostileCharacter("Moss Drenched Undead", 10, 2, 8, 1, 3, HostileEntityType.UNDEAD,
				"""
						The flames of your torch illuminate a rotting warrior that has grown into the wall,
						moss and huge funguses cover its body and binds it to the wall, as you look closer
						its cataracted eyes open and it rips free swinging a rusted axe at your head.""");
	}

	// the easies of enemies in the caverns but still dangerous
	public static HostileCharacter getDrownedCrawler() {
		return new HostileCharacter("Drowned Crawler", 10, 2, 4, 2, 1, HostileEntityType.UNDEAD,
				"""
						Out of the fungus covered floor of the cave rise meter long grey maggots
						with black carapace that tries to consume you.""");
	}

	// very hard to hit and very fast very dangerous if unarmored
	public static HostileCharacter getGiantBloodsuckingBat() {
		return new HostileCharacter("Giant Bloodsucking Bats", 15, 2, 3, 5, 5, HostileEntityType.UNDEFINED,
				"""
						You feel the wind change and hear the chittering noises as a murmuration of giant
						black and red bats flood the tunnel and begin taking swiping bites at you.""");
	}

	// very dangerous this is the boss people!
	public static HostileCharacter getTyrannicLizardKing() {
		return new HostileCharacter("Maximus Rex", 10, 3, 9, 1, 5, HostileEntityType.BOSS,
				"""
						You have reached the end of the caverns and it has lead you to what must be the basement of the tavern
						as you can faintly hear the music from up above, but in the dark lays the beast of darkness,
						the embodiment of shadow the giant emberprism eating king of lizards known only as -Maximus Rex-.""");
	}

	public static HostileCharacter getUndefinedCharacter() {
		return null;
	}
}
