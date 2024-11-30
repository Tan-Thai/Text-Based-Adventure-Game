package Interactions;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Entities.Entity;
import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Items.EquipmentType;
import GameObjects.Items.Weapon;
import GameObjects.Items.WeaponType;
import Global.Utility;

import java.util.*;

public class Combat {
    // Needed for combat loop
    private static Combat instance;
    private boolean isCombatInProgress = true;
    private Scanner sc;

    // Actors/ Entities in play - had first and second actor, but ill start with
    // Player + enemy.
    private PlayerCharacter player;
    private HostileCharacter enemy;

    private int playerInitiative = 0;
    private int enemyInitiative = 0;

    // new up A COMBAT object, and it will be the only one since it's a singleton.
    public static synchronized Combat getInstance() {
        if (instance == null) {
            instance = new Combat();
        }
        return instance;
    }

    // news up the combat situation and then starts the combat logic
    // Currently takes in player-char and hostile-char as a simple combat, but also to
    // reach their respective methods.
    public void initiateCombat(PlayerCharacter player, HostileCharacter enemy, Scanner sc) {
        this.player = player;
        this.enemy = enemy;
        this.sc = sc;

        System.out.println("You have entered combat with a " + enemy.getName() + "!");
        System.out.println(enemy.getHostileEntityDescription());
        Utility.promptEnterKey(sc);
        Utility.clearConsole();
        isCombatInProgress = true;
        combatLoop();
    }

    private void combatLoop() {
        // creates the initiative for both entities
        playerInitiative = calcInitiative(player, Utility.GREEN);
        enemyInitiative = calcInitiative(enemy, Utility.RED);

        int turnCount = 1;
        boolean playerGoingFirst = playerInitiative >= enemyInitiative;
        Utility.clearConsole();

        // loop for combat.
        printInitiative();
        while (isCombatInProgress) {
            // made code more DRY by only repeating the needed methods (the attack order being swapped.)
            printStatusDisplay(turnCount);
            if (playerGoingFirst)
                playerCombatAction();
            else
                enemyAttack(calcAttack(enemy, Utility.RED));

            Utility.promptEnterKey(sc);
            Utility.clearConsole();

            printStatusDisplay(turnCount);
            if (playerGoingFirst)
                enemyAttack(calcAttack(enemy, Utility.RED));
            else
                playerCombatAction();


            System.out.println("------------ End of Turn ------------");
            checkVictoryConditionMet();
            // We can most likely have a single checkVictoryCon here due to the automatic return when x is dead.
            // then it will just assert that by calling isDead (it already does). to then proceed to delivering loot.
            Utility.promptEnterKey(sc);
            Utility.clearConsole();
            turnCount++;
        }
        // Combat ended to mark that it will return to main menu after the 2nd prompt
        // (avoids the double prompt issue too) - TT
        System.out.println("------------ The combat has ended ------------");
    }

    //region Print-methods
    private void printStatusDisplay(int turnCount) {
        System.out.println("------------ Turn: " + turnCount + " ------------");
        printEntityHP(player, Utility.GREEN);
        printEntityHP(enemy, Utility.RED);
    }

    private void printEntityHP(Entity actor, String colour) {
        System.out.println(colour + actor.getName() + " health: " + actor.getHealth() + " / " + actor.getMaxHealth() +
                           Utility.RESET);

        for (int i = 1; i <= actor.getHealth(); i++) {
            System.out.print(colour + "|" + Utility.RESET);
        }

        for (int i = 0; i < actor.getMaxHealth() - actor.getHealth(); i++) {
            System.out.print(Utility.LOW_INTENSITY + "|" + Utility.RESET);
        }
        System.out.println();
    }

    private void printInitiative() {
        System.out.println("Your initiative is " + playerInitiative);
        System.out.println("Your enemies initiative is " + enemyInitiative);
    }

    private void printAttemptsAndAvoids(Entity attacker, Entity defender, int attackHits) {
        // dynamically omits 's' if it's only a singular hit.
        System.out.println(attacker.getName() + " attempts to swing " + attackHits + " time" +
                           (attackHits == 1 ? "" : "s") + "!\n--");

        // if case added to prevent that the target "avoids" more than the amount of hits attempted.
        if (defender.getDexterity() > attackHits) {
            System.out.println(defender.getName() + " avoids " + attackHits + " hit" +
                               (attackHits == 1 ? "" : "s") + "!\n--");
        } else {
            System.out.println(defender.getName() + " avoids " + defender.getDexterity() + " hit" +
                               (defender.getDexterity() == 1 ? "" : "s") + "!\n--");
        }
    }
    //endregion

