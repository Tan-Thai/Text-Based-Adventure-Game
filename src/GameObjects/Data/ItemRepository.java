package GameObjects.Data;

import GameObjects.Items.*;
import Interactions.Combat;

public class ItemRepository {
    private static ItemRepository instance;
    protected Potion healthPotion;
    protected Potion poisonPotion;

    private ItemRepository () {
        healthPotion = new Potion(
                "Health Potion",
                "Chug for yum",
                new HealingEffect(7),
                30);

        poisonPotion = new Potion(
                "Totally a Health Potion",
                "Chug for ouch",
                new DamageEffect(7),
                30);
    }

    public static synchronized ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepository();
        }
        return instance;
    }


    public Potion getHealthPotion() {
        return healthPotion;
    }

    public static Equipment getRustyLongsword() {
        return new Equipment("Rusty Longsword",
                "An old and rusty longsword barely sharp enough to cut butter",
                EquipmentType.WEAPON,
                new DamageEffect(1),
                30);
    }

    public static Equipment getMetioricIronLongsword() {
        return new Equipment(
                "Metioric Iron longsword",
                "A longsword made of metioric iron, fallen from the stars, sharp as a razor",
                EquipmentType.WEAPON,
                new DamageEffect(2),
                50);
    }

    public static Equipment getGreataxe() {
        return new Equipment(
                "Greataxe",
                "A Greataxe, its simply a great axe",
                EquipmentType.WEAPON,
                new DamageEffect(3),
                120);
    }


    public Potion getPoisonPotion() {
        return poisonPotion;
    }
}
