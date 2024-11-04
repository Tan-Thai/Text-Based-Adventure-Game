import java.util.Scanner;
import Global.Utility;

public class Game {
    Scanner sc = new Scanner(System.in);

    public void gameMenu(Scanner sc) {

        System.out.println("Welcome to the game!");
        System.out.println("1. Start Game");
        System.out.println("2. How to Play");
        System.out.println("3. Exit");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                System.out.println("Starting game...");
                // startGame(); call main game loop
                break;
            case 2:
                Utility.howToPlay();
                break;
            case 3:
                System.out.println("Exiting game...");
                System.exit(0);
                break;
        }


        
    }
    
}
