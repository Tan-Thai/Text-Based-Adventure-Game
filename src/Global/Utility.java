package Global;

import java.util.OptionalInt;
import java.util.Random;
import java.util.Scanner;

public class Utility {

    // region colour for prints
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String LOW_INTENSITY = "\u001B[2m";
    public static final String HIGH_INTENSITY = "\u001B[1m";
    // endregion

    // region vars for standard dice values
    private static final int AMOUNT_OF_SIDES = 6;
    private static final int CRIT_VALUE = 6;
    private static final int SUCCESS_VALUE = 4;
    private static final Random random = new Random();
    // endregion

    //region Input-Related Utility
    public static String checkIfValidString(Scanner sc) {
        String userInput;
        // Mocking up a temporary maxLength for now.
        // Should be replaced with a maxLength parameter for modularity.
        int maxLength = 13;
        do {
            userInput = sc.nextLine();

            if (!userInput.isEmpty() && userInput.length() <= maxLength) {
                return userInput;
            }

            if (userInput.isEmpty())
                System.err.print("Please enter a name: ");
            else if (userInput.length() <= maxLength)
                System.err.print(
                        "The name you entered is too long. Please enter a name with a maximum of " + maxLength +
                        " characters: ");
        } while (true);
    }

    public static int checkIfNumber(Scanner sc) {

        int userInput;
        while (true) {
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

        int userInput;

        // forced loop in while
        while (true) {
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
            // forced loop in do while
        } while (true);
    }

    public static void clearScanner(Scanner sc) {
        if (sc != null && sc.hasNextLine()) {
            sc.nextLine();
        }
    }

    public static void promptEnterKey(Scanner sc) {
        System.out.print("\nPress \"ENTER\" to continue.");

        clearScanner(sc);
        // sc.nextLine();
    }
    //endregion

    //region Dice-Related Utility

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

            if (currentValue >= 4 && currentValue < 6)
                System.out.print(colour + "[" + currentValue + "]" + RESET);
            else if (currentValue == 6)
                System.out.print(HIGH_INTENSITY + colour + "[" + currentValue + "]" + RESET);
            else
                System.out.print(LOW_INTENSITY + "[" + currentValue + "]" + RESET);


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
    //endregion

    //region Display-Related Utility

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

    // New method to just streamline break in text, not yet fully implemented into the game.
    public static void printBigLine() {
        System.out.println("-----------------------------------------------------------");
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
    //endregion
}