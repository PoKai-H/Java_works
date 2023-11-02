package main;

import browser.NgordnetServer;
import ngrams.NGramMap;

public class Main {
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        /* The following code might be useful to you.
*/
        String wordFile = "./data/ngrams/top_14377_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";

        String synsetFile = "./data/wordnet/synsets16.txt";
        String hyponymFile = "./data/wordnet/hyponyms16.txt";
        //NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNetNew wm = new WordNetNew(synsetFile, hyponymFile);
        NGramMap nm = new NGramMap(wordFile, countFile);


        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsHandler(wm, nm));

        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}
