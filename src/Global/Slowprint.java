package Global;
public class Slowprint {

    private int delay = 40; // Default delay

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void slowPrint(String text) {
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

    public void slowPrint(int number) {
        slowPrint(Integer.toString(number)); // Convert int to String
    }

    public void slowPrint(Object obj) {
        // Handle any object by converting it to a String
        slowPrint(obj.toString());
    }
}
