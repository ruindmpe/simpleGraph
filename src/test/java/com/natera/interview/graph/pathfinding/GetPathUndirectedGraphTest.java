package com.natera.interview.graph.pathfinding;

import com.natera.interview.graph.builder.GraphBuilder;
import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.pathfinding.impl.BfsAlgorithm;
import com.natera.interview.graph.visit.impl.BfTraversal;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;

public class GetPathUndirectedGraphTest {
    private Graph<Integer> graph;

    @Before
    public void setUp() {
        /*
         *        (1)----(2)----(3)       (8)---(9)
         *       /                \     /        |
         *     (0)----(6)---(5)---(4)--(7)       |
         *      \                               /
         *        -----------------------------
         *
         *              (10)-(11)-(12)
         */
        GraphBuilder<Integer> builder = GraphBuilder.<Integer>builder()
                .pathFinder(new BfsAlgorithm<>())
                .traversal(new BfTraversal<>())
                .undirected();
        for(int i = 0; i < 13; i++) {
            builder.addVertex(i);
        }
        graph = builder
                .addEdge(0, 1)
                .addEdge(1, 2)
                .addEdge(2, 3)
                .addEdge(3, 4)
                .addEdge(4, 5)
                .addEdge(5, 6)
                .addEdge(6, 0)
                .addEdge(4, 7)
                .addEdge(7, 8)
                .addEdge(8, 9)
                .addEdge(9, 0)
                .addEdge(10, 11)
                .addEdge(11, 12)
                .build();
    }

    @Test
    public void findCorrectPath() {
        List<Integer> path = graph.getPath(2, 0);
        assertNotNull(path);
        assertEquals(Arrays.asList(2, 1, 0), path);
    }

    @Test(expected = IllegalStateException.class)
    public void pathDoesNotExist() {
        graph.getPath(2, 12);
    }
}