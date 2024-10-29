package Global;
import java.util.Scanner;

public class GlobalMethodLibrary {
    public static String checkIfValidString (Scanner sc) {
        String userInput;
        do {
            userInput = sc.nextLine();
            if (!userInput.isEmpty()) {
                return userInput;
            }
            System.err.print("Please enter a name: ");
        } while (true); //forced loop
    }

    public static int checkIfNumber(Scanner sc) {
        int userInput;

        while (true) { //forced loop in while
            if (sc.hasNextInt()) {
                userInput = sc.nextInt();
                if (userInput >= 0) {
                    clearScanner(sc);
                    return userInput;
                }
            } else {
                sc.next();
            }
            System.err.print("Invalid input, please enter a number: ");
        }
    }

    public static boolean checkYesOrNo(Scanner sc) {
        do {
            String inputString = sc.nextLine().trim();
            if (inputString.length() == 1) {
                char choice = inputString.charAt(0);
                choice = Character.toUpperCase(choice);

                switch (choice) {
                    case 'Y':
                        return true;
                    case 'N':
                        return false;
                }
            }
            System.err.print("Invalid input, please enter either Y or N: ");
        } while (true); //forced loop in do while
    }

    public static void clearScanner(Scanner sc) {
        if(sc.hasNextLine()){
            sc.nextLine();
        }
    }

    public static void promtEnterKey(Scanner sc) {
        System.out.println("\nPress \"ENTER\" to continue.");
        sc.nextLine();
    }
}
