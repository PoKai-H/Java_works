package main;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    Graph ngrams;
    NGramMap gramMap;

    public HyponymsHandler(Graph ngrams, NGramMap gramMap) {
        super();
        this.ngrams = ngrams;
        this.gramMap = gramMap;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        if (words.isEmpty()) {
            return "[]";
        }

        TreeSet<String> hyps = ngrams.hyponyms(words.get(0));
        if (hyps.isEmpty()) {
            return "[]";
        }

        for (String word : words) {
            hyps.retainAll(ngrams.hyponyms(word));
        }

        if (k == 0) {
            return hyps.toString();
        } else {
            Map<String, Double> hyponymsMap = new HashMap<>();
            PriorityQueue<Double> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

            for (String hyponym : hyps) {
                TimeSeries hyponymTs = gramMap.countHistory(hyponym, startYear, endYear);
                Collection<Double> aLs =  hyponymTs.values();
                Double sum = (double) 0;
                for (Double al : aLs) {
                    sum = sum + al;
                }
                hyponymsMap.put(hyponym, sum);
                maxPQ.add(sum);
            }
            TreeSet<String> mostPopular = new TreeSet<>();
            if (k <= hyps.size()) {
                for (int i = 0; i < k; i = i + 1) {
                    Double popularKey = maxPQ.poll();
                    if (popularKey != 0.0) {
                        for(String hy: hyponymsMap.keySet()) {
                            if (hyponymsMap.get(hy) == popularKey) {
                                mostPopular.add(hy);
                            }
                        }
                    } else {
                        break;
                    }

                }
                if (mostPopular.isEmpty()) {
                    return Collections.emptyList().toString();
                }
                return mostPopular.toString();
            } else {
                for (int i = 0; i < hyps.size(); i = i + 1) {
                    Double popularKey = maxPQ.poll();
                    if (popularKey != 0.0) {
                        for(String hy: hyponymsMap.keySet()) {
                            if (hyponymsMap.get(hy) == popularKey) {
                                mostPopular.add(hy);
                            }
                        }
                    } else {
                        break;
                    }
                }
                if (mostPopular.isEmpty()) {
                    return Collections.emptyList().toString();
                }
                return mostPopular.toString();
            }
        }
    }
}