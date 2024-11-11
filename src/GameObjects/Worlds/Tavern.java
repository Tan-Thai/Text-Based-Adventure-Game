package GameObjects.Worlds;

import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

public class Tavern extends Zone {
    public Tavern() {
        super("Tavern", "A lively tavern filled with patrons and the smell of ale.", true, ZoneType.TAVERN, null);
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
        System.out.print("Items for sale:");
    }


}
