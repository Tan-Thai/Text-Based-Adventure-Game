package GameObjects.Entities;

public class HostileCharacter extends Entity {

    private final HostileEntityType hostileEntityType;
    private final String hostileEntityDescription;

    

    public HostileCharacter(String name, int health) {
        super(name, health, 1);
        this.hostileEntityType = HostileEntityType.UNDEFINED;
        this.hostileEntityDescription="";
    }

    public HostileCharacter(String name, int health, int level, int strength, int dexterity, int intelligence,
                            HostileEntityType hostileEntityType,String hostileEntityDescription) {
        // temporarily added a 0 for currency due to compile errors.
        super(name, health, level, strength, dexterity, intelligence, (level * 100 / 3));
        this.hostileEntityType = hostileEntityType;
        this.hostileEntityDescription=hostileEntityDescription;
    }

    // TODO: Expand logic for enemy types and experience.
    public HostileEntityType getHostileEntityType() {
        return hostileEntityType;
    }
    public String getHostileEntityDescription() {
        return hostileEntityDescription;
    }
    // calculates the experience based on the level. numbers TBD
    public int calcExperienceGiven() {
        return 100;
        // return (int) (Math.round((level * 20 * 1.25)));
    }
}
