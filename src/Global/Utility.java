package Global;

import java.util.OptionalInt;
import java.util.Random;
import java.util.Scanner;

public class Utility {

    // #region vars for standard dice values
    private static Random random = new Random();

    private static final int AMOUNT_OF_SIDES = 6;
    private static final int CRIT_VALUE = 6;
    private static final int SUCCESS_VALUE = 4;
    // #endregion

    // #region colour for prints
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    // #endregion

    public static String checkIfValidString(Scanner sc) {
        String userInput;
        do {
            userInput = sc.nextLine();
            if (!userInput.isEmpty()) {
                return userInput;
            }
            System.err.print("Please enter a name: ");
        } while (true); // forced loop
    }

    public static int checkIfNumber(Scanner sc) {
        int userInput;

        while (true) { // forced loop in while
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
        } while (true); // forced loop in do while
    }

    public static void clearScanner(Scanner sc) {
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
    }

    public static void promptEnterKey(Scanner sc) {
        System.out.print("\nPress \"ENTER\" to continue.");
        sc.nextLine();
    }

    public static void clearConsole() {
        // ANSI escape code to clear the console // TOTALLY YOINKED THIS

        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Could not clear console: " + e.getMessage());
            // fallback: print 10 new lines
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }
        }
    }

    // TODO: Write explaining summary of this function
    public static int rollDicePool(int diceAmount, String colour, OptionalInt optionalSuccessValue,
            OptionalInt optionalCritValue, OptionalInt optionalDiceSideAmount) {

        int successValue = optionalSuccessValue.orElse(SUCCESS_VALUE);
        int critValue = optionalCritValue.orElse(CRIT_VALUE);
        int diceSidesAmount = optionalDiceSideAmount.orElse(AMOUNT_OF_SIDES);

        int currentValue;
        int successAmount = 0;

        for (int i = 0; i < diceAmount; i++) {
            currentValue = random.nextInt(diceSidesAmount) + 1;
            // TODO Check if it is possible to get the reset value here, at start of print
            // out, to avoid needing to save it, and then just save it after again?
            System.out.print(colour + "[" + currentValue + "]" + RESET);

            if (currentValue >= successValue) {
                if (currentValue == critValue) {
                    // TODO Check with WV if all die pools are "exploding" or if this should be
                    // controlled by a bool.
                    i--;
                }
                successAmount++;
            }
        }

        return successAmount;
    }

    public class Slowprint { // TODO make method instead of class.

        private static int delay = 40; // Default delay

        public void setDelay(int delay) {
            this.delay = delay;
        }

        public static void sp(String text) {
            for (int i = 0; i < text.length(); i++) {
                char currentChar = text.charAt(i);
                System.out.print(currentChar);
                if (currentChar != ' ') {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        System.err.println("Interrupted: " + e.getMessage());
                    }
                }
            }
            System.out.println();
        }

        public void sp(int number) {
            sp(Integer.toString(number)); // Convert int to String
        }

        public void sp(Object obj) {
            // Handle any object by converting it to a String
            sp(obj.toString());
        }
    }
}
