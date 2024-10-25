import Entites.*;
import Resources.GlobalMethodLibrary;

import java.util.Scanner;
import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayerCharacter pc = setupUser(sc);
        System.out.println(MessageFormat.format("Your name is {0} and your current health is {1}",
                pc.getName(), pc.getHealth()));

        Item sword = new Item("A Simple Sword", "Your standard blade as a new adventurer.");
        Item potion = new Item("Health Potion", "Chug when ouch");

        pc.getInventory().addItem(sword);
        pc.getInventory().addItem(potion);

        pc.getInventory().displayInventory();

        pc.levelUp();
    }

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = GlobalMethodLibrary.checkIfValidString(sc);
        System.out.print("What is your health?: ");
        int healthInput = GlobalMethodLibrary.checkIfNumber(sc);

        return new PlayerCharacter(nameInput, healthInput);
    }
}