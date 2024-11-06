import GameObjects.Data.EncounterRepository;
import GameObjects.Entities.*;
import GameObjects.Items.*;
import GameObjects.Worlds.Zone;
import Global.*;
import Interactions.Combat;
import Interactions.EncounterHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // currently just slapping everything into main - will create a "game" package
        // later to put all game logic into.
        Scanner sc = new Scanner(System.in);
        PlayerCharacter pc = setupUser(sc);
        System.out.printf("Your name is %s and your current health is %d",
                pc.getName(), pc.getHealth());
        Utility.promptEnterKey(sc);

        // HostileCharacter goblin = new HostileCharacter("Goblin", 6);

        // Item and goblin tests.
        // itemAndDisplayTest(pc, goblin, sc);

        // Combat test.
        // combatTest(pc, goblin, sc);

        // Encounter test
        encounterTest(pc, sc);

        // combatTest(pc, new HostileCharacter("Wimpy boyo", 4), sc);

        // MESSING AROUND WITH ZONES AND TRAVEL
        Game.gameMenu(pc, sc);
    }

    private static void encounterTest(PlayerCharacter pc, Scanner myScanner) {

        Zone testZone = new Zone("testZone", "This is a very testy testzone", false,
                null, EncounterRepository.getForestEncounters());

        EncounterHandler encounterHandler = EncounterHandler.getInstance();

        while (true) {
            System.out.println(testZone.getName());
            encounterHandler.initiateEncounter(pc, testZone.getUnclearedEncounter(), myScanner);

        }
    }

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = Utility.checkIfValidString(sc);
        System.out.print("What is your health?: ");
        int healthInput = Utility.checkIfNumber(sc);

        // temp input of stats since i got no idea what we are basing it on.
        return new PlayerCharacter(nameInput, healthInput, 3, 2, 4);
    }

    private static void itemAndDisplayTest(PlayerCharacter pc, Entity goblin, Scanner sc) {
        Equipment sword = new Equipment("A Simple Sword", "Your standard blade as a new adventurer.",
                new DamageEffect(2));
        Potion potion = new Potion("Health Potion", "Chug when ouch", new HealingEffect(5));
        Potion poison = new Potion("Totally a Health Potion", "Chug for ouch", new DamageEffect(7));

        pc.getInventory().addItem(sword);
        pc.getInventory().addItem(potion);
        pc.getInventory().addItem(poison);

        pc.getInventory().inspectInventory(sc, pc);

        Utility.clearConsole();
        pc.displayHealth();
        pc.displayStats();
        pc.gainExperience(110);
        Utility.promptEnterKey(sc);

        goblin.displayStats();
        goblin.displayHealth();
        Utility.promptEnterKey(sc);
    }

    private static void combatTest(PlayerCharacter pc, HostileCharacter goblin, Scanner sc) {
        // HostileCharacter angyBoi = new HostileCharacter("Big MAN", 30);

        // new up A COMBAT object, and it will be the only one since it's a singleton.
        Combat combat = Combat.getInstance();
        // this is what we call and send in when a combat between 2 entities happens.

        combat.initiateCombat(pc, goblin, sc);
        // combat.initiateCombat(pc, angyBoi, sc);
        // sc.close();
    }
}
