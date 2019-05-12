package com.natera.interview.graph.model.impl;

import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.pathfinding.PathFinder;
import com.natera.interview.graph.visit.Traverse;

import java.util.*;
import java.util.function.Consumer;

public class SimpleGraph<T> implements Graph<T> {

    private final Set<T> vertices = new HashSet<>();
    private final Map<T, Set<T>> adjacentVertices = new HashMap<>();
    private final Boolean isDirected;
    private final PathFinder<T> pathFinder;
    private final Traverse<T> traversal;

    public SimpleGraph(
            PathFinder<T> pathFinder,
            Traverse<T> traversal,
            boolean isDirected) {

        if (pathFinder == null || traversal == null) {
            throw new IllegalArgumentException("PathFinder and traversal should not be null");
        }
        this.pathFinder = pathFinder;
        this.traversal = traversal;
        this.isDirected = isDirected;
    }

    public synchronized void addVertex(T vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex should not be null");
        }
        vertices.add(vertex);
    }

    public synchronized void addEdge(T sourceVertex, T targetVertex) {
        if (!vertices.contains(sourceVertex) || !vertices.contains(targetVertex)) {
            throw new IllegalArgumentException("Illegal edge, one of vertices does not exist" );
        }
        if (sourceVertex == targetVertex) {
            return;
        }
        addToAdjacentVertices(sourceVertex, targetVertex);
        if (!isDirected) {
            addToAdjacentVertices(targetVertex, sourceVertex);
        }
    }

    public synchronized List<T> getPath(T sourceVertex, T targetVertex) {
        return pathFinder.findPath(this, sourceVertex, targetVertex);
    }


    @Override
    public synchronized void traverse(Consumer<T> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("Consumer should not be null");
        }
        traversal.traverse(this, consumer);
    }

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    @Override
    public synchronized Map<T, Set<T>> getAdjacentVertices() {
        return adjacentVertices;
    }

    @Override
    public synchronized Set<T> getVertices() {
        return vertices;
    }

    private void addToAdjacentVertices(T sourceVertex, T targetVertex) {
        adjacentVertices.computeIfAbsent(sourceVertex, k -> new HashSet<>())
                .add(targetVertex);
    }

}
