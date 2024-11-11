import GameObjects.Data.Info;
import GameObjects.Entities.PlayerCharacter;
import Global.*;
import java.util.Scanner;

public class Game {

    public static void gameMenu(PlayerCharacter pc, Scanner sc) {

        while (true) {
            Utility.clearConsole();
            System.out.println("Welcome to the game!\n1. Start Game\n2. How to Play\n3. Exit");

            int choice = Utility.checkIfNumber(sc);

            switch (choice) {

            case 1 -> {
                System.out.println("Starting game...");
                travelTest(pc, sc);
                    // startGame(); call main game loop
                }
            case 2 -> Info.howToPlay(sc);
            case 3 -> {
                System.out.println("Exiting game...");
                System.exit(0);
                }
        }

        }

    }

    private static void travelTest(PlayerCharacter pc, Scanner sc) {
        while (true) {
            pc.getCurrentZone().travelMenu(pc);
            
            Utility.promptEnterKey(sc);
        }

    }

}
