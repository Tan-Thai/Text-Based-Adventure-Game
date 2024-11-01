package GameObjects.Worlds;

import Global.*;
import Global.Utility.Slowprint;

public class Basement extends Zone {
    public static boolean bossDefeated = false;
    public Basement() {
        super("the deepest recesses of the cave..", "A musty moist marred basement filled with spiderwebs", false);
        
    }

    public void bossfight() {
        Utility.clearConsole();
        Slowprint.sp("As you poke through the loose rocks you find yourself in a musty basement.\n You recognize this basement... It's the basement of the tavern!!!!");
        Slowprint.sp("You see a large figure in the shadows.\nIt roars and charges at you!");
        Slowprint.sp("You destroy his stash of old HDDs and he dies on the spot\nCongratulations...  YOU WIN!!!!!!!");
        bossDefeated = true;
        //start combat with boss

    }
}