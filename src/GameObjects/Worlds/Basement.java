package GameObjects.Worlds;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Interactions.ExploreZone;
import java.util.Scanner;

public class Basement extends Zone {

    public Basement() {
        super("the deepest recesses of the cave..", "A musty moist marred basement filled with spiderwebs", false,
                ZoneType.BASEMENT, null, 1);
    }

    public void checkIfBossDead(Zone zone, PlayerCharacter pc, Scanner sc) {
        if (ExploreZone.getUnclearedEncountersAmount(zone) != 1) {
            GameStateManager.getInstance().setCurrentState(GameState.VICTORY);
        } else if (ExploreZone.getUnclearedEncountersAmount(zone) > 0 && pc.getHealth() <= 0) {
            GameStateManager.getInstance().setCurrentState(GameState.GAME_OVER);
        } else {
            return;
        }
    }
    public void endGame() {
        if (GameStateManager.getInstance().getCurrentState() == GameState.VICTORY) {
            System.out.println("You have defeated the shadow beast and have saved the valley from disappearing into the ever-night\n" +
            "You have recovered the 'Ember Prism' from the guts of Maximus Rex\n" +
            "You will return the prisms to their rebuilt seats\n" + 
            "You are a hero and will be remembered for generations to come.");
            System.out.println();
            System.out.println();
            System.out.println();
             Utility.slowPrint("Made by Wolf light Studios:\r\n" + //
                        "Wille Virtanen - Developer\r\n" + //
                        "Tan Thai - Developer\r\n" + //
                        "Johan Ahlsten - Developer\r\n" + //
                        "Henrik Bergman - Developer\r\n" + //
                        "Maximilian \"Maximus\" Ygdell - Guest Role as Himself\r\n" + //   
                        "Thank you for playing our game!", 40);
        } else if (GameStateManager.getInstance().getCurrentState() == GameState.GAME_OVER) {
            System.out.println("You have been defeated by the shadow beast!\n" + 
                                "You have failed in you quest!\n" +
                                "Another hero must rise to save the people of the tender foot clan\n" +
                                "or the land will fall into darkness\n" +
                                "Thank you for playing!");
        } else {
            return;
        }
    }
}