    //region Main Combat Calculations
    private int calcInitiative(Entity actor, String colour) {

        int speedCount = Utility.rollDicePool(actor.getIntelligence(), colour, OptionalInt.empty(), OptionalInt.empty(),
                OptionalInt.empty());
        System.out.println();
        return speedCount;
    }

    private void playerCombatAction() {
        if (player.isDead()) {
            return;
        }

        // Used to secure loop if input is incorrect, usable until Tan adds the max
        // option number to the Utility.checkIfNumber() method.
        boolean isInputCorrect = true;

        do {
            System.out.print("\n1. Attack" +
                             "\n2. Inventory" +
                             "\n0. Flee" +
                             "\nChoose your action: ");

            // Sets to true, so program breaks out of script unless it is set to false in
            // the default case of the switch.
            isInputCorrect = true;

            switch (Utility.checkIfNumber(sc)) {
                case 1 -> {
                    playerAttack(enemy, player, calcAttack(player, Utility.GREEN));
                }
                // TODO bug: no matter what player does, turn will be consumed.
                //  Example: picking wrong item and not using item.
                case 2 -> player.getInventory().inspectInventory(sc, player, enemy);
                case 0 -> {
                    if (enemy.getHostileEntityType().equals(HostileEntityType.BOSS)) {
                        System.out.println("You can't run from a boss!");
                        Utility.clearConsole();
                    } else {
                        System.out.println("You have fled from combat!");
                        isCombatInProgress = false;

                        enemy.heal(enemy.getMaxHealth());
                    }
                }
                default -> {
                    System.out.println("incorrect input, try again");
                    isInputCorrect = false;
                }
            }
        } while (!isInputCorrect);
    }

    private int calcAttack(Entity actor, String colour) {

        System.out.println(
                "\n------------ " + colour + actor.getName() + "'s action" + Utility.RESET + " ------------");
        int hitCount = Utility.rollDicePool(actor.getStrength(), colour, OptionalInt.empty(), OptionalInt.empty(),
                OptionalInt.empty());

        // Used to add break in between lines in the console
        System.out.println();
        return hitCount;

    }

    private void enemyAttack(int attackHits) {
        if (enemy.isDead()) {
            return;
        }

        printAttemptsAndAvoids(enemy, player, attackHits);

        attackHits -= player.getDexterity();

        if (attackHits <= 0) {
            System.out.println(enemy.getName() + " misses...");
        } else {
            int weaponDamage = addedWeaponDamage(enemy);

            if (weaponDamage > 0) {
                attackHits += weaponDamage;

                System.out.println(player.getEquipmentList().getEquipment(EquipmentType.WEAPON).getName()
                                   + " added " + weaponDamage + " damage.\n--");
            }

            if (addedArmorSave(player) > 0) {
                System.out.println(addedArmorSave(player) + " damage was blocked by armour.");
            }

            attackHits -= addedArmorSave(player);

            if (attackHits < 0) {
                attackHits = 0;
            }

            player.takeDamage(attackHits);
        }
    }

    private void playerAttack(HostileCharacter enemy, PlayerCharacter player, int attackHits) {

        printAttemptsAndAvoids(player, enemy, attackHits);

        attackHits -= enemy.getDexterity();

        if (attackHits < 0) {
            attackHits = 0;
        }

        if (attackHits == 0) {
            System.out.println(player.getName() + " misses...");
        } else {
            int weaponDamage = addedWeaponDamage(player);

            if (weaponDamage > 0) {
                attackHits += weaponDamage;
                attackHits = getDamageConversionBasedOnType(attackHits, player, enemy);
                // TODO Make a print based on effectiveness, or rework how the resolution of damage is printed.
                // This line is super deceptive, since we don't mention ANYTHING about effectiveness.
                System.out.println(player.getEquipmentList().getEquipment(EquipmentType.WEAPON).getName()
                                   + " added " + weaponDamage + " damage.\n--");
            }

            enemy.takeDamage(attackHits);
        }
    }
    //endregion

