package Global;

import java.util.Scanner;

public class EndOfGameScreen {
    public static boolean gameOver = true;
    public static boolean death = true;
    public static boolean gameOnGoing = true;

    public static Scanner sc = new Scanner(System.in);

    public static void gameOverCheck() {
        if (gameOver == true) {
            if (death == false) {
                victoryScreen();
            } else {
                gameOverScreen();
            }
        } else {
        }

    }

    public static void victoryScreen() {
        System.out.println("Victory!");
        System.out.println("Would you like to play again?(Y/N?)");
        if(Utility.checkYesOrNo(sc))
        {
            gameOnGoing=true;
        }
        else
        {
            gameOnGoing=false;
        }

    }

    public static void gameOverScreen() {
        System.out.println("Game Over!");
        System.out.println("Would you like to play again?(Y/N?)");
        if(Utility.checkYesOrNo(sc))
        {
            gameOnGoing=true;
        }
        else
        {
            gameOnGoing=false;
        }

    }

    public static void endOfgame() {
        EndOfGameScreen.gameOverCheck();

    }

    //just for testing the code
    public static void main(String[] args) {
        while (gameOnGoing) {
            EndOfGameScreen.endOfgame();
        }
    }

}