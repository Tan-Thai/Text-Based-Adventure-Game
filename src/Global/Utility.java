package Global;

import java.util.NoSuchElementException;
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
            try {
                userInput = sc.nextLine();
            } catch (NoSuchElementException e) {
                /*
                 * If the scanner is closed, or the line exhausted, I'm assuming that we'd
                 * rather send a null string back, rather than do another attempt by letting the
                 * loop continue.
                 */
                System.err.println("Scanner couldn't find a next line, and so the method returns an empty string. "
                        + e.getMessage());
                return "";
            }
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
        try {
            if (sc != null && sc.hasNextLine()) {
                sc.nextLine();
            }
        } catch (NullPointerException e) {
            System.err.println("Scanner was null, probably it wasn't initiated.");
        } catch (IllegalStateException e) {
            System.err.println("The scanner was closed, and so couldn't return a next line.");
        }
    }

    public static void promptEnterKey(Scanner sc) {
        System.out.print("\nPress \"ENTER\" to continue.");

        clearScanner(sc);
        // sc.nextLine();
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

        if (successValue > diceSidesAmount) {
            System.err.println(
                    "The successvalue is higher than the amount of sides on the die, so the roll will never return a success.");
        }

        if (critValue > diceSidesAmount) {
            System.err.println(
                    "The critical success value is higher than the amount of sides on the die, so a crit will never happen.");
        }

        if (diceSidesAmount <= 0) {
            System.err.println(
                    "The number of sides on the die has been set to zero or less. The dice pool will not be rolled and zero returned.");
            return 0;
        }

        int currentValue;
        int successAmount = 0;

        for (int i = 0; i < diceAmount; i++) {
            try {
                currentValue = random.nextInt(diceSidesAmount) + 1;
            } catch (IllegalArgumentException e) {
                System.err.println(
                        "The random function returned a negative value. meaning that the die was asigned a negative amount of sides. The result of the die will be zero"
                                + e.getMessage());
                currentValue = 0;
            }
            System.out.print(colour + "[" + currentValue + "]" + RESET);

            if (currentValue >= successValue) {
                if (currentValue >= critValue) {
                    i--;
                }
                successAmount++;
            }
        }

        // Give space between the result and the following text.
        System.out.println();

        return successAmount;
    }

    /**
     * slowprint texts to highten player experience, if optional param isn't given
     * the delay is set to 40
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
                    System.err.println("The variable for time delay was a negative number. " + e.getMessage());
                }
            }
        }
        System.out.println();
    }

    /**
     * Protection function that uses try-catch to intercept IllegalStateException
     * and NullPointerException. Returns false and prints message to
     * System.err.println in those cases.
     * 
     * @param sc
     * @return
     */
    private static boolean checkScanner(Scanner sc) {

        try {
            if (sc == null) {
                return false;
            }
        } catch (NullPointerException e) {
            System.err.println("Scanner is null" + e.getMessage());
            return false;
        }

        try {
            if (sc.hasNext()) {
                return true;
            }
            return false;
        } catch (IllegalStateException e) {
            System.err.println("Scanner was closed and couldn't be used to get input." + e.getMessage());
            return false;
        }
    }
}