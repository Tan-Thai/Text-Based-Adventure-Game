package Interactions;

import java.util.ArrayList;
import java.util.List;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Items.Item;

public class Encounter {

	private boolean isCleared = false;

	private HostileCharacter enemy;

	private String description;
	private String successfulmessage;
	private String failureMessage;

	private int challengeThreshold;
	private ChallengeType challengeType;

	// If we want, can later on add rewards for failing here as well...
	private int experienceReward;
	private int currencyReward;
	private List<Item> loot = new ArrayList<Item>();

	public Encounter(HostileCharacter enemy) {
		this.enemy = enemy;
	}

	public Encounter(String description, int challengeThreshold, ChallengeType challengeType, String successfulMessage,
			String failureMessage, int experienceReward, int currencyReward, List<Item> loot) {
		this.description = description;
		this.challengeThreshold = challengeThreshold;
		this.challengeType = challengeType;
		this.successfulmessage = successfulMessage;
		this.failureMessage = failureMessage;
		this.experienceReward = experienceReward;
		this.currencyReward = currencyReward;
		this.loot = loot;
	}

	public boolean isCleared() {
		return isCleared;
	}

	public void isCleared(boolean state) {
		isCleared = state;
	}

	public boolean isCombatEncounter() {
		if (enemy == null)
			return false;
		else
			return true;
	}

	public HostileCharacter getEnemy() {
		if (isCombatEncounter())
			return enemy;
		else {
			System.out.println("This encounter did not contain an enemy, and so one couldn't be returned.");
			return null;
		}
	}

	public String getDescription() {
		return description;
	}

	public String getSuccessfulmessage() {
		return successfulmessage;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public int getChallengeThreshold() {
		return challengeThreshold;
	}

	public ChallengeType getChallengeType() {
		return challengeType;
	}

	public int getExperienceReward() {
		return experienceReward;
	}

	public int getCurrencyReward() {
		return currencyReward;
	}

	public List<Item> getLoot() {
		return loot;
	}
}