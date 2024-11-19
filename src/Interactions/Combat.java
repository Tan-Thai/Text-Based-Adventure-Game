package Interactions;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Entities.Entity;
import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Items.EquipmentType;
import GameObjects.Items.WeaponType;
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
	private HostileCharacter enemy;

	private int playerHitCount = 0;
	private int enemyHitCount = 0;
	private int playerInitiative = 0;
	private int enemyInitiative = 0;

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
	// Currently takes in playerchar and hostilechar as a simple combat, but also to
	// reach their respective methods.
	public void initiateCombat(Entity firstActor, HostileCharacter secondActor, Scanner sc) {
		this.player = firstActor;
		this.enemy = secondActor;
		this.sc = sc;

		System.out.println("You have entered combat with a " + enemy.getName() + "!");
		System.out.println(enemy.getHostileEntityDescription());
		Utility.promptEnterKey(sc);
		Utility.clearConsole();
		isCombatInProgress = true;
		combatLoop();
	}

	private void combatLoop() {
		// creates the intiative for both entitys
		playerInitiative = calcInitiative(player, Utility.GREEN);
		enemyInitiative = calcInitiative(enemy, Utility.RED);

		// loop for combat.
		while (isCombatInProgress) {
			Utility.clearConsole();
			printInitative();
			// Prints all hp's
			printEntityHP(player, Utility.GREEN);
			printEntityHP(enemy, Utility.RED);

			if (enemyInitiative > playerInitiative) {
				enemyHitCount = calcAttack(enemy, Utility.RED);
				resolveAttack(player, enemy, enemyHitCount);
				// printEnemyHits();
				checkVictoryConditionMet();
				playerattackmethod();
				checkVictoryConditionMet();
			} else {

				playerattackmethod();
				checkVictoryConditionMet();
				enemyHitCount = calcAttack(enemy, Utility.RED);
				resolveAttack(player, enemy, enemyHitCount);
				// printEnemyHits();
				checkVictoryConditionMet();
			}
			Utility.promptEnterKey(sc);
		}
	}

	private void playerattackmethod() {
		if (player.isDead()) {
			return;
		}
		System.out.println("to attack press 1, to use inventory press 2, to flee press 3");
		switch (Utility.checkIfNumber(sc)) {
			case 1 -> {
				playerHitCount = calcAttack(player, Utility.GREEN);
				// printPlayerHits();
				resolveAttack(enemy, player, playerHitCount);
			}
			case 2 -> player.inspectEntity(sc);
			case 3 -> {
				if (((HostileCharacter) enemy).getHostileEntityType().equals(HostileEntityType.BOSS)) {
					System.out.println("You can't run from a boss!");
					Utility.clearConsole();
				} else {
					System.out.println("You have fled from combat!");
					isCombatInProgress = false;
				}
			}
			default -> System.out.println("incorrect input, try again");
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

		// Used to add break inbetween lines in the console
		System.out.println();
		return hitCount;
	}

	private int calcInitiative(Entity actor, String colour) {

		int speedCount = Utility.rollDicePool(actor.getIntelligence(), colour, OptionalInt.empty(), OptionalInt.empty(),
				OptionalInt.empty());
		System.out.println();
		return speedCount;
	}

	// checks and returns the added weapon damage if actor has one equipped.
	private int addedWeaponDamage(Entity actor) {
		if (actor.getEquipmentList().getEquipment(EquipmentType.WEAPON) != null)
			return actor.getEquipmentList().getEquipment(EquipmentType.WEAPON).getEffectValue();
		else
			return 0;
	}

	// checks and returns the protection from the armor.
	private int addedArmorSave(Entity actor) {
		if (actor.getEquipmentList().getEquipment(EquipmentType.ARMOUR) != null)
			return actor.getEquipmentList().getEquipment(EquipmentType.ARMOUR).getEffectValue();
		else
			return 0;
	}

	private void printInitative() {
		System.out.println("Your initative is " + playerInitiative);
		System.out.println("Your enemies initiative is " + enemyInitiative);
	}

	private void resolveAttack(Entity defender, Entity attacker, int attackHits) {
		System.out.println(attacker.getName() + " gets " + attackHits + " hits");
		System.out.println(defender.getName() + " has " + defender.getDexterity()
				+ " Dextarity which is subtracted from your hits");
		attackHits -= defender.getDexterity();
		System.out.println(attackHits);

		if (attackHits <= 0) {
			System.out.println(attacker.getName() + " misses");
		} else {
			int weaponDamage = addedWeaponDamage(attacker);
			// if statement could be skipped on final product, but this is structured as a
			// test for now.
			if (weaponDamage > 0) {

				weaponDamage = getDamageConversionBasedOnType(weaponDamage, attacker, defender);
				System.out.println("Weapon added " + weaponDamage + " damage.");
				System.out.println("Making it " + (attackHits + weaponDamage) + " hits");
				attackHits += weaponDamage;
			}

			System.out.println("added armor save is: " + addedArmorSave(defender));

			attackHits -= addedArmorSave(defender);
			if (attackHits < 0) {
				attackHits = 0;
			}

			defender.takeDamage(attackHits);
		}
	}

	private int getDamageConversionBasedOnType(int weaponDamage, Entity attacker, Entity defender) {
		
		if(attacker.getEquipmentList().getEquipment(EquipmentType.WEAPON)!=null)
		WeaponType = attacker.getEquipmentList().getEquipment(EquipmentType.WEAPON).get
		
		
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getDamageConversionBasedOnType'");
	}

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
		if (isCombatInProgress) {
			gainExperience();
		}
		isCombatInProgress = false;
		Utility.promptEnterKey(sc);
		Utility.clearConsole();
	}

	private void gainExperience() {

		if (player.isDead() || !enemy.isDead()) {
			return;
		}

		if (player instanceof PlayerCharacter && enemy instanceof HostileCharacter) {
			((PlayerCharacter) player).gainExperience(((HostileCharacter) enemy).calcExperienceGiven());
		} else {
			System.err.println(
					"Either entity was of an unexpected type, and so the player couldn't get Experience at end of combat.");
		}
	}

}
