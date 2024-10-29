package Global;
public class Slowprint {

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
