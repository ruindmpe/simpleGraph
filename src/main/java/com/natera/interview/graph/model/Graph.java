package com.natera.interview.graph.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public interface Graph<T> {
    /**
     * Add vertex to the graph
     * Vertex must be unique, just as Map keys must be. They must also be non-null.
     *
     * @param vertex to add
     */
    void addVertex(T vertex);

    /**
     * Adds an edge connecting fromVertex to toVertex if one is not already present.
     * In an undirected graph, the edge will also connect toVertex to fromVertex.
     *
     * @param fromVertex 1st vertex of edge
     * @param toVertex   2nd vertex
     * @throws IllegalArgumentException if graph does'not contains fromVertex or toVertex vertexes
     */
    void addEdge(T fromVertex, T toVertex);

    /**
     * Method finds path between {fromVertex} and {toVertex} vertices.
     *
     * @param fromVertex the source of the path
     * @param toVertex   the target of the path
     * @return ordered list with vertices
     * @throws IllegalStateException if path is not found
     */
    List<T> getPath(T fromVertex, T toVertex);

    /**
     * Directed edges connect a source vertex to a target vertex,
     * while undirected edges connect a pair of vertices to each other.
     * @return true - if the edges in this graph are directed
     */
    boolean isDirected();

    /**
     * Visiting each vertex in a graph and call consumer on each vertex.
     *
     * @param consumer will be called on each vertex
     * @throws IllegalArgumentException if consumer is null
     */
    void traverse(Consumer<T> consumer);
}
