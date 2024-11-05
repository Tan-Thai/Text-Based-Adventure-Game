import java.util.Scanner;

import GameObjects.Entities.PlayerCharacter;
import GameObjects.Worlds.Zone;
import Global.Utility;

public class Game {
    Scanner sc = new Scanner(System.in);

    public static void gameMenu(PlayerCharacter pc, Scanner sc) {

        while (true) {
        Utility.clearScanner(sc);
        Utility.clearConsole();
        System.out.println("Welcome to the game!");
        System.out.println("1. Start Game");
        System.out.println("2. How to Play");
        System.out.println("3. Exit");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                System.out.println("Starting game...");
                travelTest(pc, sc);
                // startGame(); call main game loop
                break;
            case 2:
                Utility.howToPlay(sc);
                break;
            case 3:
                System.out.println("Exiting game...");
                System.exit(0);
                break;
        }

    }


        
    }

    private static void travelTest(PlayerCharacter pc, Scanner sc) {
        Zone room = new Zone();
     while (room.checkGameOver() == false) {
         Utility.clearConsole();
         room.travelMenu(pc, room);
        } 
    }
    
}