package GameObjects.Worlds;

import GameObjects.Entities.PlayerCharacter;
import Global.EndOfGameScreen;
import Global.Utility;

public class Tavern extends Zone {
    public Tavern() {
        super("Rothollow tavern",
        """
                A giant hollowed out log, covered in deep green moss and large red mushrooms. 
                With a small door and tiny windows from which warm light emit. 
                Ruckus song can be heard from within. 
        """,    true, ZoneType.TAVERN, null);
    }

    // Heals character.
    public void takeRest(PlayerCharacter pc) {
        Utility.clearConsole();
        Utility.slowPrint("You take a rest and regain some health.");
        pc.setHealth(pc.getMaxHealth());
        Utility.slowPrint("Your health is now " + pc.getHealth());

    }

    // TODO: add method to call to sell OR call to buy.
    public void openShop(PlayerCharacter pc) { // open shop method, WIP
        Utility.clearConsole();
        // pc.getInventory();
        // make npc to shop from since they have inventory
        Utility.slowPrint("You open the shop and see a variety of items for sale.");
        System.out.printf("Items for sale:");
        // make npc to shop from since they have inventory
    }

    public void retireCharacter(PlayerCharacter pc) { // retire method
        Utility.clearConsole();
        Utility.slowPrint("You retire from adventuring and live out the rest of your days in peace.");
        Utility.promptEnterKey(sc);
        EndOfGameScreen.victoryScreen();
        
    }

}
