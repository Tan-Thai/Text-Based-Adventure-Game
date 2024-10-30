package GameObjects.Worlds;
import GameObjects.Entites.PlayerCharacter;
import GameObjects.Worlds.Zones.Area;
import Global.Utility;
import Global.Utility.Slowprint; // Add this line to import Slowprint

public class Tavern extends Zone {
    public Tavern() {
        super( "Tavern", "A lively tavern filled with patrons and the smell of ale.", true); //????????????

    }
    
    public void takeRest(PlayerCharacter pc) {
        Utility.clearConsole();
        Slowprint.sp("You take a rest and regain some health.");
        pc.setHealth(pc.getMaxHealth());
        Slowprint.sp("Your health is now " + pc.getHealth());
        Utility.promptEnterKey(sc);
        
        
    }

    public void openShop(PlayerCharacter pc) {
        Utility.clearConsole();
     //   pc.getInventory();
        Slowprint.sp("You open the shop and see a variety of items for sale.");
        System.out.printf("Items for sale:");
        // call a method to open shop from items class?
        Utility.promptEnterKey(sc);
        
    }

    public void setOut(PlayerCharacter pc, Area zone) { //not needed now?
        Utility.clearConsole();
        Global.Utility.Slowprint.sp("You leave the tavern and head out in the looming dark forest.");
        pc.setCurrentZone(zone.FOREST);
        Utility.promptEnterKey(sc);
        
    }

   // @Override //testar Override
  //  public void populateEvents(String event, String description) {
        //${0: TODO Auto-generated method stub
  //      super.populateEvents(event, description);
 //   }
}
