package GameObjects.Worlds;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Interactions.Combat;
import Interactions.ExploreZone;
import Interactions.Adventure;

import java.util.Scanner;

import Core.GameState;
import Core.GameStateManager;

public class Basement extends Zone {

    public Basement() {
        super("the deepest recesses of the cave..", "A musty moist marred basement filled with spiderwebs", false,
                ZoneType.BASEMENT, null, 1);
    }

    public void bossIntro() {
        Utility.clearConsole();
        System.out.println("YOU FIGHTO THE BOSSO");
        
    }

    // two temporary methods 
    public void checkIfBossDead(Zone zone, PlayerCharacter pc, Scanner sc) {
        if (ExploreZone.getUnclearedEncountersAmount(zone) == 0) {
            GameStateManager.getInstance().setCurrentState(GameState.VICTORY);
        } else if (ExploreZone.getUnclearedEncountersAmount(zone) > 0 && pc.getHealth() <= 0) {
            GameStateManager.getInstance().setCurrentState(GameState.GAME_OVER);
        } else {
            Adventure.adventureMenu(pc, sc, zone);
        }
    }
    public void endGame() {
        if (GameStateManager.getInstance().getCurrentState() == GameState.VICTORY) {
            System.out.println("You have defeated the boss and have saved the town from certain doom!\n" + 
            "You have saved the town from the evil that lurked in the basement of the tavern.\n" + 
            "You are a hero and will be remembered for generations to come.\n" + 
            "Thank you for playing!");
        } else {
            System.out.println("You have been defeated by the boss and the town is doomed. You have failed to save the town from the evil that lurked in the basement of the tavern. You are a failure and will be remembered as such. Thank you for playing!");
        }
    }



    

   /* 
    
    public void Adventure.adventureMenu(PlayerCharacter pc, Scanner sc) {
        Utility.clearConsole();
        System.out.println("You continue even deeper and notice a few loose rocks that could crumble at any moment");
        Utility.slowPrint(
                "As you poke through the loose rocks you find yourself in a musty basement.\n You recognize this basement... It's the basement of the tavern!!!!");
        Utility.slowPrint("You see a large figure in the shadows.\nIt roars and charges at you!");

        Utility.promptEnterKey(sc);
        HostileCharacter boss = new HostileCharacter("Tavern Keeper", 15, 2, 2, 20, 20, HostileEntityType.DRACONIC);
        
        Combat.getInstance().initiateCombat(pc, boss, sc);
         
    }
    */ 
}