package GameObjects.Data;

import java.util.ArrayList;
import java.util.List;

import Interactions.ChallengeType;
import Interactions.Encounter;

public class EncounterRepository {

	private static List<Encounter> forestEncounters = new ArrayList<>();

	public static List<Encounter> getForestEncounters() {

		forestEncounters = new ArrayList<Encounter>();

		forestEncounters.add(getLureWillOWispEncounter());
		forestEncounters.add(getHuntersForgottenTrapEncounter());
		forestEncounters.add(getGristlyMealEncounter());

		return forestEncounters;
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
