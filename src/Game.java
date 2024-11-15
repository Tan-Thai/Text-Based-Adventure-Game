import Core.GameState;
import Core.GameStateManager;
import GameObjects.Data.PlayerClassRepository;
import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Items.*;
import GameObjects.Worlds.ZoneManager;
import Global.Utility;
import Interactions.Adventure;
import Interactions.Combat;

import java.util.Scanner;

public class Game {
    Scanner sc;
    GameStateManager gameManager;

    // TODO: migrate Game.java to core package.
    // stuff we can start generating as soon as the program starts running.
    public Game(Scanner sc) {
        // All instances and such can be made here such as zones etc.
        gameManager = GameStateManager.getInstance();
        ZoneManager.getInstance();
        this.sc = sc;
    }

    // the core game loop
    public void run() {
        Utility.clearConsole();
        // adding player char and basic items(temp items for now.)
        PlayerCharacter pc = setupUser(sc);
        // --- TESTS -----------------------
        addItems(pc);
        // Combat Test
        // combatTest(pc, addEnemyTemp(), sc);
        /*Equipment bow = new Equipment(
                "'bow temp'",
                "Your standard blade as a new adventurer.",
                EquipmentType.WEAPON,
                new DamageEffect(2));
        pc.getInventory().addItem(bow, sc);*/

        // Encounter test
        // encounterTest(pc, sc);

        // ----------------------------------
        while (gameManager.getCurrentState() != GameState.EXIT) {
            switch (gameManager.getCurrentState()) {
                case RUNNING:
                    runGame(pc, sc);
                    break;
                case VICTORY:
                    handleVictory();
                    break;
                case GAME_OVER:
                    handleGameOver();
                    break;
                default:
                    break;
            }
        }
    }

    // There are a few mega temporary methods atm, combat related ones being a few.
    // Wherever we invoke the combat method, we need to make sure it calls "Combat
    // combat = Combat.getInstance();"
    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = Utility.checkIfValidString(sc);
        // temp input of stats since i got no idea what we are basing it on.

        System.out.println("Your name is " + nameInput + "!");
        Utility.promptEnterKey(sc);
        return playerCharacterClassSelect(nameInput, sc);
    }

    private static void runGame(PlayerCharacter pc, Scanner sc) {
        // considering a potential while (Running) here to then break out if we die,
        // ive noticed that a couple of the death's that can occur leads to double enter prompts.
        Adventure.adventureMenu(pc, sc, pc.getCurrentZone());
        Utility.promptEnterKey(sc);
    }

/* 
    private static void encounterTest(PlayerCharacter pc, Scanner myScanner) {

        // This one is my bad, currently the ZoneManager needs to get instantiated for
        // the rest to work.
        // I will fix this later I promise! Just let it be for now!
        ZoneManager.getInstance();

        EncounterHandler encounterHandler = EncounterHandler.getInstance();

        while (ZoneManager.getZone(ZoneType.FOREST).hasUnclearedEncounters()) {
            System.out.println(ZoneManager.getZone(ZoneType.FOREST).getName());
            encounterHandler.runEncounter(pc, ZoneManager.getZone(ZoneType.FOREST).getUnclearedEncounter(), myScanner);
        }
    }
    */

    private void handleVictory() {
        System.out.println("Victory!");
        gameManager.setCurrentState(GameState.EXIT);
    }

    private void handleGameOver() {
        System.out.println("Game Over!");
        gameManager.setCurrentState(GameState.EXIT);
    }

    private static PlayerCharacter playerCharacterClassSelect(String nameInput, Scanner sc) {
        Utility.clearConsole();
        Utility.slowPrint("Choose a class:");
        System.out.println(
                "1. Barbarian (Strong with lots of health)\n2. Rogue (dexterous and swift)\n3. Wizard (Clever and astmatic)");

        int choice = Utility.checkIfNumber(sc);

        switch (choice) {
            case 1 -> {
                return PlayerClassRepository.getBarbarian(nameInput);
            }
            case 2 -> {
                return PlayerClassRepository.getRogue(nameInput);
            }
            case 3 -> {
                return PlayerClassRepository.getWizard(nameInput);
            }
            default -> {
                System.out.println("Invalid choice. Please try again.");
                return playerCharacterClassSelect(nameInput, sc);
            }
        }
    }

    // methods down here are most likely tests methods.
    private static HostileCharacter addEnemyTemp() {
        return new HostileCharacter("Troll", 5, 3, 2, 1, 1, HostileEntityType.TROLLKIN);
    }

    //region TT - Testing area
    private static void combatTest(PlayerCharacter pc, HostileCharacter enemy, Scanner sc) {
        // new up A COMBAT object, and it will be the only one since it's a singleton.
        Combat combat = Combat.getInstance();
        pc.inspectEntity(sc);
        // this is what we call and send in when a combat between 2 entities happens.
        combat.initiateCombat(pc, enemy, sc);
    }

    private static void addItems(PlayerCharacter pc) {
        Equipment sword = new Equipment(
                "'A Simple Sword'",
                "Your standard blade as a new adventurer.",
                EquipmentType.WEAPON,
                new DamageEffect(2),
                100);
        Equipment bow = new Equipment(
                "'bow temp'",
                "Your standard blade as a new adventurer.",
                EquipmentType.WEAPON,
                new DamageEffect(2),
                80);
        Potion potion = new Potion(
                "Health Potion",
                "Chug when ouch",
                new HealingEffect(5),
                20);
        Potion poison = new Potion(
                "Totally a Health Potion",
                "Chug for ouch",
                new DamageEffect(7),
                30);

        pc.getInventory().spawnItem(bow);
        pc.getInventory().spawnItem(sword);
        pc.getInventory().spawnItem(potion);
        pc.getInventory().spawnItem(poison);
        pc.getInventory().spawnItem(poison);
        pc.getInventory().spawnItem(poison);
        pc.getInventory().spawnItem(poison);
        pc.getInventory().spawnItem(poison);
        pc.getInventory().spawnItem(poison);
        pc.getInventory().spawnItem(poison);

    }
    //endregion

}
