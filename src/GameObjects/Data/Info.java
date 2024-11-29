package GameObjects.Data;

import GameObjects.Entities.PlayerCharacter;
import Global.Utility;
import Core.Config;

import java.util.Scanner;

public class Info {
    public static void howToPlay(Scanner sc) {
        Utility.clearConsole();
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.print("How to Play Menu\n" +
                             "1. How do stats work?\n" +
                             "2. How does combat work?\n" +
                             "3. How to navigate between or inside zones.\n" +
                             "4. How to interact with items\n" +
                             "5. How to rest and use the shop in the tavern\n" + Config.MENU_CHOICE_STRING);
            int choice = Utility.checkIfNumber(sc);
            Utility.clearConsole();

            switch (choice) {
                case 1:
                    System.out.println("Your character has different stats that determine how well they perform in different situations.\nHealth: Determines how much damage you can take before you die.\nStrength: Determines how much damage you deal in combat.\nDexterity: Determines your defensive capabilities\nIntelligence: Determines the order in combat.\nExperience: Determines how close you are to leveling up.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 2:
                    System.out.println("Combat is turn-based. You and your enemy take turns attacking each other.\nYou can choose to attack, use an item, or run away.\nThe combat ends when either you or your enemy's health reaches 0.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 3:
                    System.out.println("You can navigate between different zones by using the travel menu. You may travel to the Forest or the Swamp freely at the start but advancing more requires you to explore these zones.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 4:
                    System.out.println("You can interact with items by choosing to use them in the inventory menu.\nYou can also inspect items to view their descriptions and effects.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 5:
                    System.out.println("You can rest and shop for items in the tavern. Resting will restore your health, and you can buy or sell items from the shopkeeper.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 0:
                    exitMenu = true;
                    Utility.clearConsole();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
            }
        }
    }

    //Temporary game intro, insert actual Wille stuff here
    public static void gameIntro() {
        System.out.println(Utility.GREEN + "  ___ ___                                               ___  ___ ___  " +
                           Utility.RESET);
        System.out.println(Utility.GREEN + " /   |   \\   ___________  ____D  ______    ___ ______  _| |_/   |   \\  " +
                           Utility.RESET);
        System.out.println(
                Utility.GREEN + "/    ~    \\_/ __ \\_  __ \\/  _ \\/  ___/    \\_____\\__  \\|_   _|   ~    \\ " +
                Utility.RESET);
        System.out.println(
                Utility.GREEN + "\\    Y    /\\  ___/|  | \\(  <_> )___ \\      |  |_> > __ \\| |\\    Y    /" +
                Utility.RESET);
        System.out.println(
                Utility.GREEN + " \\___|_  /  \\___> |  |   \\____/____  >     |   __(____  / | \\___|_  / " +
                Utility.RESET);
        System.out.println(
                Utility.GREEN + "       \\/          \\/              \\/      |__|         \\/        \\/ " +
                Utility.RESET);
        System.out.println();
        System.out.println(Utility.RED + "By: Wolf light studios" + Utility.RESET);
        System.out.println();

        Utility.slowPrint("The land is cursed in darkness. \r\n" + //
                          "Plauge, war and famine have pushed the tenderfoot folk to flee. \r\n" + //
                          "But now they find themselves trapped in Bleakstone Vale. \r\n" + //
                          "Being a gentle and soft-hearted kin, they need a savior.\r\n" + //
                          "They need you!", 40);
        System.out.println();
        Utility.slowPrint("Restore the Ember Prisms.\r\n" + //
                          "These crystals were once the pride of the land and used to banish the dark of Evernight.\r\n" +
                          //
                          "Held in ancient seats of power, these relic beacons kept the shroud at bay.\r\n" + //

                          "But they have fallen to the tooth of time, disrepair and decay have claimed them\r\n" + //

                          "As the seats crumbled the heirlooms were lost. \r\n" + //

                          "Seek them in the perilous wilds! \r\n" + //

                          "Restor them to the seats!\r\n" + //

                          "Banish the darkness!\r\n", 40);
    }

    public static void adventureMenuPrint() {
        System.out.println(
                "1. Explore" +
                "\n2. Inspect yourself" +
                "\n3. Travel" +
                "\n4. Remind me how to play again");
    }

    public static void adventureMenuTavernPrint() {
        System.out.println(
                "1. Tavern menu" +
                "\n2. Inspect yourself" +
                "\n3. Travel" +
                "\n4. Remind me how to play again");
    }

    public static void characterIntro(PlayerCharacter pc, Scanner sc) {
        Utility.clearConsole();
        Utility.slowPrint("Here is your chosen character and their attributes:\n");
        pc.displayStats();
        Utility.promptEnterKey(sc);
        Utility.clearConsole();
        Utility.slowPrint("You begin your tale.. just outside Rothollow tavern... \n" + """ 
                It is a giant hollowed out log, covered in deep green moss and large red mushrooms. 
                With a small door and tiny windows from which warm light emit.
                Ruckus song can be heard from within.
                
                You decide to enter the tavern...
                """, 20);
        Utility.promptEnterKey(sc);
    }

}

