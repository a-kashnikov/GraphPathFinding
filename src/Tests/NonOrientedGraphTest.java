package Tests;

import Exeptions.AdjacentMatrixCreationExeption;
import Graphs.NonOrientedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Антон on 13.06.2017.
 */
class NonOrientedGraphTest {
    @Test
    void checkMatrix() throws AdjacentMatrixCreationExeption {
        Throwable exception1 = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            NonOrientedGraph graph = new NonOrientedGraph(new char[][]{{'1', '1', '0'}, {'1', '1', '0'},{'0','0', '2'}});
        });
        assertEquals("Values in matrix must be 1 or 0", exception1.getMessage());

        Throwable exception2 = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            NonOrientedGraph graph = new NonOrientedGraph(new char[][]{{'1', '1', '0'}, {'1', '1', '0'},{'0','0'}});
        });
        assertEquals("Matrix is not quadratic!", exception2.getMessage());

        Throwable exception3 = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            NonOrientedGraph graph = new NonOrientedGraph(new char[][]{{'1', '0', '0'}, {'1', '1', '0'},{'0','0', '1'}});
        });
        assertEquals("Matrix is not symmetrical", exception3.getMessage());

        new NonOrientedGraph(new char[][]{{'1', '1', '0'}, {'1', '1', '0'},{'0','0', '1'}});
        new NonOrientedGraph(new String[][]{{"0", "1", "0", "1"}, {"1", "1", "1", "0"}, {"0", "1", "0", "0"}, {"1", "0", "0", "1"}});
    }

    @Test
    void getPathDepthTest() throws AdjacentMatrixCreationExeption {
        NonOrientedGraph graph = new NonOrientedGraph(new int[][]{  {0,1,0,1},
                                                                    {1,0,1,1},
                                                                    {0,1,1,1},
                                                                    {1,1,1,1}});
        assertEquals("1-2-3-4", graph.getPathDepth("1", "4"));
        assertEquals("1-2-3", graph.getPathDepth("1", "3"));

        NonOrientedGraph graph1 = new NonOrientedGraph(new int[][]{ {0,1,1,1},
                                                                    {1,0,1,0},
                                                                    {1,1,1,0},
                                                                    {1,0,0,1}});
        assertEquals("1-4", graph1.getPathDepth("1", "4"));
        assertEquals("3-1-4", graph1.getPathDepth("3", "4"));
    }

    @Test
    void getPathWidthTest() throws AdjacentMatrixCreationExeption {
        NonOrientedGraph graph = new NonOrientedGraph(new int[][]{  {0,1,0,1},
                                                                    {1,0,1,1},
                                                                    {0,1,1,1},
                                                                    {1,1,1,1}});
        assertEquals("1-2-4", graph.getPathWidth("1", "4"));
    }

    @Test
    void getPathWaveAlgorithmTest() throws AdjacentMatrixCreationExeption {
        NonOrientedGraph graph = new NonOrientedGraph(new int[][]{  {0,1,0,1},
                                                                    {1,0,1,1},
                                                                    {0,1,1,1},
                                                                    {1,1,1,1}});
        assertEquals("1-4", graph.getPathWaveAlgorithm("1", "4"));

        NonOrientedGraph graph1 = new NonOrientedGraph(
                "" +
                        "0 1 1 0 0 0 0 1\n" +
                        "1 0 0 1 0 0 0 0\n" +
                        "1 0 0 0 1 0 0 0\n" +
                        "0 1 0 0 0 0 1 0\n" +
                        "0 0 1 0 0 1 0 0\n" +
                        "0 0 0 0 1 0 1 0\n" +
                        "0 0 0 1 0 1 0 1\n" +
                        "1 0 0 0 0 0 1 0");
        assertEquals("1-3-5", graph1.getPathWaveAlgorithm("1", "5"));
    }
}