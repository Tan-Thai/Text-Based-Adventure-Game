package GameObjects.Entities;

import GameObjects.Items.Inventory;

public class HostileCharacter extends Entity {

    private final HostileEntityType hostileEntityType;

    public HostileCharacter(String name, int health) {
        super(name, health, 1);
        this.hostileEntityType = HostileEntityType.UNDEFINED;
    }

    public HostileCharacter(String name, int health, int level, int strength, int dexterity, int intelligence,
            HostileEntityType hostileEntityType) {
        super(name, health, level, strength, dexterity, intelligence);
        this.hostileEntityType = hostileEntityType;
    }

    // Expand logic
    public HostileEntityType getHostileEntityType() {
        return hostileEntityType;
    }

    // calculates the experience based on the level. numbers TBD
    public int calcExperienceGiven() {
        return (int) (Math.round((level * 20 * 1.25)));
    }
}
