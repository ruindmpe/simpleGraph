package com.natera.interview.graph.visit.impl;

import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.visit.Traverse;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Collections.emptySet;

public class BfTraversal<T> implements Traverse<T> {
    @Override
    public void traverse(Graph<T> graph, Consumer<T> consumer) {
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        Map<T, Set<T>> adjacentVertices = graph.getAdjacentVertices();
        Optional<T> root = getUnvisitedVertex(graph, visited);
        root.ifPresent(value -> queue.add(value));
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            if (!visited.contains(vertex)) {
                visit(vertex, consumer, queue, visited);
           }
            for (T v : adjacentVertices.getOrDefault(vertex, emptySet())) {
                if (!visited.contains(v)) {
                    visit(v, consumer, queue, visited);
                }
            }
            if (queue.isEmpty()) {
                Optional<T> unvisitedVertex = getUnvisitedVertex(graph, visited);
                unvisitedVertex.ifPresent(value -> queue.add(value));
            }
        }
    }

    private void visit(T vertex, Consumer<T> consumer, Queue<T> queue, Set<T> visited) {
        visited.add(vertex);
        queue.add(vertex);
        consumer.accept(vertex);
    }

    private Optional<T> getUnvisitedVertex(Graph<T> graph, Set<T> visited) {
        return graph.getVertices().stream()
            .filter(vertex -> !visited.contains(vertex))
            .findFirst();
    }
}
