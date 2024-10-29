package GameObjects.Worlds;

import Global.*;

public class Basement extends Zone {
    public Basement() {
        super("Tavern basement", "A musty moist marred basement filled with spiderwebs", false);
        
    }

    public void bossfight() {
        Utility.clearConsole();
        System.out.println("As you descend the stairs, you see a large figure in the shadows.\nIt roars and charges at you!");
        Slowprint.sp("You destroy his stash of old HDDs and he dies on the spot\nCongratulations...  YOU WIN!!!!!!!");
        //start combat with boss

    }
}