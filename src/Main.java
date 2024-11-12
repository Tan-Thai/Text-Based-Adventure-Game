import Core.GameState;
import Core.GameStateManager;
import Global.Utility;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Boolean replay;

        do {
            Game game = new Game(sc);
            game.run();

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

}
