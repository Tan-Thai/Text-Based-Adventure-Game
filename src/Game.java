import java.util.Scanner;

import GameObjects.Data.Info;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.*;

public class Game {

    public static void gameMenu(PlayerCharacter pc, Scanner sc) {

        while (true) {
            Utility.clearConsole();
            System.out.println("Welcome to the game!\n1. Start Game\n2. How to Play\n3. Exit");

            int choice = Utility.checkIfNumber(sc);

            switch (choice) {

            case 1:
                System.out.println("Starting game...");
                travelTest(pc, sc);
                // startGame(); call main game loop
                break;
            case 2:
                Info.howToPlay(sc);
                break;
            case 3:
                System.out.println("Exiting game...");
                System.exit(0);
                break;
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
