package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;

import java.util.*;

public class HsponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;
    private NGramMap gramMap;
    public HsponymsHandler(WordNet wm, NGramMap gramMap) {
        super();
        this.gramMap = gramMap;
        this.wn = wm;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        Set<String> hypos = new HashSet<>();
        for (String word: words) {
            Set<String> hypo ;
            hypo = wn.hyponyms(word);
            for (String h: hypo) {
                hypos.add(h);
            }
        }
        List<String> hypsList = new ArrayList<>(hypos);
        return hypsList.toString();
//        if (k != 0) {
//            HashMap<String, Double> hypCounts = new HashMap<>();
//
//            for (String word : hypos) {
//                List<Double> history = gramMap.countHistory(word, startYear, endYear).data();
//                double count = history.stream().mapToDouble(Double::doubleValue).sum();
//
//                if (count == 0) {
//                    hypsList.remove(word);
//                } else {
//                    hypCounts.put(word, count);
//                }
//            }
//
//            if (q.k() < hypsList.size()) {
//                hypsList.sort(Comparator.comparingDouble(hypCounts::get).reversed());
//                hypsList = hypsList.subList(0, q.k());
//            }
//        }
//
//        Collections.sort(hypsList);
//        return "[" + String.join(", ", hypsList) + "]";
    }
}
