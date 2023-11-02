package main;

import java.util.*;

public class Graph<T> {
    // variables : what is our graph representation?
    // adjList(HashMap) or adjMatrix
    private HashMap<T, Set<T>> graph ;
    public Graph() {
        graph = new HashMap<>();
    }
    public void createNode(T vertex) {
        graph.put(vertex, new HashSet<>());
    }

    public void addEdge(T source, T destination) {
        if (!graph.containsKey(source)) {
            createNode(source);
        }
        if (!graph.containsKey(destination)) {
            createNode(destination);
        }
        graph.get(source).add(destination);
    }
    public Set<T> get(T source) {
        return graph.get(source);
    }

    public String toString() {
        return "";
    }
}
