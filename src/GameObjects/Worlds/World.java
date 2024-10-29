package GameObjects.Worlds;

import java.util.ArrayList;
import GameObjects.Entites.PlayerCharacter;
import Global.*;

public class World {
    private ArrayList<Zone> zones;

    public World() {
        this.zones = new ArrayList<Zone>();
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }
    
}
