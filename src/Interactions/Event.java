package Interactions;

import java.util.ArrayList;
import java.util.List;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Items.Item;

public class Event {
	// TODO: At the moment I have just getters for values and such,
	// and the EventHandler will be the one that decides things, but obviously we
	// can change that so that things are thought through here.

	private HostileCharacter enemy;

	// TODO: These needs to be set at construction instead and so won't be static
	// final from then on.
	private String description;

	private int challengeThreshold;
	private ChallengeType challengeType;

	private String successfulmessage;
	private String failureMessage;

	// If we want, can later on add rewards for failing here as well...
	private int experienceReward;
	private int currencyReward;
	private List<Item> loot = new ArrayList<Item>();

	public Event(HostileCharacter enemy) {
		this.enemy = enemy;
	}

	public Event(String description, int challengeThreshold, ChallengeType challengeType, String successfulMessage,
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
			System.out.println("This event did not contain an enemy, and so one couldn't be returned.");
			return null;
		}
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