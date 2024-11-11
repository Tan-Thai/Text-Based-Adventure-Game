package GameObjects.Entities;

public class HostileCharacter extends Entity {

    private final HostileEntityType hostileEntityType;

    public HostileCharacter(String name, int health) {
        super(name, health, 1);
        this.hostileEntityType = HostileEntityType.UNDEFINED;
    }

    public HostileCharacter(String name, int health, HostileEntityType hostileEntityType) {
        super(name, health, 1);
        this.hostileEntityType = hostileEntityType;
    }

    // Expand logic
    public HostileEntityType getHostileEntityType() {
        return hostileEntityType;
    }

    //calculates the experience based on the level. numbers TBD
    public int calcExperienceGiven() {
        return (int) (Math.round((level * 20 * 1.25)));
    }
}
