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
                    "5. How to rest and shop for items in the tavern\n" + Config.MENU_CHOICE_STRING);
            int choice = Utility.checkIfNumber(sc);
            Utility.clearConsole();

            switch (choice) {
                case 1:
                    System.out.println("Your character has different stats that determine how well they perform in different situations.\nHealth: Determines how much damage you can take before you die.\nStrength: Determines how much damage you deal in combat.\nDexterity:\nExperience: Determines how close you are to leveling up.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 2:
                    System.out.println("Combat is turn-based. You and your enemy take turns attacking each other.\nYou can choose to attack, use an item, or run away.\nThe combat ends when either you or your enemy's health reaches 0.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 3:
                    System.out.println("You can navigate between different zones by using the travel menu. You may travel to the Forest or the Swamp freely but advancing more requires you to explore these zones.\nYou can also explore different areas within a zone by choosing to wander in the travel menu.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 4:
                    System.out.println("You can interact with items by choosing to use them in the inventory menu.\nYou can also inspect items to view their descriptions and effects.");
                    Utility.promptEnterKey(sc);
                    Utility.clearConsole();
                    break;
                case 5:
                    System.out.println("You can rest and shop for items in the tavern. Resting will restore your health, and you can buy items from the shopkeeper.");
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
}
