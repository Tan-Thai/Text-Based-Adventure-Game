package GameObjects.Entities;

public class HostileCharacter extends Entity {

    private final HostileEntityType hostileEntityType;

    public HostileCharacter(String name, int health) {
        super(name, health, 1);
        this.hostileEntityType = HostileEntityType.UNDEFINED;
    }

    public HostileCharacter(String name, int health, int level, int strength, int dexterity, int intelligence,
                            HostileEntityType hostileEntityType) {
        // temporarily added a 0 for currency due to compile errors.
        super(name, health, level, strength, dexterity, intelligence, 0);
        this.hostileEntityType = hostileEntityType;
    }

    // TODO: Expand logic for enemy types and experience.
    public HostileEntityType getHostileEntityType() {
        return hostileEntityType;
    }

    // calculates the experience based on the level. numbers TBD
    public int calcExperienceGiven() {
        return 100;
        // return (int) (Math.round((level * 20 * 1.25)));
    }
}
