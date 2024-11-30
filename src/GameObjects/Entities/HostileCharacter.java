package GameObjects.Entities;

import GameObjects.Data.ItemId;
import GameObjects.Data.ItemRepository;
import Core.Config;

public class HostileCharacter extends Entity {

    private final HostileEntityType hostileEntityType;
    private final String hostileEntityDescription;

    public HostileCharacter(String name, int health) {
        super(name, health, 1);
        this.hostileEntityType = HostileEntityType.UNDEFINED;
        this.hostileEntityDescription = "";
    }

    public HostileCharacter(String name, int health, int level, int strength, int dexterity, int intelligence,
                            HostileEntityType hostileEntityType, String hostileEntityDescription) {

        super(name, health, level, strength, dexterity, intelligence, (level * 100 / 3));
        this.hostileEntityType = hostileEntityType;
        this.hostileEntityDescription = hostileEntityDescription;

        getInventory().addItem(ItemRepository.getItemById(ItemId.HEALTH_POTION));
        getInventory().addItem(ItemRepository.getItemById(ItemId.POISON_POTION));
    }

    public HostileEntityType getHostileEntityType() {
        return hostileEntityType;
    }

    public String getHostileEntityDescription() {
        return hostileEntityDescription;
    }

    /**
     * Returns experience based on entities level times experience per encounter
     *
     * @return
     */
    public int calcExperienceGiven() {
        return level * Config.EXPERIENCE_PER_ENCOUNTER;

    }
}
