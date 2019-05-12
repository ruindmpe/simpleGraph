package com.natera.interview.graph.builder;

import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.pathfinding.impl.BfsAlgorithm;
import com.natera.interview.graph.visit.impl.BfTraversal;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GraphBuilderTest {

    private GraphBuilder<Integer> builder;

    @Before
    public void setUp() {
        /*
         *                (0)
         *               / | \
         *             (1) |  (2)
         *            / |  |    \
         *         (3) (4) |    (5)
         *                \|      \
         *                (6)-----(7)
         *                          \
         *                          (8)-(9)
         */

        builder = GraphBuilder.builder();
        for(int i = 0; i < 10; i++) {
            builder.addVertex(i);
        }
        builder.addEdge(0, 1)
                .addEdge(0, 2)
                .addEdge(0, 6)
                .addEdge(1, 3)
                .addEdge(1, 4)
                .addEdge(2, 5)
                .addEdge(4, 6)
                .addEdge(6, 7)
                .addEdge(5, 7)
                .addEdge(7, 8)
                .addEdge(8, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void directionIsNotDefined() {
        builder.build();
    }

    @Test
    public void allTheParametersArePassed() {
        Graph<Integer> graph = builder.undirected()
                .pathFinder(new BfsAlgorithm<>())
                .traversal(new BfTraversal<>())
                .build();
        assertEquals(10, graph.getVertices().size());
        assertEquals(new HashSet<>(Arrays.asList(1, 2, 6)), graph.getAdjacentVertices().get(0));

        List<Integer> path = graph.getPath(9, 4);
        assertNotNull(path);
        assertEquals(Arrays.asList(9, 8, 7, 6, 4), path);
    }
}