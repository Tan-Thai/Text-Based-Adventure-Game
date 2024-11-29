package GameObjects.Data;

import Interactions.ChallengeType;
import Interactions.Encounter;
import Core.Config;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepository {

    private static List<Encounter> forestEncounters = new ArrayList<>();
    private static List<Encounter> swampEncounters = new ArrayList<>();
    private static List<Encounter> caveEncounters = new ArrayList<>();
    private static List<Encounter> basementEncounters = new ArrayList<>();

    public static List<Encounter> getForestEncounters() {

        forestEncounters = new ArrayList<>();

        forestEncounters.add(getLureWillOWispEncounter());
        forestEncounters.add(getHuntersForgottenTrapEncounter());
        forestEncounters.add(getGristlyMealEncounter());
        forestEncounters.add(getStuntedMudTrollEncounter());
        forestEncounters.add(getRottenCrawlerEncounter());
        forestEncounters.add(getDerangedTrapperEncounter());

        return forestEncounters;
    }

    public static List<Encounter> getSwampEncounters() {

        swampEncounters = new ArrayList<>();

        swampEncounters.add(getSinkingMireEncounter());
        swampEncounters.add(getSwampHagsTreasureEncounter());
        swampEncounters.add(getRiddleRottenWoodEncounter());
        swampEncounters.add(getMudCrocEncounter());
        swampEncounters.add(getGiantRavenousTadpoleEncounter());
        swampEncounters.add(getSlimyToadManEncounter());

        return swampEncounters;
    }

    public static List<Encounter> getCaveEncounters() {
        caveEncounters = new ArrayList<>();

        caveEncounters.add(getUnluckyAdventurerEncounter());
        caveEncounters.add(getASlipperySlopEncounter());
        caveEncounters.add(getBlockedByRubbleEncounter());
        caveEncounters.add(getMossDrenchedUndeadEncounter());
        caveEncounters.add(getDrownedCrawlerEncounter());
        caveEncounters.add(getGiantBloodsuckingBatEncounter());

        return caveEncounters;
    }

    public static List<Encounter> getBasementEncounters() {
        basementEncounters = new ArrayList<>();

        basementEncounters.add(getBossEncounter());

        return basementEncounters;
    }

    private static Encounter getBossEncounter() {

        return new Encounter(HostileEntitiesRepository.getTyrannicLizardKing());

    }

    private static Encounter getLureWillOWispEncounter() {
        return new Encounter(
                """
                        You walk deeper into the dense forest, ancient pines wrapped in gray moss towers over you.
                        Suddenly, you hear faint pleas for help accompanied by weak sobs of despair.
                        A child must have gotten lost in the forest and is in need of your aid!"
                        """,
                1,
                ChallengeType.INTELLIGENCE,
                """
                        As you are hurrying in the direction of the child's faint cries you get a feeling that something
                        doesn't seem right about this. You pause to listen and are able to detect a faint ethereal tone
                        interwoven with the child's voice.
                        You realize that you were about to be tricked by a will-o-wisp, a nefarious fay creature that
                        enjoys tricking unwary travelers into stepping off the beaten path.
                        Strengthened by your own good judgment you ignore the enchanting voice of the sprite and
                        continue on your journey.
                        """,
                """
                        You follow the faint cries for help deeper and deeper into the woods. The pleas seemingly
                        always come from just beyond your sight. You do not realize that you've been tricked until a
                        small translucent fairy appears before you with a cruel laugh.
                        “Oh what a merry chase we've had you and I!” the will-o-wisp gloats before disappearing in a
                        wreath of smoke.
                        It takes several exhausting hours for you to find your way back to familiar grounds.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                0,
                null);
    }

    private static Encounter getHuntersForgottenTrapEncounter() {
        return new Encounter(
                """
                        You are walking along a narrow hunting trail when you feel something first catch onto, and then
                        quickly give way, against your ankle.
                        """,
                1,
                ChallengeType.DEXTERITY,
                """
                        You have just enough time to pull your foot back before the snare trap pulls taught and whips
                        the rope up into the air. You can't say for certain, but you have a creeping suspicion that this
                        trap was set by someone hoping to collect human skins rather than rabbit pelts.
                        Now even more cautious, you continue on through the woods.
                        """,
                """
                        Before you have time to move a snare is pulled taut around your ankle and you are wrenched
                        off your feet as the trap is triggered.	You find yourself suspended upside down three feet off the
                        ground. Luckily you still have your knife, and you hurry to cut yourself down before any hunter
                        shows up to claim there prey.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                0,
                null);
    }

    private static Encounter getGristlyMealEncounter() {
        return new Encounter(
                """
                        Wandering through the forest you come upon a strange gathering. A group of wart-covered trolls
                        have gathered around a boiling pot of something that gives off noxious fumes. The creatures are
                        seemingly taking turns putting disgusting ingredients into the pot, daring each other to either
                        taste the foul concoction or forfeit.
                        Instead of attacking you, the evil creatures invite you to take part in their filthy game. You are
                        about to refuse when you notice that the trolls are betting quite a lot of gold and other plunder.
                        """,
                2,
                ChallengeType.INTELLIGENCE,
                """
                        You take your place in the ring as the game starts up again. Within a few rounds it is clear to
                        you that you cannot possibly last against creatures that habitually feast on carrion. Luckily, you
                        are able to take a few cautions sips from your health potion without anyone noticing.
                        The drink fortifies your constitution enough and you are able to win.
                        Triumphantly you collect your winnings and quickly leave before any of the trolls thinks to protest.
                        """,
                """
                        You take your place in the ring as the game starts up again. Within a few rounds it is clear to
                        you that you cannot possibly last against creatures that habitually feast on carrion. You stumble
                        away from the game with your innards in turmoil, your pride in tatters and your trousers in an
                        even dire straits.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                35,
                null);
    }

    private static Encounter getStuntedMudTrollEncounter() {
        return new Encounter(HostileEntitiesRepository.getStuntedMudTroll());
    }

    private static Encounter getRottenCrawlerEncounter() {
        return new Encounter(HostileEntitiesRepository.getRottenCrawler());
    }

    private static Encounter getDerangedTrapperEncounter() {
        return new Encounter(HostileEntitiesRepository.getDerangedTrapper());
    }

    private static Encounter getSinkingMireEncounter() {
        return new Encounter(
                """
                        As you stumble between fetid ponds and rotten trees the ground suddenly gives way beneath
                        your feet. You’ve accidentally stepped into a pit of thick, sucking mud that begins to pull you
                        under.
                        """,
                1,
                ChallengeType.STRENGTH,
                """
                        You struggle mightily and manage to heave yourself up and out of the sucking pit. You collapse
                        on the wet ground, exhausted and with burning muscles, but you are alive!
                        """,
                """
                        The viscous mud threatens to pull you under. You struggle for hours before reaching the edge
                        of the pit. By that time your limbs are stiff and near useless. Wet, cold and sore you limp away
                        utterly exhausted.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                0,
                null);
    }

    private static Encounter getSwampHagsTreasureEncounter() {
        return new Encounter(
                """
                        You are exploring the treacherous wetlands when you feel an ill stench on the wind. Hurrying to
                        take cover you see a hulking creature covered in warts and leeches come trudging through
                        the swamp.
                        This must be a swamp hag! The cruel witch has sold her soul to dark gods in return for dark
                        powers. She now serves her evil masters by conjuring pestilence and putting curses on
                        unwitting villagers.
                        You notice the sack she carries slung over one shoulder. It must contain whatever treasures she
                        has taken from the victims of her cruel magic!
                        You decide to follow her, hoping to find out what she means to do with the ill-gotten goods.
                        """,
                1,
                ChallengeType.DEXTERITY,
                """
                        You carefully follow the sea hag for hours without being seen. Finally she stops at a toppled
                        obelisk, and you see how the witch hides her loot underneath the ancient rock. When you are
                        sure that she has left you come out of cover and ransack her hiding place.
                        """,
                """
                        You are certain that the evil hag hasn't noticed you, until you accidentally disturb a nest of
                        ducks. The scared birds create a ruckus and you are forced to flee before the swamp hag is
                        able to find you.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                15,
                null);
    }

    private static Encounter getRiddleRottenWoodEncounter() {
        return new Encounter(
                """
                        As you try to escape from a swarm of bloodsucking mosquitos you stumble into a grove of
                        twisted, ancient trees. The trees all bear grotesque faces that have been gouged into the living
                        wood. As you take a moment to catch your breath, the gnarled faces begin to whisper to you.
                        'Abandoned before birth,
                        Sent my adopted siblings from my home,
                        Treated well by those who raised me,
                        I abandoned them still, before the first frost came.
                        What am I?'
                        """,
                1,
                ChallengeType.INTELLIGENCE,
                """
                        'A cuckoo bird' you declare, having pondered the riddle for a moment.
                        A sliver of light pierces through the branches and reflects off something stuck within the mouth
                        of one of the faces. Carefully you pull out an old tarnished silver coin.
                        You pocket it before leaving, taking it as your reward for solving the riddle.
                        """,
                """
                        'A scoundrel,' you loudly declare, having pondered the riddle awhile. Silence is the only
                        response. Cursing the spirits for refusing to recognize your genius you slink away rather than
                        coming up with another answer.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                5,
                null);
    }

    private static Encounter getMudCrocEncounter() {
        return new Encounter(HostileEntitiesRepository.getMudCroc());
    }

    private static Encounter getGiantRavenousTadpoleEncounter() {
        return new Encounter(HostileEntitiesRepository.getGiantRavenousTadpole());
    }

    private static Encounter getSlimyToadManEncounter() {
        return new Encounter(HostileEntitiesRepository.getSlimyToadMan());
    }

    private static Encounter getUnluckyAdventurerEncounter() {
        return new Encounter(
                """
                        				As you walk deeper into the cold wet tunnels you stumble over a sorry pile of bones and rotten
                        				pieces of cloth. Some sorry soul must have gotten lost in these lightless caverns and died
                        				before seeing the sky again.
                        """,
                2,
                ChallengeType.INTELLIGENCE,
                """
                        You take a moment to pray over the corpse of the unknown explorer, asking whatever god they
                        followed in life to see their soul free from this subterranean warren. As you leave, you feel as if
                        you carry a sense of calm and serenity away with you.
                        """,
                """
                        You instinctively shy away from the rotten corpse, your skin seems to crawl when you are
                        reminded of your own mortality. Muttering some half remembered prayer that you hope will keep
                        the spirit of the dead from noticing you, you quickly slink away.
                        
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                0,
                null);
    }

    private static Encounter getASlipperySlopEncounter() {
        return new Encounter(
                """
                        Deep into the caves you come across an underground stream. The current is fierce as the water
                        foams around the razor sharp rocks strewn along the streambed. You are able to make out a
                        narrow faultline in the cliff face that you think you might use to climb across without getting
                        drenched.
                        """,
                2,
                ChallengeType.DEXTERITY,
                """
                        Slowly and carefully you are able to make your way across the stream, finding purchase
                        between the moss-slick rocks, until you are once again standing on firm ground on the other
                        side.
                        """,
                """
                        Tenuously you make your way along the cave wall from grip to grip. However, you
                        underestimated how slick the moss and mold would make the stone. Halfway across you notice
                        how you're starting to lose your grip. Desperately you throw yourself forward, barely making it,
                        but slamming into the sharp rocks on the other side in the process.
                        Laboriously you climb to your feet again, you're bruised and bleeding, but at least you managed
                        to avoid being pulled under by the current.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                0,
                null);
    }

    private static Encounter getBlockedByRubbleEncounter() {
        return new Encounter(
                """
                        You come across a part of the tunnel that has collapsed completely blocking your progress.
                        """,
                2,
                ChallengeType.STRENGTH,
                """
                        After having moved several of the smaller stones out of the way you are able to get a good grip
                        on the larger boulder that fills most of the passage. Straining with all your might you are able to
                        shift the boulder and roll it away.
                        On the other side the dark tunnel beckons you deeper into the mountain. Bravely you step
                        forward on your journey.
                        """,
                """
                        After having spent an hour laboriously shifting the smaller rocks out of the way you are able to
                        start shifting the larger stones out of the way. You strain and heave with all your might, but you
                        are unable to get the rock to do more than shift slightly in its place.
                        Finally, exhausted, you must concede defeat.
                        You are forced to turn back, and spend many long hours backtracking before you are able to
                        find a new route that lets you circumvent the blockage.
                        """,
                Config.EXPERIENCE_PER_ENCOUNTER,
                0,
                null);
    }

    private static Encounter getMossDrenchedUndeadEncounter() {
        return new Encounter(HostileEntitiesRepository.getMossDrenchedUndead());
    }

    private static Encounter getDrownedCrawlerEncounter() {
        return new Encounter(HostileEntitiesRepository.getDrownedCrawler());
    }

    private static Encounter getGiantBloodsuckingBatEncounter() {
        return new Encounter(HostileEntitiesRepository.getGiantBloodsuckingBat());
    }

    private static Encounter getUnfinishedEncounter() {
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