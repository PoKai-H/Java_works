import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import main.AutograderBuddy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestA {
    public static final String WORDS_FILE = "data/ngrams/frequency_tester.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SYNSET_FILE = "data/wordnet/synsets20.txt";
    public static final String HYPONYM_FILE = "data/wordnet/hyponyms20.txt";
    private static final NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
            WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);
    @Test
    public void LassTest() {
        List<String> words = List.of("AAAA");
        NgordnetQuery nq = new NgordnetQuery(words, 1450, 1500, 3);
        String actual = studentHandler.handle(nq);
        String expected = "[DDDD, EEEE, FFFF]";
        assertThat(actual).isEqualTo(expected);
    }
}
