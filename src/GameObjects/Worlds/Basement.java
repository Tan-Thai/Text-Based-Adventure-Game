package GameObjects.Worlds;

import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.HostileEntityType;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Interactions.Combat;

import java.util.Scanner;

public class Basement extends Zone {

    public Basement() {
        super("the deepest recesses of the cave..", "A musty moist marred basement filled with spiderwebs", false,
                ZoneType.BASEMENT, null, 1);
    }

   // odd line conflict that didnt look like it had any issues
   // In case something break, check here.

    
    @Override 
    public void adventureMenu(PlayerCharacter pc, Scanner sc) {
        Utility.clearConsole();
        System.out.println("You continue even deeper and notice a few loose rocks that could crumble at any moment");
        Utility.slowPrint(
                "As you poke through the loose rocks you find yourself in a musty basement.\n You recognize this basement... It's the basement of the tavern!!!!");
        Utility.slowPrint("You see a large figure in the shadows.\nIt roars and charges at you!");

        Utility.promptEnterKey(sc);
        HostileCharacter boss = new HostileCharacter("Tavern Keeper", 100, 20, 20, 20, 20, HostileEntityType.DRACONIC);
        
        Combat.getInstance().initiateCombat(pc, boss, sc);

        
    }
}