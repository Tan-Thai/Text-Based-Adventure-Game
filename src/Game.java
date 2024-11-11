import java.util.Scanner;
import GameObjects.Data.Info;
import GameObjects.Entities.*;
import GameObjects.Items.*;
import GameObjects.Worlds.ZoneManager;
import GameObjects.Worlds.ZoneType;
import Global.*;
import Interactions.*;

public class Game {

    Scanner sc;
    //Boolean here to exit the game when the user chooses to, OR dies in game.
    private boolean exitGame = false;

    // stuff we can start generating as soon as the program starts running.
    public Game(Scanner sc) {
        // All instances and such can be made here such as zones etc.
        this.sc = sc;
        ZoneManager.getInstance();
    }

    // the core game loop
    public void run () {
        Utility.clearConsole();
        // adding player char and basic items(temp items for now.)
        PlayerCharacter pc = setupUser(sc);
        addItems(pc);
        // --- TESTS -----------------------
        // Combat Test
        // combatTest(pc, addEnemyTemp(), sc);

        // Encounter test
        // encounterTest(pc, sc);
        //----------------------------------
        while (!exitGame) {
            gameMenu(pc, sc);
        }
    }

    // There are a few mega temporary methods atm, combat related ones being a few.
    // Wherever we invoke the combat method, we need to make sure it calls "Combat combat = Combat.getInstance();"
    private void gameMenu(PlayerCharacter pc, Scanner sc) {

            Utility.clearConsole();
            System.out.println("Welcome to the game!\n1. Start Game\n2. How to Play\n3. Exit");

            int choice = Utility.checkIfNumber(sc);

            switch (choice) {

            case 1:
                System.out.println("Starting game...");
                travelTest(pc, sc);
                // startGame(); call main game loop
                break;
            case 2:
                Info.howToPlay(sc);
                break;
            case 3:
                System.out.println("Exiting game...");
                exitGame = true;
                break;
            }


    }

    private static PlayerCharacter setupUser(Scanner sc) {
        System.out.print("What is your name?: ");
        String nameInput = Utility.checkIfValidString(sc);
        // temp input of stats since i got no idea what we are basing it on.

        System.out.println("Your name is " + nameInput + "!");
        Utility.promptEnterKey(sc);
        return new PlayerCharacter(nameInput, 18, 3, 2, 4);
    }
    private static void addItems(PlayerCharacter pc) {
        Equipment sword = new Equipment("A Simple Sword", "Your standard blade as a new adventurer.",
                new DamageEffect(2));
        Potion potion = new Potion("Health Potion", "Chug when ouch", new HealingEffect(5));
        Potion poison = new Potion("Totally a Health Potion", "Chug for ouch", new DamageEffect(7));

        pc.getInventory().addItem(sword);
        pc.getInventory().addItem(potion);
        pc.getInventory().addItem(poison);
    }

    // methods down here are most likely tests methods.
    private static void travelTest(PlayerCharacter pc, Scanner sc) {
        while (true) {
            pc.getCurrentZone().travelMenu(pc);

            Utility.promptEnterKey(sc);
        }
    }
    private static HostileCharacter addEnemyTemp() {return new HostileCharacter("Goblin", 6);}
    private static void combatTest(PlayerCharacter pc, HostileCharacter enemy, Scanner sc) {
        // new up A COMBAT object, and it will be the only one since it's a singleton.
        Combat combat = Combat.getInstance();
        // this is what we call and send in when a combat between 2 entities happens.
        combat.initiateCombat(pc, enemy, sc);
    }

    private static void encounterTest(PlayerCharacter pc, Scanner myScanner) {

        // This one is my bad, currently the ZoneManager needs to get instantiated for
        // the rest to work.
        // I will fix this later I promise! Just let it be for now!
        ZoneManager.getInstance();

        EncounterHandler encounterHandler = EncounterHandler.getInstance();

        while (ZoneManager.getZone(ZoneType.FOREST).hasUnclearedEncounters()) {
            System.out.println(ZoneManager.getZone(ZoneType.FOREST).getName());
            encounterHandler.runEncounter(pc, ZoneManager.getZone(ZoneType.FOREST).getUnclearedEncounter(), myScanner);
        }
    }
}
