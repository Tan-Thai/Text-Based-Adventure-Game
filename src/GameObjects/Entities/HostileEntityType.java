package GameObjects.Entities;

public enum HostileEntityType {
	DRACONIC, //halved damage from fire,
	HUMAN,
	TOADKIN, // halved damage from fire,
	TROLLKIN, // weak against fire & sunlight
	UNDEAD, // weak against fire, sunlight & holy
	UNDEFINED // Is set when nothing else is given in constructor.
}
