package GameObjects.Entities;

public enum HostileEntityType {
	DRACONIC, // halved damage from fire,
	HUMAN,
	TROLLKIN, // weak against fire & sunlight
	UNDEAD, // weak against fire, sunlight & holy
	UNDEFINED // Is set when nothing else is given in constructor.
}
