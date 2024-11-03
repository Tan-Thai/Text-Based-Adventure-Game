package Interactions;

import java.util.Random;
import java.util.Scanner;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

public class EventHandler {

	private static EventHandler instance;
	private Random random = new Random();
	private Scanner scanner;

	private static final int AMOUNT_OF_SIDES = 6;
	private static final int CRIT_VALUE = 6;
	private static final int SUCCESS_VALUE = 4;

	private Entity player;
	private Event event;

	private static synchronized EventHandler getInstance() {
		if (instance == null) {
			instance = new EventHandler();
		}
		return instance;
	}

	/*
	 * So, receives an event, and a player then, then goes through the stages so,
	 * Read prompt
	 * First choice
	 * Outcome
	 * Reward if success
	 */

	public void initiateEvent(Entity player, Event event) {
		// TODO: Discuss adding check here for if player and event contains what they
		// should.
		this.player = player;
		this.event = event;

		System.out.println(event.getDescription());
		Utility.promptEnterKey(scanner);
		Utility.clearConsole();

		if (attemptChallenge()) {
			event.getSuccessfulmessage();
			// TODO: Could move this out of being method I suppose, but it seemed easier if
			// we want to display things.
			gainRewards();
		} else {
			event.getFailureMessage();
		}

		// TODO: Discuss if failure should cause disadvantage, if events should be
		// possible to restart again.
		// TODO: If failure causes damage, need to add check after event for death?

		Utility.promptEnterKey(scanner);
		Utility.clearConsole();

		// TODO Add cleaning up methods etc.
	}

	private boolean attemptChallenge() {

		/*
		 * Returns the value of the relevant skill based on an enum in the challenge,
		 * Should note, I broke this out so that we could perform a check on it, if we
		 * want,
		 * to see it if is negative (to handle more errors)
		 */
		int playerSkillValue = getRelevantPlayerSkillValue();

		// TODO: Suggest if dice rolling shouldn't be broken out and be part of utility
		// instead? That way, the way dice are handled is centralized.
		if (rollDice(playerSkillValue) >= event.getChallengeThreshold()) {
			// Indicates success on challenge,
			return true;
		} else {
			return false;
		}
	}

	private int rollDice(int playerSkillValue) {

		int currentValue;
		int hitCount = 0;

		for (int i = 0; i < playerSkillValue; i++) {
			currentValue = random.nextInt(AMOUNT_OF_SIDES) + 1;

			if (currentValue >= SUCCESS_VALUE) {
				if (currentValue == CRIT_VALUE) {
					i--;
				}
				hitCount++;
			}
		}
		return hitCount;
	}

	private int getRelevantPlayerSkillValue() {

		// TODO Suggest having these return -1, and have the return value check for that
		// as well, however, in that case, it is important that player value can never
		// go below zero.

		if (event.getChallengeType() == null) {
			System.out.println("Was unable to find a challenge type in event.");
			return 0;
		}

		switch (event.getChallengeType()) {
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
		((PlayerCharacter) player).gainExperience(event.getExperienceReward());

		// TODO: player is unable to hold money right now, is that by design? When to
		// add if it is mvp?
	}

}
