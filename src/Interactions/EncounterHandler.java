package Interactions;

import java.util.OptionalInt;
import java.util.Scanner;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

public class EncounterHandler {

	private static EncounterHandler instance;

	private Scanner myScanner;
	private Entity player;
	private Encounter encounter;

	public static synchronized EncounterHandler getInstance() {
		if (instance == null) {
			instance = new EncounterHandler();
		}
		return instance;
	}

	public void runEncounter(Entity player, Encounter encounter, Scanner scanner) {

		this.player = player;
		this.encounter = encounter;
		this.myScanner = scanner;

		System.out.println(this.encounter.getDescription());
		Utility.promptEnterKey(myScanner);
		Utility.clearConsole();

		if (attemptChallenge()) {
			System.out.println(this.encounter.getSuccessfulmessage());
			this.encounter.isCleared(true);
			gainRewards();
		} else {
			System.out.println(this.encounter.getFailureMessage());
		}

		Utility.promptEnterKey(myScanner);
		Utility.clearConsole();
	}

	private boolean attemptChallenge() {

		int playerSkillValue = getRelevantPlayerSkillValue();

		if (playerSkillValue < 0) {
			System.out.println(
					"The relevant skill had a negative value, this shouldn't be possible. And so couldn't attempt the encounter.");
			return false;
		}

		if (Utility.rollDicePool(playerSkillValue, Utility.GREEN, OptionalInt.empty(), OptionalInt.empty(),
				OptionalInt.empty()) >= encounter.getChallengeThreshold()) {
			// Indicates success on challenge,
			return true;
		} else {
			return false;
		}
	}

	private int getRelevantPlayerSkillValue() {

		if (encounter.getChallengeType() == null) {
			System.out.println("Was unable to find a challenge type in encounter.");
			return -1;
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
				return -1;
		}
	}

	private void gainRewards() {
		((PlayerCharacter) player).gainExperience(encounter.getExperienceReward());
	}
}
