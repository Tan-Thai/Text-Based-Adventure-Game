package GameObjects.Data;

import GameObjects.Entities.PlayerCharacter;


public class PlayerClassRepository {


    public static PlayerCharacter getBarbarian(String name)
    {
        return new PlayerCharacter(name, 20, 4, 3, 2);
    }
    public static PlayerCharacter getRogue(String name)
    {
        return new PlayerCharacter(name, 15, 2, 5, 3);
    }
    public static PlayerCharacter getWizard(String name)
    {
        return new PlayerCharacter(name, 15, 3, 3, 5);
    }

}