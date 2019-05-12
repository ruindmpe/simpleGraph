package com.natera.interview.graph.pathfinding.impl;

import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.pathfinding.PathFinder;

import java.util.*;

import static java.util.Collections.emptySet;

public class BfsAlgorithm<T> implements PathFinder<T> {

    public List<T> findPath(Graph<T> graph, T sourceVertex, T targetVertex) {
        Map<T, T> reversedPath = getAllPaths(graph.getAdjacentVertices(), sourceVertex);
        if (!reversedPath.containsKey(targetVertex)) {
            throw new IllegalStateException("There is no path between " + sourceVertex + " and " + targetVertex);
        }
        return backtracePath(sourceVertex, targetVertex, reversedPath);
    }

    private List<T> backtracePath(T sourceVertex, T targetVertex, Map<T, T> reversedPath) {
        List<T> path = new LinkedList<>();
        T vertex = targetVertex;
        while (vertex != null) {
            path.add(0, vertex);
            if (vertex.equals(sourceVertex)) {
                break;
            }
            vertex = reversedPath.get(vertex);
        }
        return path;
    }

    private Map<T, T> getAllPaths(Map<T, Set<T>> adjacentVertices, T sourceVertex) {
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        Map<T, T> allPaths = new HashMap<>();
        queue.add(sourceVertex);

        while (queue.size() != 0) {
            T vertex = queue.poll();
            adjacentVertices.getOrDefault(vertex, emptySet())
                    .stream()
                    .filter(v -> !visited.contains(v))
                    .forEach(v -> {
                        allPaths.put(v, vertex);
                        visited.add(v);
                        queue.add(v);
                    });
        }
        return allPaths;
    }
}
