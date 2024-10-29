package GameObjects.Worlds;

public class Basement extends Zone {
    public Basement() {
        super("Tavern basement", "A musty moist marred basement filled with spiderwebs", false);
        
    }

    public void bossfight() {
        System.out.println("As you descend the stairs, you see a large figure in the shadows.\nIt roars and charges at you!");
        //start combat with boss

    }
}