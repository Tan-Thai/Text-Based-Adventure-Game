package GameObjects.Worlds;

import Core.GameState;
import Core.GameStateManager;
import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Interactions.Adventure;
import Resources.Config;

public class Tavern extends Zone {
    public Tavern() {
        super("Rothollow tavern",
                """
                                A giant hollowed out log, covered in deep green moss and large red mushrooms. 
                                With a small door and tiny windows from which warm light emit. 
                                Ruckus song can be heard from within. 
                        """, true, ZoneType.TAVERN, null, 0);
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
        // make npc to shop from since they have inventory
    }

    public void retireCharacter(PlayerCharacter pc) { // retire method
        Utility.clearConsole();
        Utility.slowPrint("You retire from adventuring and live out the rest of your days in peace.");
        GameStateManager.getInstance().setCurrentState(GameState.VICTORY);
        Utility.promptEnterKey(sc);

        //Removed call to EndofGameScreen due to it being phased out.
        //check of state should not exist here because the only time this will be visible is if
        //the player already are in a state of VICTORY. --- TT


    }

    // opnens up tavern menu for resting and shopping for
    public void tavernMenu(PlayerCharacter pc) {

        boolean exitMenu = false;
        // items

        while (!exitMenu) {
            Utility.clearConsole();
            System.out.println(
                    "0. Exit menu\n" +
                    "1. Rest (restore health)\n" +
                    "2. Open shop (buy items)");
            if (pc.getLevel() >= Config.PC_RETIREMENT_LEVEL) {
                System.out.println("3. Retire (End game)");
            }
            Utility.slowPrint("Choose an action:");
            // talk to npcs? Listen to rumours? etc.
            // TODO npc should be newed up here to create an instance of neutral character
            int choice = Utility.checkIfNumber(sc);

            switch (choice) {
                case 0:
                    exitMenu = true;
                    break;
                case 1:
                    takeRest(pc);
                    Utility.promptEnterKey(sc);
                    break;
                case 2:
                    openShop(pc);
                    Utility.promptEnterKey(sc);
                    break;

                case 3:
                    if (pc.getLevel() < Config.PC_RETIREMENT_LEVEL) {
                        Utility.slowPrint(
                                "You are not experienced enough to retire yet. You must reach even higher heights!");
                        Utility.promptEnterKey(sc);
                        break;
                    } else {
                        Utility.slowPrint("Are you sure you want to retire? (Y/N)");
                        if (Utility.checkYesOrNo(sc)) {
                            retireCharacter(pc);
                            exitMenu = true;
                        } else {
                            Utility.slowPrint("Returning to tavern menu...");
                            Utility.promptEnterKey(sc);
                        }
                    }

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

}
