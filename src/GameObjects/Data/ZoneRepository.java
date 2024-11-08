package GameObjects.Data;

import java.util.HashMap;

import GameObjects.Worlds.Zone;
import GameObjects.Worlds.ZoneType;

public class ZoneRepository { // This class is a repository for all the zones in the game.


    private static Zone forestZone() {
        return new Zone("Wraithwood depths",
                """
                        A dark tranquil old forest where every step taken sinks into the deep soft moss muffling all sound,
                        walking here feels timeless and keeping a direction is impossible, becoming lost among the giant timbers
                        is only a matter of time. In this sea of green flashes of yellowing white can be seen poking up from
                        beneath the autumn foliage.
                        """,
                false,
                ZoneType.FOREST,
                null);
    }

    private static Zone swampZone() {
        return new Zone("Mistmoor Marsh",
                """
                        A foul-smelling yellow brown morass with slow moving stagnant water. Ever shrouded in smothering fog
                        that steals away all sound giving the marsh an eerie otherworldly feel. Scattered throughout are huge
                        floating tussocks, small islands of wild grass and small shrubs. Beneatha the murky surface large shadows
                        moves creating soft ripples and swirls.
                        """,
                false,
                ZoneType.SWAMP,
                null);
    }

    private static Zone caveZone() {
        return new Zone("Cave",
                """
                        A dark and damp cave with a musty smell.
                        """,
                false,
                ZoneType.CAVE,
                null);
    }

    private static Zone tavernZone() {
        return new Zone("Tavern",
                """
                        A lively tavern filled with patrons and the smell of ale.
                        """,
                true,
                ZoneType.TAVERN,
                null);
    }

    private static Zone basementZone() {
        return new Zone("Basement",
                """
                        A dark and damp basement with a musty smell.
                        """,
                false,
                ZoneType.BASEMENT,
                null);
    }

}
