package Interactions;

import GameObjects.Entities.Entity;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.OptionalInt;
import java.util.Scanner;

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
        // The prompt and clear are resolved in the runGame method in game.
        // Therefore these have been temporarily removed for gameflow purposes. //Henrik
  //      Utility.promptEnterKey(myScanner);
  //      Utility.clearConsole();
    }

    private boolean attemptChallenge() {

        int playerSkillValue = getRelevantPlayerSkillValue();

        if (playerSkillValue < 0) {
            System.out.println(
                    "The relevant skill had a negative value, this shouldn't be possible. And so couldn't attempt the encounter.");
            return false;
        }

        // Indicates success on challenge,
        displayRelevantSkill();
        return Utility.rollDicePool(playerSkillValue, Utility.GREEN, OptionalInt.empty(), OptionalInt.empty(),
                OptionalInt.empty()) >= encounter.getChallengeThreshold();
    }

    private void gainRewards() {
        ((PlayerCharacter) player).gainExperience(encounter.getExperienceReward());
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
                System.out.println("Couldn't find a case for the challenge type in the " + getClass());
                return -1;
        }
    }

    private void displayRelevantSkill() {

        if (encounter.getChallengeType() == null) {
            System.out.println("Was unable to find a challenge type in encounter.");
            return;
        }

        switch (encounter.getChallengeType()) {
            case DEXTERITY:
                System.out.println("You use your Dexterity to attempt this challenge.\nYour current Dex is: " + player.getDexterity());
                Utility.promptEnterKey(myScanner);
                break;
            case HEALTH:
                System.out.println("You use your Health to attempt this challenge.\nYour current Health is: " + player.getHealth());
                Utility.promptEnterKey(myScanner);
                break;
            case INTELLIGENCE:
                System.out.println("You use your Intelligence to attempt this challenge.\nYour current Int is: " + player.getIntelligence());
                Utility.promptEnterKey(myScanner);
                break;
            case LEVEL:
                System.out.println("You use your experience to attempt this challenge.\nYour current level is: " + player.getLevel());
                Utility.promptEnterKey(myScanner);
                break;
            case STRENGTH:
                System.out.println("You use your Strength to attempt this challenge.\nYour current Str is: " + player.getStrength());
                Utility.promptEnterKey(myScanner);
                break;
            default:
                System.out.println("Couldn't find a case for the challenge type in the " + getClass());
                Utility.promptEnterKey(myScanner);
                break;
        }
    }
}