    //region Equipment Calculations
    // checks and returns the added weapon damage if actor has one equipped,
    // otherwise returns zero.
    private int addedWeaponDamage(Entity actor) {
        if (actor.getEquipmentList().getEquipment(EquipmentType.WEAPON) != null)
            return actor.getEquipmentList().getEquipment(EquipmentType.WEAPON).getEffectValue();
        else
            return 0;
    }

    // checks and returns the protection from the armor, if entity has one equipped,
    // otherwise returns zero.
    private int addedArmorSave(Entity actor) {
        if (actor.getEquipmentList().getEquipment(EquipmentType.ARMOUR) != null)

            return actor.getEquipmentList().getEquipment(EquipmentType.ARMOUR).getEffectValue();
        else
            return 0;
    }

    private int getDamageConversionBasedOnType(int damage, Entity attacker, HostileCharacter defender) {

        WeaponType tempWeaponType = WeaponType.DEFAULT;

        // If attacker does not have weapon equipped this returns damage without
        // modifying it.
        if (attacker.getEquipmentList().getEquipment(EquipmentType.WEAPON) == null) {
            return damage;
        } else {
            tempWeaponType = ((Weapon) attacker.getEquipmentList().getEquipment(EquipmentType.WEAPON))
                    .getWeaponType();
        }

        HostileEntityType tempHostileEntityType = defender.getHostileEntityType();

        switch (tempHostileEntityType) {
            case DRACONIC:
                if (tempWeaponType == WeaponType.FIRE) {
                    weakenAttack(damage);
                } else {
                    return damage;
                }
            case TOADKIN:
                if (tempWeaponType == WeaponType.FIRE) {
                    return weakenAttack(damage);
                } else {
                    return damage;
                }
            case TROLLKIN:
                if (tempWeaponType == WeaponType.FIRE || tempWeaponType == WeaponType.SUNLIGHT) {
                    return strengthenAttack(damage);
                } else {
                    return damage;
                }
            case UNDEAD:
                if (tempWeaponType == WeaponType.FIRE || tempWeaponType == WeaponType.HOLY
                    || tempWeaponType == WeaponType.SUNLIGHT) {
                    return strengthenAttack(damage);
                } else {
                    return weakenAttack(damage);
                }
            default:
                return damage;
        }
    }

    /**
     * Prints a statement warning that the attack seemed to have little effect.
     * Then returns damage divided by two, rounded down
     *
     * @param damage
     * @return
     */
    private int weakenAttack(int damage) {
        System.out.println("Your attack had almost no effect! You only caused " + (damage / 2) + " points of damage!");

        return damage / 2;
    }

    /**
     * Prints a congratulatory statement about how effective the attack was.
     * Then returns the val times two.
     *
     * @param damage
     * @return
     */
    private int strengthenAttack(int damage) {
        System.out.println("Your attack was super effective! You caused " + (damage * 2) + " points of damage!");
        return damage * 2;
    }
    //endregion

    //region Post-Combat Methods (WinConditions, EXP gain etc.)
    // TODO: Look at making this a checking method, and using it to decide if combat
    // should continue, and move messages to other method.
    private void checkVictoryConditionMet() {
        // will prolly have to change this due to us hardcoding a win message and all
        // other handling here.
        if (enemy.isDead()) {
            Utility.clearConsole();
            printEntityHP(player, Utility.GREEN);
            System.out.println("You have vanquished your foe!");
            exitingCombat();

        } else if (player.isDead()) {
            System.out.println("You have been slain");
            // currently calling the state to game over on death here.
            GameStateManager.getInstance().setCurrentState(GameState.GAME_OVER);
            exitingCombat();
        }
    }

    private void exitingCombat() {
        if (enemy.isDead()) {
            Transactions.receiveLootFromCombat(player, enemy, sc);
            gainExperience();
        }
        isCombatInProgress = false;
    }

    private void gainExperience() {

        if (player.isDead() || !enemy.isDead()) {
            return;
        }

        if (player != null && enemy != null) {
            player.gainExperience(enemy.calcExperienceGiven());
        } else {
            System.err.println(
                    "Either entity was null, and so the player couldn't get Experience at end of combat.");
        }
    }
    //endregion
}
