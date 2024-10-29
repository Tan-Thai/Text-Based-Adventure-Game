package GameObjects.Worlds;

import java.util.ArrayList;
import GameObjects.Entites.PlayerCharacter;
import Global.*;

public class NyWorld {
    private ArrayList<Zone> zones;

    public NyWorld() {
        this.zones = new ArrayList<Zone>();
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }
    
}
