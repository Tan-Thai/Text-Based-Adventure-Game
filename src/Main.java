import GameObjects.Entities.Entity;
import GameObjects.Entities.HostileCharacter;
import GameObjects.Entities.PlayerCharacter;
import GameObjects.Items.*;
import Global.*;
import Interactions.Combat;

import java.util.Scanner;
import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {

        // currently just slapping everything into main - will create a "game" package later to put all game logic into.
        Scanner sc = new Scanner(System.in);
        PlayerCharacter pc = setupUser(sc);
        System.out.println(MessageFormat.format("Your name is {0} and your current health is {1}",
                pc.getName(), pc.getHealth()));

        HostileCharacter goblin = new HostileCharacter("Goblin", 6);
        HostileCharacter angyBoi = new HostileCharacter("Big MAN", 30);

        // Item and goblin tests.
        itemAndDisplayTest(pc, goblin, sc);

        // new up A COMBAT object, and it will be the only one since its a singleton.
        Combat combat = Combat.getInstance();
        // this is what we call and send in when a combat between 2 entities happens.
        combat.initiateCombat(pc, goblin, sc);

        pc.getInventory().inspectInventory(sc, pc);

        combat.initiateCombat(pc, angyBoi, sc);
        sc.close();
    }

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = Utility.checkIfValidString(sc);
        System.out.print("What is your health?: ");
        int healthInput = Utility.checkIfNumber(sc);

        // temp input of stats since i got no idea what we are basing it on.
        return new PlayerCharacter(nameInput, healthInput, 9, 12, 15);
    }

    private static void itemAndDisplayTest(PlayerCharacter pc, Entity goblin, Scanner sc) {
        Equipment sword = new Equipment("A Simple Sword", "Your standard blade as a new adventurer.", new DamageEffect(2));
        Potion potion = new Potion("Health Potion", "Chug when ouch", new HealingEffect(5));
        Potion poison = new Potion("Totally a Health Potion", "Chug for ouch", new DamageEffect(7));

        pc.getInventory().addItem(sword);
        pc.getInventory().addItem(potion);
        pc.getInventory().addItem(poison);

        pc.getInventory().inspectInventory(sc, pc);

        pc.setHealth(pc.getHealth() - 10);
        Utility.clearConsole();
        pc.displayHealth();
        pc.displayStats();
        pc.gainExperience(110);
        Utility.promptEnterKey(sc);

        goblin.displayStats();
        goblin.displayHealth();
        Utility.promptEnterKey(sc);
    }

}