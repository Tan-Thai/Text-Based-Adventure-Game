package GameObjects.Worlds;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Interactions.Adventure;

public class Tavern extends Zone {
    public Tavern() {
        super("Rothollow tavern",
        """
                A giant hollowed out log, covered in deep green moss and large red mushrooms. 
                With a small door and tiny windows from which warm light emit. 
                Ruckus song can be heard from within. 
        """,    true, ZoneType.TAVERN, null, 0);
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
        //Removed call to EndofGameScreen due to it being phased out.
        //check of state should not exist here because the only time this will be visible is if
        //the player already are in a state of VICTORY. --- TT

        
    }

    
    public void tavernMenu(PlayerCharacter pc) { // opnens up tavern menu for resting and shopping for
        // items
        Utility.clearConsole();
        Utility.slowPrint("Choose an action:");
        System.out.println(
                "1. Rest (restore health)\n" + 
                "2. Open shop (buy items)\n" + 
                "3. Set out (Back to travel menu)\n" + 
                "0. Exit menu");
        if (GameStateManager.getInstance().getCurrentState() == GameState.VICTORY) {
            System.out.println("4. Retire (End game)");
        } // retire character, end game.
        // talk to npcs? Listen to rumours? etc.

        int choice = Utility.checkIfNumber(sc);

        switch (choice) {
            case 1:
                takeRest(pc);
                Utility.promptEnterKey(sc);
                tavernMenu(pc);
                break;
            case 2:
                openShop(pc);
                Utility.promptEnterKey(sc);
                tavernMenu(pc);
                break;
            case 3:
                Adventure.adventureMenu(pc, sc, pc.getCurrentZone());
                break;
            case 4:
                if (GameStateManager.getInstance().getCurrentState() != GameState.VICTORY) {
                    Utility.slowPrint(
                            "You are not experienced enough to retire yet. You must reach even higher heights!");
                    Utility.promptEnterKey(sc);
                    tavernMenu(pc);
                    break;
                } else {
                    Utility.slowPrint("Are you sure you want to retire? (Y/N)");
                    if (Utility.checkYesOrNo(sc)) {
                        retireCharacter(pc);
                    } else {
                        tavernMenu(pc);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

}
