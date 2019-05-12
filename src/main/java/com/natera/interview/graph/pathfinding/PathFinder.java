package com.natera.interview.graph.pathfinding;

import com.natera.interview.graph.model.Graph;

import java.util.List;

/**
 * Finds a path between source and target vertices
 *
 * @param <T> vertex type
 */
public interface PathFinder<T> {
    /**
     * Method finds path between {fromVertex} and {toVertex} vertices
     * @param graph graph to find the path in
     * @param fromVertex the source of the path
     * @param toVertex the target of the path
     * @return ordered list with vertices
     * @throws IllegalStateException if path is not found
     */
    List<T> findPath(Graph<T> graph, T fromVertex, T toVertex);
}
