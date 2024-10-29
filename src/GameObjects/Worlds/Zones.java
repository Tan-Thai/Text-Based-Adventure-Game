package GameObjects.Worlds;

public class Zones {

    public enum Area {
        FOREST("Forest", "A dense forest with tall trees.", false),
        SWAMP("Swamp", "A wetland area with muddy ground.", false),
        CAVE("Cave", "A dark and damp cave.", false);

        private final String name;
        private final String description;
        private boolean zoneCleared;

        Area(String name, String description, boolean b) {
            this.name = name;
            this.description = description;
            this.zoneCleared = b;
        }


        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public void setZoneCleared(boolean zoneCleared) {
            this.zoneCleared = zoneCleared;
        }

        public boolean getZoneCleared() {
            return zoneCleared;
        }
    }
   
}
