package Interactions;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepository {
	// TODO Discuss moving this to global since it will be holding data
	// TODO Consider if we want one of these for enemies later then?

	// So, this one will hold lists of the encounters for each zone then.
	// But they will also be created here, so we will see their data here then?

	// TODO: Discuss renamning this List to forestEncounters, might be easier to
	// work with more direct name, even if it "marries" us to the concept of it
	// being Forest.
	private static final List<Encounter> firstZoneEncounters = new ArrayList<>();

	public static List<Encounter> getFirstZoneEncounters() {

		return firstZoneEncounters;
	}

	public static Encounter getLostChildLurEncounter() {
		return new Encounter(
				"""
						You walk deeper into the dense forest, ancient pines wrapped in gray moss towers over you.
						Suddenly, you hear faint pleas for help accompanied by weak sobs of despair.
						A child must have gotten lost in the forest and is in need of your aid!"
						""",
				1,
				ChallengeType.INTELLIGENCE,
				null,
				null,
				0,
				0,
				null);
	}

	public static Encounter getUnfinishedEncounter() {
		return new Encounter(
				null,
				0,
				null,
				null,
				null,
				0,
				0,
				null);
	}
}
