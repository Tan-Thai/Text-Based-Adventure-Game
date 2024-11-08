package GameObjects.Data;

import java.util.Scanner;

import Global.Utility;

public class Info {
    

    public static void howToPlay(Scanner sc) {
            Utility.clearConsole();
            
            System.out.println("Here are some basic instructions on how to play:\nPlease choose the number corresponding to the option you need help with:");
            System.out.println("1. My character, it's stats and abilities\n2. How does combat work?\n3. How to navigate between or inside zones.\n4. How to interact with items\n5. How to rest and shop for items in the tavern\n6. Exit menu.\nEnter your choice: ");
            int choice = Utility.checkIfNumber(sc);
            Utility.clearConsole();

            switch (choice) {
                
                case 1:
                    System.out.println("Your character has different stats that determine how well they perform in different situations.\nHealth: Determines how much damage you can take before you die.\nStrength: Determines how much damage you deal in combat.\nDexterity:\nExperience: Determines how close you are to leveling up.");
                    Utility.promptEnterKey(sc);
                    howToPlay(sc);
                    break;
                case 2:
                    System.out.println("Combat is turn-based. You and your enemy take turns attacking each other.\nYou can choose to attack, use an item, or run away.\nThe combat ends when either you or your enemy's health reaches 0.");
                    Utility.promptEnterKey(sc);
                    howToPlay(sc);
                    break;
                case 3:
                    System.out.println("You can navigate between different zones by using the travel menu. You may travel to the Forest or the Swamp freely but advancing more requires you to explore these zones.\nYou can also explore different areas within a zone by choosing to wander in the travel menu.");
                    Utility.promptEnterKey(sc);
                    howToPlay(sc);
                    break;
                case 4:
                    System.out.println("You can interact with items by choosing to use them in the inventory menu.\nYou can also inspect items to view their descriptions and effects.");
                    Utility.promptEnterKey(sc);
                    howToPlay(sc);
                    break;
                case 5:
                    System.out.println("You can rest and shop for items in the tavern. Resting will restore your health and shopping will allow you to buy items.\nThis is all accessible through the tavern menu when your character is in the tavern.");
                    Utility.promptEnterKey(sc);
                    howToPlay(sc);
                    break;
                case 6:
                    System.out.println("Exiting menu...");
                    Utility.promptEnterKey(sc);
                    break;
                default:
                    Utility.slowPrint("Invalid choice. Please try again.");
                    Utility.promptEnterKey(sc);
                    howToPlay(sc);
                    break;
            }
            
    } 
}
