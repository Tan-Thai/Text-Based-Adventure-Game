package Interactions;

import java.util.OptionalInt;
import java.util.Scanner;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

public class EncounterHandler {

	private static EncounterHandler instance;
	private Scanner scanner;

	private Entity player;
	private Encounter encounter;

	public static synchronized EncounterHandler getInstance() {
		if (instance == null) {
			instance = new EncounterHandler();
		}
		return instance;
	}

	public void initiateEncounter(Entity player, Encounter encounter, Scanner myScanner) {
		// TODO: Discuss adding check here for if player and encounters contains what
		// they
		// should.
		this.player = player;
		this.encounter = encounter;
		this.scanner = myScanner;

		System.out.println(encounter.getDescription());
		Utility.promptEnterKey(scanner);
		Utility.clearConsole();

		if (attemptChallenge()) {
			System.out.println(encounter.getSuccessfulmessage());
			// TODO: Could move this out of being method I suppose, but it seemed easier if
			// we want to display things.
			gainRewards();
		} else {
			System.out.println(encounter.getFailureMessage());
		}

		// TODO: Discuss if failure should cause disadvantage, if encounters should be
		// possible to restart again.
		// TODO: If failure causes damage, need to add check after encounters for death?

		Utility.promptEnterKey(scanner);
		Utility.clearConsole();

		// TODO Add cleaning up method for if encounter was completed and such.

	}

	private boolean attemptChallenge() {

		/*
		 * Returns the value of the relevant skill based on an enum in the challenge,
		 * Should note, I broke this out so that we could perform a check on it, if we
		 * want,
		 * to see it if is negative (to handle more errors)
		 */
		int playerSkillValue = getRelevantPlayerSkillValue();

		if (Utility.rollDicePool(playerSkillValue, Utility.GREEN, OptionalInt.empty(), OptionalInt.empty(),
				OptionalInt.empty()) >= encounter.getChallengeThreshold()) {
			// Indicates success on challenge,
			return true;
		} else {
			return false;
		}
	}

	private int getRelevantPlayerSkillValue() {

		// TODO Suggest having these return -1, and have the return value check for that
		// as well, however, in that case, it is important that player value can never
		// go below zero.

		if (encounter.getChallengeType() == null) {
			System.out.println("Was unable to find a challenge type in encounter.");
			return 0;
		}

		switch (encounter.getChallengeType()) {
			case DEXTERITY:
				return player.getDexterity();
			case HEALTH:
				return player.getHealth();
			case INTELLIGENCE:
				return player.getIntelligence();
			case LEVEL:
				return player.getLevel();
			case STRENGTH:
				return player.getStrength();
			default:
				System.out.println("Couldn't find a case for the challenge type in the " + getClass().toString());
				return 0;
		}
	}

	private void gainRewards() {
		((PlayerCharacter) player).gainExperience(encounter.getExperienceReward());

		// TODO: player is unable to hold money right now, is that by design? When to
		// add if it is mvp?

		// TODO: If hostile entities should also be held within encounters. Do we want
		// the encounter to extract xp and equipment and such from hostile enities as
		// well?
	}
}
