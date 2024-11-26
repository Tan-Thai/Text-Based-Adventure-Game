import Core.GameState;
import Core.GameStateManager;
import GameObjects.Data.Info;
import GameObjects.Worlds.ZoneManager;
import Global.Utility;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner sc = new Scanner(System.in);
        Boolean replay;
        /*File file = new File("C:\\Users\\wille\\Documents\\Java github repos\\text\\Text-Based-Adventure-Game\\src\\Resources\\musicSample\\MusicForGame.wav");
        AudioInputStream audioStream=AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.loop(3000);
        clip.start();*/
         
        Info.gameIntro();
        Utility.promptEnterKey(sc);
        
        do {
            Game game = new Game(sc);
            
                       
            startMenu(game, sc);

            System.out.print("Do you wish to play again? (Y/N): ");
            replay = Utility.checkYesOrNo(sc);

            if (replay) {
                System.out.println("Restarting game...");
                Utility.promptEnterKey(sc);
                ZoneManager.getInstance().resetZones();
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

        while (GameStateManager.getInstance().getCurrentState() == GameState.RUNNING) {
            Utility.clearConsole();
            System.out.println("Main menu" +
                               "\n1. Start Game" +
                               "\n2. How to Play");
            System.out.print("\nEnter your choice or press 0 to close program: ");
            switch (Utility.checkIfNumber(sc)) {
                case 1 -> {
                    // TODO: adding player should be here inside game menu when we select start game
                    System.out.println("Starting game...");
                    game.run();
                }
                case 2 -> Info.howToPlay(sc);
                case 0 -> {
                    System.out.println("Exiting game...");
                    sc.close();
                    System.exit(0);
                }
            }
        }
    }
}

