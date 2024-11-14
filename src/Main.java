import Core.GameState;
import Core.GameStateManager;
import GameObjects.Data.Info;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boolean replay;

        do {
            Game game = new Game(sc);
            startMenu(game, sc);

            System.out.print("Do you wish to play again? (Y/N): ");
            replay = Utility.checkYesOrNo(sc);

            if (replay) {
                Utility.promptEnterKey(sc);
                GameStateManager.getInstance().setCurrentState(GameState.RUNNING);
            }

        } while (replay);

        Utility.clearConsole();
        System.out.println("Thank you for playing!");
        Utility.promptEnterKey(sc);
        sc.close();
        System.exit(0);
    }

    public static void startMenu(Game game, Scanner sc) {
        Utility.clearConsole();

        while (true) {
        System.out.println("Welcome to the game!" +
                "\n1. Start Game" +
                "\n2. How to Play" +
                "\n3. Exit");

        switch (Utility.checkIfNumber(sc)) {
            case 1 -> {
                // TODO: adding player should be here inside game menu when we select start game
                System.out.println("Starting game...");
                game.run();
            }
            case 2 -> Info.howToPlay(sc);
            case 3 -> {
                System.out.println("Exiting game...");
                sc.close();
                System.exit(0);
            }
            }
        }
    }
}
