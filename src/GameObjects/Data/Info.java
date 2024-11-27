package GameObjects.Data;

import java.util.Scanner;
import Resources.Config;

import Global.Utility;

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
        System.out.println("Welcome to the game!\n" +
                "You are a hero who has been tasked with saving the town from an evil that lurks in the basement of the tavern.\n" +
                "You must explore different zones, fight monsters, and collect items to prepare for the final battle.\n" +
                "Good luck!");
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
}

