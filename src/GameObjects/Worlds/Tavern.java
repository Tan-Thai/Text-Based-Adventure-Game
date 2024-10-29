package GameObjects.Worlds;
import Global.Slowprint;

public class Tavern extends Zone {
    public Tavern() {
        super( "Tavern", "A lively tavern filled with patrons and the smell of ale", true);

    }
    
    public void takeRest() {
        Slowprint.sp("You take a rest and regain some health.");
        // call a method to regain health from player class or interactions class
    }

    public void openShop() {
        Slowprint.sp("You open the shop and see a variety of items for sale.");
        // call a method to open shop from items class?
    }

   // @Override //testar Override
  //  public void populateEvents(String event, String description) {
        //${0: TODO Auto-generated method stub
  //      super.populateEvents(event, description);
 //   }
}
