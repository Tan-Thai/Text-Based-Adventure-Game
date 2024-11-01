package GameObjects.Worlds;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Global.Utility.Slowprint; // Add this line to import Slowprint
import GameObjects.Entities.NPCs.*;
import GameObjects.Items.DamageEffect;
import GameObjects.Items.Equipment;
import GameObjects.Items.Item;

public class Tavern extends Zone {
    public Tavern() {
        super( "Tavern", "A lively tavern filled with patrons and the smell of ale.", true); 

    }
    
    public void takeRest(PlayerCharacter pc) { // take rest method
        Utility.clearConsole();
        Slowprint.sp("You take a rest and regain some health.");
        pc.setHealth(pc.getMaxHealth());
        Slowprint.sp("Your health is now " + pc.getHealth());
        
        
    }

    public void openShop(PlayerCharacter pc) { // open shop method, WIP
        Utility.clearConsole();
        Shopkeeper shopkeeper = new Shopkeeper("Shopkeeper", 10);
        populateShop(shopkeeper);
     //   pc.getInventory();
        Slowprint.sp("You open the shop and see a variety of items for sale.");
        System.out.println("Items for sale:");
        shopkeeper.getInventory().printInventory();
        // make npc to shop from since they have inventory
        Utility.promptEnterKey(sc);
    }

    private void populateShop(Shopkeeper shopkeeper) {
        shopkeeper.getInventory().spawnItem(new Equipment("Sword", "A simple sword", 5, new DamageEffect(2)));
        shopkeeper.getInventory().spawnItem(new Equipment("Axe", "A simple axe", 10, new DamageEffect(3)));
        shopkeeper.getInventory().spawnItem(new Equipment("Bow", "A simple bow", 5, new DamageEffect(2)));
        shopkeeper.getInventory().spawnItem(new Equipment("Staff", "A simple staff", 2, new DamageEffect(1)));
        shopkeeper.getInventory().spawnItem(new Equipment("Dagger", "A simple dagger", 2, new DamageEffect(1)));
        shopkeeper.getInventory().spawnItem(new Equipment("Mace", "A simple mace", 10, new DamageEffect(3)));
        // populate shop with items
    }


/* 
    public void setOut(PlayerCharacter pc, Area zone) { //not needed now?
        Utility.clearConsole();
        Global.Utility.Slowprint.sp("You leave the tavern and head out in the looming dark forest.");
        pc.setCurrentZone(zone.FOREST);
        Utility.promptEnterKey(sc);
        
    }   */

   // @Override //testar Override
  //  public void populateEvents(String event, String description) {
        //${0: TODO Auto-generated method stub
  //      super.populateEvents(event, description);
 //   }
}
