package com.natera.interview.graph.algorithm.traverse;

import com.natera.interview.graph.model.Graph;

import java.util.function.Consumer;

public interface Traverse<T> {
    /**
     * Will take a user defined function and apply it on every vertex of the graph.
     * @param graph for  traverse
     * @param consumer will be called for each vertex
     */
    void traverse(Graph<T> graph, Consumer<T> consumer);
}
