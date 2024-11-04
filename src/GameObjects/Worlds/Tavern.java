package GameObjects.Worlds;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;

public class Tavern extends Zone {
    public Tavern() {
        super( "Tavern", "A lively tavern filled with patrons and the smell of ale.", true); 

    }
    
    public void takeRest(PlayerCharacter pc) { // take rest method
        Utility.clearConsole();
        Utility.slowPrint("You take a rest and regain some health.");
        pc.setHealth(pc.getMaxHealth());
        Utility.slowPrint("Your health is now " + pc.getHealth());
        
        
    }

    public void openShop(PlayerCharacter pc) { // open shop method, WIP
        Utility.clearConsole();
     //   pc.getInventory();
     Utility.slowPrint("You open the shop and see a variety of items for sale.");
        System.out.printf("Items for sale:");
        // make npc to shop from since they have inventory
        Utility.promptEnterKey(sc);
    }
/* 
    public void setOut(PlayerCharacter pc, Area zone) { //not needed now?
        Utility.clearConsole();
        Global.Utility.Slowprint.sp("You leave the tavern and head out in the looming dark forest.");
        pc.setCurrentZone(zone.FOREST);
        Utility.promptEnterKey(sc);
        
    }   */

}
