package GameObjects.Data;

import GameObjects.Worlds.Basement;
import GameObjects.Worlds.Tavern;
import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneType;
import Resources.Config;

public class ZoneRepository { // This class is a repository for all the zones in the game.


    public static Zone forestZone() {
        return new Zone(
                "Wraithwood depths",
                """
                        A dark tranquil old forest where every step taken sinks into the deep soft moss muffling all sound,
                        walking here feels timeless and keeping a direction is impossible, becoming lost among the giant timbers
                        is only a matter of time. In this sea of green flashes of yellowing white can be seen poking up from
                        beneath the autumn foliage.
                        """,
                false,
                ZoneType.FOREST,
                null,
                Config.ZONE_CLEARTHRESHOLD);
    }

    public static Zone swampZone() {
        return new Zone(
                "Mistmoor Marsh",
                """
                        A foul-smelling yellow brown morass with slow moving stagnant water. Ever shrouded in smothering fog
                        that steals away all sound giving the marsh an eerie otherworldly feel. Scattered throughout are huge
                        floating tussocks, small islands of wild grass and small shrubs. Beneatha the murky surface large shadows
                        moves creating soft ripples and swirls.
                        """,
                false,
                ZoneType.SWAMP,
                null,
                Config.ZONE_CLEARTHRESHOLD);
    }

    public static Zone caveZone() {
        return new Zone(
                "Weeping caverns",
                """
                        A labyrinthine series of deep granite caves, 
                        named for the roof that weeps eternal and the wind which carries 
                        the anguished and mournful howls of the lost soul that are said to dwell in the depths. 
                        """,
                false,
                ZoneType.CAVE,
                null,
                Config.ZONE_CLEARTHRESHOLD);
    }

    public static Zone tavernZone() { // Is tavern and basement needed? How to with special zones?
        return new Tavern();
    }

    public static Zone basementZone() {
        return new Basement();
    }

}
