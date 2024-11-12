package GameObjects.Worlds;

import Global.Utility;

public class Basement extends Zone {

    public Basement() {
        super("the deepest recesses of the cave..", "A musty moist marred basement filled with spiderwebs", false,
                ZoneType.BASEMENT, null, 1);
    }

   // odd line conflict that didnt look like it had any issues
   // In case something break, check here.

    public void bossIntro() {
        Utility.clearConsole();
        System.out.println("You continue even deeper and notice a few loose rocks that could crumble at any moment");
        Utility.slowPrint(
                "As you poke through the loose rocks you find yourself in a musty basement.\n You recognize this basement... It's the basement of the tavern!!!!");
        Utility.slowPrint("You see a large figure in the shadows.\nIt roars and charges at you!");
        
        
        
        

    }
}