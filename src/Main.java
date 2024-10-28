import GameObjects.*;
import Global.*;

import java.util.Scanner;
import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {

        // currently just slapping everything into main - will create a "game" package later to put all game logic into.
        Scanner sc = new Scanner(System.in);
        PlayerCharacter pc = setupUser(sc);
        System.out.println(MessageFormat.format("Your name is {0} and your current health is {1}",
                pc.getName(), pc.getHealth()));

        Item sword = new Item("A Simple Sword", "Your standard blade as a new adventurer.");
        Item potion = new Item("Health Potion", "Chug when ouch");

        pc.getInventory().addItem(sword);
        pc.getInventory().addItem(potion);

        pc.getInventory().inspectInventory(sc);

        pc.getInventory().removeItem(sword);

        pc.setHealth(pc.getHealth() - 10);
        pc.displayHealth();
        pc.displayStats();
        pc.levelUp();
    }

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = GlobalMethodLibrary.checkIfValidString(sc);
        System.out.print("What is your health?: ");
        int healthInput = GlobalMethodLibrary.checkIfNumber(sc);

        // temp input of stats since i got no idea what we are basing it on.
        return new PlayerCharacter(nameInput, healthInput, 9, 12, 15);
    }
}