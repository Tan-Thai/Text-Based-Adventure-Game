import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Items.DamageEffect;
import GameObjects.Items.HealingEffect;
import GameObjects.Items.Item;
import GameObjects.Items.Potion;
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

        //Item sword = new Item("A Simple Sword", "Your standard blade as a new adventurer.");
        Potion potion = new Potion("Health Potion", "Chug when ouch", new HealingEffect(5));
        Potion poison = new Potion("Totally a Health Potion", "Chug for ouch", new DamageEffect(7));


        HostileCharacter goblin = new HostileCharacter("Goblin", 6);

        //pc.getInventory().addItem(sword);
        pc.getInventory().addItem(potion);
        pc.getInventory().addItem(poison);

        pc.getInventory().inspectInventory(sc, pc);

        //pc.getInventory().removeItem(sword);

        pc.setHealth(pc.getHealth() - 10);
        Utility.clearConsole();
        pc.displayHealth();
        pc.displayStats();
        pc.gainExperience(110);
        Utility.promptEnterKey(sc);

        goblin.displayStats();
        goblin.displayHealth();

    }

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = Utility.checkIfValidString(sc);
        System.out.print("What is your health?: ");
        int healthInput = Utility.checkIfNumber(sc);

        // temp input of stats since i got no idea what we are basing it on.
        return new PlayerCharacter(nameInput, healthInput, 9, 12, 15);
    }
}