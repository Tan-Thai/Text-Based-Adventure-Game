package Interactions;
import GameObjects.Entities.*;
import Global.Utility;

import java.util.Random;
import java.util.Scanner;

public class Combat
{
    // Needed for combat loop
    private static Combat instance;
    private boolean isCombatInProgress = true;
    private Random random =new Random();
    private Scanner sc;

    //Actors/ Entities in play - had first and second actor, but ill start with Player + enemy.
    private Entity player;
    private Entity enemy;

    //Dice related
    private static final int AMOUNT_OF_SIDES = 6;
    private static final int CRIT_VALUE = 6;
    private static final int SUCCESS_VALUE = 4;

    private int playerHitCount =0;
    private int enemyHitCount =0;

    // colour for prints
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";

    public static synchronized Combat getInstance() {
        if (instance == null) {
            instance = new Combat();
        }
        return instance;
    }

    private Combat() {}

    // news up the combat situation and then starts the combat logic
    public void initiateCombat(Entity firstActor, Entity secondActor, Scanner sc) {
        this.player = firstActor;
        this.enemy = secondActor;
        this.sc = sc;

        combatLoop();
    }

    public void combatLoop()
    {
        while(isCombatInProgress) {

            // Prints all hp's
            printEntityHP(player, GREEN);
            printEntityHP(enemy, RED);
            System.out.println("to attack press 1, to end combat press 2");

            // loop for combat.
            if(Utility.checkIfNumber(sc) == 1)
            {
                // can make it so we send in a defender as well if we add a defence formula.
                attackEntity(player, GREEN);
                attackEntity(enemy, RED);

                printHits();

                resolveHP();

                checkVictoryConditionMet();
            } else {
                isCombatInProgress =false;
            }

        }
    }

    // Prints out the bars.
    private void printEntityHP(Entity actor, String colour) {
        System.out.println(colour + actor.getName() + " health: " + actor.getHealth() + RESET);
        for(int i = 1; i <= actor.getHealth(); i++)
        {
            System.out.print(colour + "|" + RESET);
        }
        System.out.println();
    }

    // calc's the attack values and etc.
    private void attackEntity(Entity actor, String colour) {
        int currentValue;
        int hitCount = 0;

        for(int i = 0; i < actor.getStrength(); i++) {
            currentValue=random.nextInt(AMOUNT_OF_SIDES)+1;
            System.out.print(colour + "["+currentValue+"]" + RESET);

            if(currentValue >= SUCCESS_VALUE) {
                if(currentValue == CRIT_VALUE) {
                    i--; // does this give another roll if you crit?
                }
                hitCount++;
            }
        }

        if (colour.equals(GREEN)) {
            playerHitCount = hitCount;
        } else {
            enemyHitCount = hitCount;
        }
        System.out.println();
    }

    private void printHits() {
        System.out.println("You got " + playerHitCount + " hits!");
        System.out.println("Your enemy got " + enemyHitCount + " hits!");
    }

    private void resolveHP() {
        player.setHealth(player.getHealth() - enemyHitCount);
        enemy.setHealth(enemy.getHealth() - playerHitCount);
        playerHitCount = 0;
        enemyHitCount = 0;

    }

    private void checkVictoryConditionMet()
    {
        // will prolly have to change this due to us hardcoding a win message and all other handling here.
        if(player.getHealth() <= 0 && enemy.getHealth() <= 0) {
            System.out.println("Both of you perish");
            isCombatInProgress =false;

        } else if (enemy.getHealth()<=0) {
            printEntityHP(player, GREEN);
            System.out.println("You have vanquished your foe!");
            int playerExp= + 5;
            System.out.println("You gain 5 experience points and a rusty longsword");
            System.out.println("your current experience is " + playerExp);
            isCombatInProgress =false;

        } else if(player.getHealth()<=0) {
            System.out.println("You have been slayed");
            isCombatInProgress =false;
        }
    }

}

