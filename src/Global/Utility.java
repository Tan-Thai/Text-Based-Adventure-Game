package Global;

public class Utility {
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
            //fallback: print 10 new lines
            for (int i = 0; i < 10; i++) {
                System.out.println();
            }
        }
    }
}