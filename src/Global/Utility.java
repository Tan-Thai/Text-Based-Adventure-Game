package Global;

import java.util.OptionalInt;
import java.util.Random;
import java.util.Scanner;

public class Utility {

    // #region colour for prints
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    private static final int AMOUNT_OF_SIDES = 6;
    // #endregion
    private static final int CRIT_VALUE = 6;
    private static final int SUCCESS_VALUE = 4;
    // #region vars for standard dice values
    private static final Random random = new Random();
    // #endregion

    public static String checkIfValidString(Scanner sc) {

        if (!checkScanner(sc)) {
            return "";
        }

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

        if (!checkScanner(sc)) {
            return 0;
        }

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

    public static int checkIfNumberTest(Scanner sc, int maxInput) {

        if (!checkScanner(sc)) {
            return 0;
        }

        int userInput;

        while (true) { // forced loop in while
            if (sc.hasNextInt()) {
                userInput = sc.nextInt();
                if (userInput >= 0 && userInput <= maxInput) {
                    clearScanner(sc);
                    return userInput;
                }
            } else {
                sc.next();
            }
            System.err.print("Invalid input, please enter a number between 0 - " + maxInput + ": ");
        }
    }

    public static boolean checkYesOrNo(Scanner sc) {

        if (!checkScanner(sc)) {
            return false;
        }

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
        if (!checkScanner(sc)) {
            return;
        }

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

    /**
     * Function that rolls a given set of dice and returns the number of dice who's
     * results where equal or above the success value.
     * If not otherwise specified, success value, as well as crit value and how many
     * sides each die has is set to the default values of the variables in this
     * script.
     *
     * @param diceAmount
     * @param colour
     * @param optionalSuccessValue   Optional argument for what value the dies needs
     *                               to be equal or above to count as successess
     * @param optionalCritValue      Optional argument for what value the dies needs
     *                               to be equal or above to count as a critical
     *                               success
     * @param optionalDiceSideAmount Optional argument for how many sides you want
     *                               the dies to have.
     * @return
     */
    public static int rollDicePool(int diceAmount, String colour, OptionalInt optionalSuccessValue,
            OptionalInt optionalCritValue, OptionalInt optionalDiceSideAmount) {

        int successValue = optionalSuccessValue.orElse(SUCCESS_VALUE);
        int critValue = optionalCritValue.orElse(CRIT_VALUE);
        int diceSidesAmount = optionalDiceSideAmount.orElse(AMOUNT_OF_SIDES);

        int currentValue;
        int successAmount = 0;

        for (int i = 0; i < diceAmount; i++) {
            currentValue = random.nextInt(diceSidesAmount) + 1;
            System.out.print(colour + "[" + currentValue + "]" + RESET);

            if (currentValue >= successValue) {
                if (currentValue >= critValue) {
                    i--;
                }
                successAmount++;
            }
        }

        // To give space between the result and the following text.
        System.out.println();

        return successAmount;
    }

    /**
     * slowprint texts to highten player experience, if optional param isn't given
     * speed is set to 40
     * 
     * @param text
     * @param optDelay int parameter to change delay,
     */
    public static void slowPrint(String text, int... optDelay) {
        int standardDelay = 40;

        int delay = optDelay.length > 0 ? optDelay[0] : standardDelay;
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            System.out.print(currentChar);
            if (currentChar != ' ') {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.err.println("Interrupted: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Time delay was a negative number. " + e.getMessage());
                }
            }
        }
        System.out.println();
    }

    private static boolean checkScanner(Scanner sc) {

        try {
            if (sc == null || !sc.hasNext()) {
                sc = new Scanner(System.in);
            }
            return true;

        } catch (NullPointerException e) {
            System.err.println("Scanner is null" + e.getMessage());
            return false;
        } catch (IllegalStateException e) {
            System.err.println("Scanner was closed and couldn't be used to get input." + e.getMessage());
            return false;
        }
    }
}
