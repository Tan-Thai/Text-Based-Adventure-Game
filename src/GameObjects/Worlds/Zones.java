package GameObjects.Worlds;

public class Zones {
    static Tavern tavern = new Tavern();
    static Basement basement = new Basement();

    public enum Area {
        TAVERN(tavern.getName(), tavern.getDescription(), true), //WTF IS THIS
        FOREST("Forest", "A dense forest with tall trees.", false),
        SWAMP("Swamp", "A wetland area with muddy ground.", false),
        CAVE("Cave", "A dark and damp cave.", false),
        BASEMENT(basement.getName(), basement.getDescription(), false); // AND THIS


        private final String name;
        private final String description;
        private boolean zoneCleared;

        Area(String name, String description, boolean zoneCleared) {
            this.name = name;
            this.description = description;
            this.zoneCleared = zoneCleared;
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
