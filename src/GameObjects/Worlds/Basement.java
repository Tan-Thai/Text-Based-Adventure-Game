package GameObjects.Worlds;

import Global.*;

public class Basement extends Zone {
    public static boolean bossDefeated = false;

    public Basement() {
        super("the deepest recesses of the cave..", "A musty moist marred basement filled with spiderwebs", false,
                ZoneType.BASEMENT, null);

    }

    public void bossFight() {
        Utility.clearConsole();
        System.out.println("You continue even deeper and notice a few loose rocks that could crumble at any moment");
        Utility.slowPrint(
                "As you poke through the loose rocks you find yourself in a musty basement.\n You recognize this basement... It's the basement of the tavern!!!!");
        Utility.slowPrint("You see a large figure in the shadows.\nIt roars and charges at you!");
        Utility.slowPrint(
                "You destroy his stash of old HDDs and he dies on the spot");
        
        EndOfGameScreen.gameOverCheck();
        

    }
}