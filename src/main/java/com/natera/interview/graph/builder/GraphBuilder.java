package com.natera.interview.graph.builder;

import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.model.impl.SimpleGraph;
import com.natera.interview.graph.pathfinding.PathFinder;
import com.natera.interview.graph.visit.Traverse;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import java.util.HashSet;
import java.util.Set;

public class GraphBuilder<T> {

    private final Set<T> vertices = new HashSet<>();
    private final Set<Pair<T, T>> edges = new HashSet<>();
    private Boolean isDirected;
    private PathFinder<T> pathFinder;
    private Traverse<T> traversal;

    private GraphBuilder() {
    }

    public static <T> GraphBuilder<T> builder() {
        return new GraphBuilder<>();
    }

    public GraphBuilder<T> addVertex(T vertex) {
        vertices.add(vertex);
        return this;
    }

    public GraphBuilder<T> addEdge(T fromVertex, T toVertex) {
        edges.add(new ImmutablePair(fromVertex, toVertex));
        return this;
    }

    public GraphBuilder<T> pathFinder(PathFinder<T> pathFinder) {
        this.pathFinder = pathFinder;
        return this;
    }

    public GraphBuilder<T> traversal(Traverse<T> traversal) {
        this.traversal = traversal;
        return this;
    }

    public GraphBuilder<T> directed() {
        this.isDirected = true;
        return this;
    }

    public GraphBuilder<T> undirected() {
        this.isDirected = false;
        return this;
    }

    public Graph<T> build() {
        if (isDirected == null) {
            throw new IllegalArgumentException(
                    "Edge type should be defined: directed or undirected");
        }
        Graph<T> graph = new SimpleGraph<>(pathFinder, traversal, isDirected);
        vertices.forEach(graph::addVertex);
        edges.forEach(edge ->
                graph.addEdge(edge.getLeft(), edge.getRight()));
        return graph;
    }
}
