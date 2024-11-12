package GameObjects.Data;

import GameObjects.Items.DamageEffect;
import GameObjects.Items.Equipment;

public class ItemRepository {
    public static Equipment getRustyLongsword()
    {
        return new Equipment("Rusty Longsword", "An old and rusty longsword barely sharp enough to cut butter",new DamageEffect(1));
    }
    public static Equipment getMetioricIronLongsword()
    {
        return new Equipment("Metioric Iron longsword", "A longsword made of metioric iron, fallen from the stars, sharp as a razor",new DamageEffect(2));
    }
    public static Equipment getGreataxe()
    {
        return new Equipment("Greataxe", "A Greataxe, its simply a great axe",new DamageEffect(3));
    }

}
