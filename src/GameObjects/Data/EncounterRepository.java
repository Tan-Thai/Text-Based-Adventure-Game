package GameObjects.Data;

import Interactions.ChallengeType;
import Interactions.Encounter;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepository {

	private static List<Encounter> forestEncounters = new ArrayList<>();

	public static List<Encounter> getForestEncounters() {

		forestEncounters = new ArrayList<Encounter>();

		forestEncounters.add(getLureWillOWispEncounter());
		forestEncounters.add(getHuntersForgottenTrapEncounter());
		forestEncounters.add(getGristlyMealEncounter());
		forestEncounters.add(getStuntedMudTrollEncounter());
		forestEncounters.add(getRottenCrawlerEncounter());
		forestEncounters.add(getDerangedTrapperEncounter());

		return forestEncounters;
	}

	private static List<Encounter> swampEncounters = new ArrayList<>();

	public static List<Encounter> getSwampEncounters() {

		swampEncounters = new ArrayList<>();

		swampEncounters.add(getSinkingMireEncounter());
		swampEncounters.add(getSwampHagsTreasureEncounter());
		swampEncounters.add(getRiddleRottenWoodEncounter());
		swampEncounters.add(getMudCrocEncounter());
		swampEncounters.add(getGiantRavenousTadpoleEncounter());
		swampEncounters.add(getSlimyToadManEncounter());

		return swampEncounters;
	}

	private static Encounter getLureWillOWispEncounter() {
		return new Encounter(
				"""
						You walk deeper into the dense forest, ancient pines wrapped in gray moss towers over you.
						Suddenly, you hear faint pleas for help accompanied by weak sobs of despair.
						A child must have gotten lost in the forest and is in need of your aid!"
						""",
				1,
				ChallengeType.INTELLIGENCE,
				"""
						As you are hurrying in the direction of the child's faint cries you get a feeling that something
						doesn't seem right about this. You pause to listen and are able to detect a faint ethereal tone
						interwoven with the child's voice.
						You realize that you were about to be tricked by a will-o-wisp, a nefarious fay creature that
						enjoys tricking unwary travelers into stepping off the beaten path.
						Strengthened by your own good judgment you ignore the enchanting voice of the sprite and
						continue on your journey.
						""",
				"""
						You follow the faint cries for help deeper and deeper into the woods. The pleas seemingly
						always come from just beyond your sight. You do not realize that you've been tricked until a
						small translucent fairy appears before you with a cruel laugh.
						“Oh what a merry chase we've had you and I!” the will-o-wisp gloats before disappearing in a
						wreath of smoke.
						It takes several exhausting hours for you to find your way back to familiar grounds.
						""",
				5,
				0,
				null);
	}

	private static Encounter getHuntersForgottenTrapEncounter() {
		return new Encounter(
				"""
						You are walking along a narrow hunting trail when you feel something first catch onto, and then
						quickly give way, against your ankle.
						""",
				1,
				ChallengeType.DEXTERITY,
				"""
						You have just enough time to pull your foot back before the snare trap pulls taught and whips
						the rope up into the air. You can't say for certain, but you have a creeping suspicion that this
						trap was set by someone hoping to collect human skins rather than rabbit pelts.
						Now even more cautious, you continue on through the woods.
						""",
				"""
						Before you have time to move a snare is pulled taut around your ankle and you are wrenched
						off your feet as the trap is triggered.	You find yourself suspended upside down three feet off the
						ground. Luckily you still have your knife, and you hurry to cut yourself down before any hunter
						shows up to claim there prey.
						""",
				5,
				0,
				null);
	}

	private static Encounter getGristlyMealEncounter() {
		return new Encounter(
				"""
						Wandering through the forest you come upon a strange gathering. A group of wart-covered trolls
						have gathered around a boiling pot of something that gives off noxious fumes. The creatures are
						seemingly taking turns putting disgusting ingredients into the pot, daring each other to either
						taste the foul concoction or forfeit.
						Instead of attacking you, the evil creatures invite you to take part in their filthy game. You are
						about to refuse when you notice that the trolls are betting quite a lot of gold and other plunder.
						""",
				0,
				ChallengeType.UNDEFINED,
				"""
						You take your place in the ring as the game starts up again. Within a few rounds it is clear to
						you that you cannot possibly last against creatures that habitually feast on carrion. Luckily, you
						are able to take a few cautions sips from your health potion without anyone noticing.
						The drink fortifies your constitution enough and you are able to win.
						Triumphantly you collect your winnings and quickly leave before any of the trolls thinks to protest.
						""",
				"""
						You take your place in the ring as the game starts up again. Within a few rounds it is clear to
						you that you cannot possibly last against creatures that habitually feast on carrion. You stumble
						away from the game with your innards in turmoil, your pride in tatters and your trousers in an
						even dire straits.
						""",
				5,
				35,
				null);
	}

	private static Encounter getStuntedMudTrollEncounter() {
		return new Encounter(HostileEntitiesRepository.getStuntedMudTroll());
	}

	private static Encounter getRottenCrawlerEncounter() {
		return new Encounter(HostileEntitiesRepository.getRottenCrawler());
	}

	private static Encounter getDerangedTrapperEncounter() {
		return new Encounter(HostileEntitiesRepository.getDerangedTrapper());
	}

	private static Encounter getSinkingMireEncounter() {
		return new Encounter(
				"""
						As you stumble between fetid ponds and rotten trees the ground suddenly gives way beneath
						your feet. You’ve accidentally stepped into a pit of thick, sucking mud that begins to pull you
						under.
						""",
				1,
				ChallengeType.STRENGTH,
				"""
						You struggle mightily and manage to heave yourself up and out of the sucking pit. You collapse
						on the wet ground, exhausted and with burning muscles, but you are alive!
						""",
				"""
						The viscous mud threatens to pull you under. You struggle for hours before reaching the edge
						of the pit. By that time your limbs are stiff and near useless. Wet, cold and sore you limp away
						utterly exhausted.
						""",
				5,
				0,
				null);
	}

	private static Encounter getSwampHagsTreasureEncounter() {
		return new Encounter(
				"""
						You are exploring the treacherous wetlands when you feel an ill stench on the wind. Hurrying to
						take cover you see a hulking creature covered in warts and leeches come trudging through
						the swamp.
						This must be a swamp hag! The cruel witch has sold her soul to dark gods in return for dark
						powers. She now serves her evil masters by conjuring pestilence and putting curses on
						unwitting villagers.
						You notice the sack she carries slung over one shoulder. It must contain whatever treasures she
						has taken from the victims of her cruel magic!
						You decide to follow her, hoping to find out what she means to do with the ill-gotten goods.
						""",
				1,
				ChallengeType.DEXTERITY,
				"""
						You carefully follow the sea hag for hours without being seen. Finally she stops at a toppled
						obelisk, and you see how the witch hides her loot underneath the ancient rock. When you are
						sure that she has left you come out of cover and ransack her hiding place.
						""",
				"""
						You are certain that the evil hag hasn't noticed you, until you accidentally disturb a nest of
						ducks. The scared birds create a ruckus and you are forced to flee before the swamp hag is
						able to find you.
						""",
				5,
				15,
				null);
	}

	private static Encounter getRiddleRottenWoodEncounter() {
		return new Encounter(
				"""
						As you try to escape from a swarm of bloodsucking mosquitos you stumble into a grove of
						twisted, ancient trees. The trees all bear grotesque faces that have been gouged into the living
						wood. As you take a moment to catch your breath, the gnarled faces begin to whisper to you.
						'Abandoned before birth,
						Sent my adopted siblings from my home,
						Treated well by those who raised me,
						I abandoned them still, before the first frost came.
						What am I?'
						""",
				1,
				ChallengeType.INTELLIGENCE,
				"""
						'A cuckoo bird' you declare, having pondered the riddle for a moment.
						A sliver of light pierces through the branches and reflects off something stuck within the mouth
						of one of the faces. Carefully you pull out an old tarnished silver coin.
						You pocket it before leaving, taking it as your reward for solving the riddle.
						""",
				"""
						'A scoundrel,' you loudly declare, having pondered the riddle awhile. Silence is the only
						response. Cursing the spirits for refusing to recognize your genius you slink away rather than
						coming up with another answer.
						""",
				5,
				5,
				null);
	}

	private static Encounter getMudCrocEncounter() {
		return new Encounter(HostileEntitiesRepository.getMudCroc());
	}

	private static Encounter getGiantRavenousTadpoleEncounter() {
		return new Encounter(HostileEntitiesRepository.getGiantRavenousTadpole());
	}

	private static Encounter getSlimyToadManEncounter() {
		return new Encounter(HostileEntitiesRepository.getSlimyToadMan());
	}

	private static Encounter getUnfinishedEncounter() {
		return new Encounter(
				null,
				0,
				null,
				null,
				null,
				0,
				0,
				null);
	}
}
