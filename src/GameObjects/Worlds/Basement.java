package GameObjects.Worlds;

import Global.*;

public class Basement extends Zone {
    public static boolean bossDefeated = false;
    public Basement() {
        super("the deepest recesses of the cave..", "A musty moist marred basement filled with spiderwebs", false);
        
    }

    public void bossfight() {
        Utility.clearConsole();
        Utility.slowPrint("As you poke through the loose rocks you find yourself in a musty basement.\n You recognize this basement... It's the basement of the tavern!!!!");
        Utility.slowPrint("You see a large figure in the shadows.\nIt roars and charges at you!");
        Utility.slowPrint("You destroy his stash of old HDDs and he dies on the spot\nCongratulations...  YOU WIN!!!!!!!");
        bossDefeated = true;
        //start combat with boss

    }
}