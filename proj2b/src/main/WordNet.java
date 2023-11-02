package main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    private Graph graph;
    private HashMap<Integer, Set<String>> h;
    // wrapper of a graph
    // provide helper function to use graph
    public WordNet(String sysnetFileName, String hyponymFileName) {
        graph = new Graph<>();
        h = new HashMap<Integer, Set<String>>();
        String s;
        String[] tokens;
        Integer id;
        String words[];
        In syn = new In(sysnetFileName);
        while (syn.hasNextLine()) {
            Set<String> synset = new HashSet<String>();
            tokens = syn.readLine().split(",");
            id = Integer.parseInt(tokens[0]);
            s = tokens[1];
            words = s.split(" ");
            for (String word: words) {
                synset.add(word);
            }
            h.put(id, synset);
            graph.createNode(id);
        }

        In hyp = new In(hyponymFileName);
        while (hyp.hasNextLine()) {
            s = hyp.readLine();
            tokens = s.split(",");
            for (int i = 0; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }

        // build the graph -> add all the edges

    }
    public Set<String> hyponyms(String word) {
        Set<String> returnValue = new HashSet<>();
        Set<Integer> synsetsNumbers = new HashSet<>();
        for (Integer id: h.keySet()) {
            Set<String> temp0 = h.get(id);
            if (temp0.contains(word)) {
                synsetsNumbers.add(id);
            }
        }
        for (Integer id: synsetsNumbers) {
            Set<Integer> hypo = graph.get(id);
            for (Integer hy: hypo) {
                Set<Integer> hyp = getHyponym(hy);
                for (Integer i : hyp) {
                    Set<String> words = h.get(i);
                    for (String w: words) {
                        returnValue.add(w);
                    }
                }

            }
        }
        return returnValue;
    }

    public Set<Integer> getHyponym(Integer id) {
        Set<Integer> hyponyms = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(id);
        while(!queue.isEmpty()) {
            Integer top = queue.remove();
            visited.add(top);
            Set<Integer> hyp = graph.get(top);
            for (Integer i: hyp) {
                if (!visited.contains(i)) {
                    hyponyms.add(i);
                    queue.add(i);
                }
            }
        }
        return hyponyms;
    }


    // graph helper functions
    public String doSomething() {
        return graph.toString();
    }
}
