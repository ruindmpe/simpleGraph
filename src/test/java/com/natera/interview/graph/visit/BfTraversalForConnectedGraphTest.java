package com.natera.interview.graph.visit;

import com.natera.interview.graph.builder.GraphBuilder;
import com.natera.interview.graph.model.Graph;
import com.natera.interview.graph.pathfinding.impl.BfsAlgorithm;
import com.natera.interview.graph.visit.impl.BfTraversal;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class BfTraversalForConnectedGraphTest {

    private Graph<Integer> simpleGraph;

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
        GraphBuilder<Integer> builder = GraphBuilder.<Integer>builder()
                .pathFinder(new BfsAlgorithm<>())
                .traversal(new BfTraversal<>())
                .undirected();
        for(int i = 0; i < 12; i++) {
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
        simpleGraph = builder.build();
    }

    @Test
    public void traverseAndCountAllVertices() {
        final int[] verticesCounter = {0};
        simpleGraph.traverse(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                verticesCounter[0]++;
            }
        });
        assertEquals(simpleGraph.getVertices().size(), verticesCounter[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tryToTraverseWithoutConsumer() {
        final int[] verticesCounter = {0};
        simpleGraph.traverse(null);
    }
}