package Interactions;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Entities.Entity;
import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Items.EquipmentSlot;
import Global.Utility;

import java.util.OptionalInt;
import java.util.Scanner;

public class Combat {
    // Needed for combat loop
    private static Combat instance;
    private boolean isCombatInProgress = true;
    private Scanner sc;

    // Actors/ Entities in play - had first and second actor, but ill start with
    // Player + enemy.
    private Entity player;
    private Entity enemy;

    private int playerHitCount = 0;
    private int enemyHitCount = 0;

    private Combat() {
    }

    // new up A COMBAT object, and it will be the only one since it's a singleton.
    public static synchronized Combat getInstance() {
        if (instance == null) {
            instance = new Combat();
        }
        return instance;
    }

    // news up the combat situation and then starts the combat logic
    // Currently takes in playerchar and hostilechar as a simple combat, but also to reach their respective methods.
    public void initiateCombat(Entity firstActor, Entity secondActor, Scanner sc) {
        this.player = firstActor;
        this.enemy = secondActor;
        this.sc = sc;

        System.out.println("You have entered combat with a " + enemy.getName() + "!");
        Utility.promptEnterKey(sc);
        Utility.clearConsole();
        isCombatInProgress = true;
        combatLoop();
    }

    private void combatLoop() {

        while (isCombatInProgress) {
            // Prints all hp's
            printEntityHP(player, Utility.GREEN);
            printEntityHP(enemy, Utility.RED);
            System.out.print("to attack press 1, to end combat press 2: ");

            // loop for combat.
            // TODO: add switch statements for when we add different actions.
            if (Utility.checkIfNumber(sc) == 1) {
                Utility.clearConsole();
                // can make it so we send in a defender as well if we add a defence formula.

                playerHitCount = calcAttack(player, Utility.GREEN);
                enemyHitCount = calcAttack(enemy, Utility.RED);

                printHits();

                resolveHP();

                checkVictoryConditionMet();
            } else {
                isCombatInProgress = false;
            }
        }
    }

    // Prints out the bars.
    private void printEntityHP(Entity actor, String colour) {
        System.out.println(colour + actor.getName() + " health: " + actor.getHealth() + Utility.RESET);
        for (int i = 1; i <= actor.getHealth(); i++) {
            System.out.print(colour + "|" + Utility.RESET);
        }
        System.out.println();
    }

    // calc's the attack values etc.
    private int calcAttack(Entity actor, String colour) {

        int hitCount = Utility.rollDicePool(actor.getStrength(), colour, OptionalInt.empty(), OptionalInt.empty(),
                OptionalInt.empty());

        if (actor.getEquipment(EquipmentSlot.WEAPON) != null) {
            int weaponDamage = actor.getEquipment(EquipmentSlot.WEAPON).getDamageValue();
            System.out.println("Weapon added " + weaponDamage + " damage.");
            return hitCount + weaponDamage;
        }
        //Used to add break inbetween lines in the console
        System.out.println();
        return hitCount;
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

    private void checkVictoryConditionMet() {
        // will prolly have to change this due to us hardcoding a win message and all
        // other handling here.
        if (player.isDead() && enemy.isDead()) {
            Utility.clearConsole();
            System.out.println("Both of you perish");
            exitingCombat();

        } else if (enemy.isDead()) {
            Utility.clearConsole();
            printEntityHP(player, Utility.GREEN);
            System.out.println("You have vanquished your foe!");
            ((PlayerCharacter) player).gainExperience(((HostileCharacter) enemy).calcExperienceGiven());
            exitingCombat();

        } else if (player.isDead()) {
            Utility.clearConsole();
            System.out.println("You have been slain");
            // currently calling the state to game over on death here.
            GameStateManager.getInstance().setCurrentState(GameState.GAME_OVER);
            exitingCombat();
        }
    }

    private void exitingCombat() {
        isCombatInProgress = false;
        Utility.promptEnterKey(sc);
        Utility.clearConsole();
    }
}
