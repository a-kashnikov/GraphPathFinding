package Tests;

import Exeptions.AdjacentMatrixCreationExeption;
import Graphs.Graph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Антон on 10.06.2017.
 */
class GraphTest {

    @Test
    void Graph() throws AdjacentMatrixCreationExeption, FileNotFoundException {

        Throwable exception = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            new Graph(new String [][]{{"1", "a"},{"1", "1"}}){
                @Override
                protected void checkMatrix(int[][] matrix) {}
            };});
        assertEquals("Wrong data format in String[][].", exception.getMessage());

        Throwable exception1 = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            new Graph(new char[][]{{'1', '0', 'g'}, {'1', '0', '1'}}) {
                @Override
                protected void checkMatrix(int[][] adjacentMatrix) {}
            };});
        assertEquals("Wrong data format in char[][].", exception1.getMessage());

        Throwable exception2 = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            new Graph(new String[]{"1", "a", "1", "1"}) {
                @Override
                protected void checkMatrix(int[][] adjacentMatrix) {}
            };});
        assertEquals("Wrong data format in String[].", exception2.getMessage());

        Throwable exception3 = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            new Graph("1 1 1 0 1 1\n 1 0 dd 0 1") {
                @Override
                protected void checkMatrix(int[][] adjacentMatrix) {}
            };});
        assertEquals("Wrong data format in String.", exception3.getMessage());

        Throwable exception4 = assertThrows(AdjacentMatrixCreationExeption.class, ()-> {
            new Graph(new File("wrong.txt")) {
                @Override
                protected void checkMatrix(int[][] adjacentMatrix) {}
            };});
        assertEquals("Wrong data format in File.", exception4.getMessage());


        new Graph(new int[][]{{1, 0, 1}, {1, 1, 0}}) {
            @Override
            protected void checkMatrix(int[][] adjacentMatrix) {}
        };
        new Graph(new String[][]{{"1", "1"}, {"1", "1"}}) {
            @Override
            protected void checkMatrix(int[][] adjacentMatrix) {}
        };
        new Graph(new char[][]{{'1', '0', '1'}, {'1', '0', '1'}}) {
            @Override
            protected void checkMatrix(int[][] adjacentMatrix) {}
        };
        new Graph(new String[]{"1 1 0 1 0", "1 0 1 0 1 0"}) {
            @Override
            protected void checkMatrix(int[][] adjacentMatrix) {}
        };
        new Graph("1 1 1 0 1 1\n 1 0 1 0 1") {
            @Override
            protected void checkMatrix(int[][] adjacentMatrix) {}
        };
        new Graph(6) {
            @Override
            protected void checkMatrix(int[][] adjacentMatrix) {}
        };
        new Graph(new File("right.txt")) {
            @Override
            protected void checkMatrix(int[][] adjacentMatrix) {}
        };
    }

}