import Core.GameState;
import Core.GameStateManager;
import GameObjects.Data.Info;
import GameObjects.Data.ItemId;
import GameObjects.Data.ItemRepository;
import GameObjects.Data.PlayerClassRepository;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.ZoneManager;
import Global.Utility;
import Interactions.Adventure;
import Core.Config;

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
        // Currently temporary with future plan of adding unique starting items based on class.
        addStarterItems(pc);

        // displays character info and intro to tavern.
        Info.characterIntro(pc, sc);
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

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = Utility.checkIfValidString(sc);

        System.out.println("Your name is " + nameInput + "!");
        Utility.promptEnterKey(sc);
        return playerCharacterClassSelect(nameInput, sc);
    }

    private static void runGame(PlayerCharacter pc, Scanner sc) {
        // ive noticed that a couple of the death's that can occur leads to double enter
        // prompts.
        Utility.clearConsole();

        Adventure.adventureMenu(pc, sc, pc.getCurrentZone());
        Utility.promptEnterKey(sc);
    }

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
        Utility.slowPrint("Choose a class");
        System.out.println(
                "1. Barbarian (Good at dealing damage and taking damage)\n2. Rogue (Hard to hit and fast)\n3. Wizard (Clever and astmatic)");
        System.out.print(Config.MENU_CHOICE_STRING);
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

    // region TT - Testing/ Temporary Area

    private static void addStarterItems(PlayerCharacter pc) {
        pc.getInventory().addItem(ItemRepository.getItemById(ItemId.RUSTY_LONGSWORD));
        pc.getInventory().addItem(ItemRepository.getItemById(ItemId.GREAT_AXE));
        pc.getInventory().addItem(ItemRepository.getItemById(ItemId.HEALTH_POTION));
        for (int i = 1; i <= 7; i++)
            pc.getInventory().addItem(ItemRepository.getItemById(ItemId.POISON_POTION));
    }
    // endregion

}
