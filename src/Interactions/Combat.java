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
import java.util.OptionalInt;
import java.util.Scanner;

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
	// Currently takes in playerchar and hostilechar as a simple combat, but also to
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
		// creates the intiative for both entities
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
				enemyAttack(calcAttack(enemy, Utility.RED));
				checkVictoryConditionMet();
				playerAttack();
				checkVictoryConditionMet();
			} else {
				playerAttack();
				checkVictoryConditionMet();
				enemyAttack(calcAttack(enemy, Utility.RED));
				checkVictoryConditionMet();
			}
			Utility.promptEnterKey(sc);
		}
	}

	private void enemyAttack(int attackHits) {
		if (enemy.isDead()) {
			return;
		}
		System.out.println(enemy.getName() + " gets " + attackHits + " hits");
		System.out.println(player.getName() + " has " + player.getDexterity()
				+ " Dextarity which is subtracted from your hits");
		attackHits -= player.getDexterity();
		System.out.println(attackHits);

		if (attackHits <= 0) {
			System.out.println(enemy.getName() + " misses");
		} else {
			int weaponDamage = addedWeaponDamage(enemy);
			// if statement could be skipped on final product, but this is structured as a
			// test for now.
			if (weaponDamage > 0) {

				System.out.println("Weapon added " + weaponDamage + " damage.");
				System.out.println("Making it " + (attackHits + weaponDamage) + " hits");
				attackHits += weaponDamage;
			}

			System.out.println("added armor save is: " + addedArmorSave(player));

			attackHits -= addedArmorSave(player);
			if (attackHits < 0) {
				attackHits = 0;
			}

			player.takeDamage(attackHits);
		}
	}

	private void playerAttack() {
		if (player.isDead()) {
			return;
		}
		System.out.println("to attack press 1, to use inventory press 2, to flee press 3");
		switch (Utility.checkIfNumber(sc)) {
			case 1 -> {
				resolveAttack(enemy, player, calcAttack(player, Utility.GREEN));
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

	private void resolveAttack(HostileCharacter enemy, PlayerCharacter player, int attackHits) {
		System.out.println(player.getName() + " gets " + attackHits + " hits");
		System.out.println(enemy.getName() + " has " + enemy.getDexterity()
				+ " Dextarity which is subtracted from your hits");
		attackHits -= enemy.getDexterity();
		System.out.println(attackHits);

		if (attackHits <= 0) {
			System.out.println(player.getName() + " misses");
		} else {
			int weaponDamage = addedWeaponDamage(player);
			// if statement could be skipped on final product, but this is structured as a
			// test for now.
			if (weaponDamage > 0) {

				System.out.println("Weapon added " + weaponDamage + " damage.");
				System.out.println("Making it " + (attackHits + weaponDamage) + " hits");
				attackHits += weaponDamage;
				attackHits = getDamageConversionBasedOnType(attackHits, player, enemy);
			}

			System.out.println("added armor save is: " + addedArmorSave(enemy));

			attackHits -= addedArmorSave(enemy);
			if (attackHits < 0) {
				attackHits = 0;
			}

			enemy.takeDamage(attackHits);
		}
	}

	private int getDamageConversionBasedOnType(int damage, Entity attacker, HostileCharacter defender) {

		WeaponType tempWeaponType = WeaponType.DEFAULT;

		if (attacker.getEquipmentList().getEquipment(EquipmentType.WEAPON) != null) {
			tempWeaponType = ((Weapon) attacker.getEquipmentList().getEquipment(EquipmentType.WEAPON))
					.getWeaponType();
		}
		HostileEntityType tempHostileEntityType = defender.getHostileEntityType();
		switch (tempHostileEntityType) {
			case DRACONIC:
				if (tempWeaponType == WeaponType.FIRE) {
					// TODO: fix here what happens if this is an un-even number
					return damage / 2;
				}
				return damage;
			case TOADKIN:
				if (tempWeaponType == WeaponType.FIRE) {
					// TODO: fix here what happens if this is an un-even number
					return damage / 2;
				}
				return damage;
			case TROLLKIN:
				if (tempWeaponType == WeaponType.FIRE || tempWeaponType == WeaponType.SUNLIGHT) {
					return damage * 2;
				}
			case UNDEAD:
				if (tempWeaponType == WeaponType.FIRE || tempWeaponType == WeaponType.HOLY
						|| tempWeaponType == WeaponType.SUNLIGHT) {
					return damage * 2;
				}
				return damage;
			default:
				return damage;
		}
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
