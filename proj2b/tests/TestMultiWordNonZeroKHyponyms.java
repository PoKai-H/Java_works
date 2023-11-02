import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import main.AutograderBuddy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;


public class TestMultiWordNonZeroKHyponyms {
    public static final String WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    private static final NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
            WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);



    /**
     * Tests finding all hyponyms of child, animal (k = 0) for startYear = 2007, endYear = 2007.
     * Note: The startYear and endYear should have no effect since k = 0.
     */
    @Test
    public void testChildAnimalK0in2007() {

        List<String> words = List.of("child", "animal");

        NgordnetQuery nq = new NgordnetQuery(words, 2007, 2007, 0);
        String actual = studentHandler.handle(nq);

        String expected = "[baby, kid, monkey, nestling, orphan, suckling, yearling]";
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Tests finding all hyponyms of child, animal (k = 1) for startYear = 2007, endYear = 2007.
     */
    @Test
    public void testChildAnimalK1in2007() {

        List<String> words = List.of("child", "animal");

        NgordnetQuery nq = new NgordnetQuery(words, 2007, 2007, 1);
        String actual = studentHandler.handle(nq);

        String expected = "[baby]";
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Tests finding all hyponyms of child, animal (k = 3) for startYear = 2007, endYear = 2007.
     */
    @Test
    public void testChildAnimalK3in2007() {

        List<String> words = List.of("child", "animal");

        NgordnetQuery nq = new NgordnetQuery(words, 2007, 2007, 3);
        String actual = studentHandler.handle(nq);

        String expected = "[baby, kid, monkey]";
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void testMapFunctionK10() {
        List<String> words = List.of("map", "function");

        NgordnetQuery nq = new NgordnetQuery(words, 1900, 2020, 10);
        String actual = studentHandler.handle(nq);
        String expected = "[expansion, function, identity, map, operator, sec, series, sin, transformation, translation]";
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void testEnergyLightSparkleK1() {
        List<String> words = List.of("energy", "light", "sparkle");

        NgordnetQuery nq = new NgordnetQuery(words, 1900, 2020, 1);
        String actual = studentHandler.handle(nq);
        String expected = "[light]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testEnergyLightBeamK3() {
        List<String> words = List.of("energy", "light", "beam");

        NgordnetQuery nq = new NgordnetQuery(words, 1900, 2020, 3);
        String actual = studentHandler.handle(nq);
        String expected = "[beam, ray, shaft]";
        assertThat(actual).isEqualTo(expected);
    }
}
