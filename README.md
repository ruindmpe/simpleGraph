# simpleGraph

The simpleGraph is a simple library for working with graphs.
 
It supports 2 types of graphs - directed and undirected with 3 operations:

- addVertex: adds vertex to the graph
- addEdge: adds edge to the graph
- getPath: returns a list of edges between 2 vertices (path is not optimal)

Vertices are of a user defined type.

## Getting started

### Usage

Example of usage with graph:

```java
import com.natera.interview.graph.builder.GraphBuilder;
import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.pathfinding.impl.BfsAlgorithm;
import com.natera.interview.graph.visit.impl.BfTraversal;

import java.util.List;

public class Application {

    public static void main (String ... args) {
        GraphBuilder<Integer> builder = GraphBuilder.<Integer>builder()
                .pathFinder(new BfsAlgorithm<>())
                .traversal(new BfTraversal<>())
                .undirected()
                .addVertex(1)
                .addVertex(2)
                .addVertex(3)
                .addVertex(4)
                .addEdge(1, 2)
                .addEdge(1, 3)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .build();
        List<Integer> path = graph.getPath(1, 4);
        System.out.println(path); // [1, 3, 4]

        graph.traverse(System.out::println);
        // 1
        // 2
        // 3
        // 4
    }
}
```

####Graph
```java
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
     * @param sourceVertex 1st vertex of edge
     * @param targetVertex   2nd vertex
     * @throws IllegalArgumentException if graph does'not contains fromVertex or toVertex vertexes
     */
    void addEdge(T sourceVertex, T targetVertex);

    /**
     * Method finds path between {sourceVertex} and {targetVertex} vertices.
     *
     * @param sourceVertex the source of the path
     * @param targetVertex   the target of the path
     * @return ordered list with vertices
     * @throws IllegalStateException if path is not found
     */
    List<T> getPath(T sourceVertex, T targetVertex);

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

    /**
     * Method returns all adjacent vertices
     *
     * @return adjacent vertices
     */
    Map<T, Set<T>> getAdjacentVertices();

    /**
     * Method returns all vertices
     *
     * @return vertices
     */
    Set<T> getVertices();
}
```

####PathFinder
```java
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
```

####Traverse
```java
package com.natera.interview.graph.visit;

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

```