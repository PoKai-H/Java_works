package ngrams;

import edu.princeton.cs.algs4.In;
import java.util.Collection;
import java.util.TreeMap;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private TreeMap<String, TimeSeries> wordFrequency;
    private TimeSeries wordsByYear;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In words = new In(wordsFilename);
        In counts = new In(countsFilename);
        wordFrequency = new TreeMap<>();
        wordsByYear = new TimeSeries();
        while (words.hasNextLine()) {
            String[] line = words.readLine().split("\t");
            if (!wordFrequency.containsKey(line[0])) {
                TimeSeries t = new TimeSeries();
                t.put(parseInt(line[1]), parseDouble(line[2]));
                wordFrequency.put(line[0], t);
            } else {
                wordFrequency.get(line[0]).put(parseInt(line[1]), parseDouble(line[2]));
            }
        }
        while (counts.hasNextLine()) {
            String[] line = counts.readLine().split(",");
            wordsByYear.put(parseInt(line[0]), parseDouble(line[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (!wordFrequency.containsKey(word)) {
            return new TimeSeries();
        }
        return new TimeSeries(wordFrequency.get(word), startYear, endYear);

    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return countHistory(word, TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries copyTs = new TimeSeries();
        for (Integer integer: wordsByYear.keySet()) {
            copyTs.put(integer, wordsByYear.get(integer));
        }
        return copyTs;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!wordFrequency.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries tsCopy = new TimeSeries(wordFrequency.get(word), startYear, endYear);
        return tsCopy.dividedBy(wordsByYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return weightHistory(word, TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries copyTs = new TimeSeries();
        for (String word: words) {
            TimeSeries perWord = new TimeSeries(wordFrequency.get(word), startYear, endYear);
            copyTs = copyTs.plus(perWord);
        }
        return copyTs.dividedBy(wordsByYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words, TimeSeries.MIN_YEAR, TimeSeries.MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
