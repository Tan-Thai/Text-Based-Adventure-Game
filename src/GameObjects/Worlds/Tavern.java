package GameObjects.Worlds;
import GameObjects.Entites.PlayerCharacter;
import Global.Slowprint;

public class Tavern extends Zone {
    public Tavern() {
        super( "Tavern", "A lively tavern filled with patrons and the smell of ale", true);

    }
    
    public void takeRest(PlayerCharacter pc) {
        Slowprint.sp("You take a rest and regain some health.");
        pc.setHealth(pc.getMaxHealth());
        
    }

    public void openShop(PlayerCharacter pc) {
        Slowprint.sp("You open the shop and see a variety of items for sale.");
        // call a method to open shop from items class?
    }

   // @Override //testar Override
  //  public void populateEvents(String event, String description) {
        //${0: TODO Auto-generated method stub
  //      super.populateEvents(event, description);
 //   }
}
