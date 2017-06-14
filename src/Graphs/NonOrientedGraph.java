package Graphs;

import Exeptions.AdjacentMatrixCreationExeption;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Антон on 10.06.2017.
 */
public class NonOrientedGraph extends Graph {

    public NonOrientedGraph(int[][] adjacentMatrix) throws AdjacentMatrixCreationExeption {
        super(adjacentMatrix);
        checkMatrix(super.adjacentMatrix);
    }

    public NonOrientedGraph(String[] adjacentMatrixRows) throws AdjacentMatrixCreationExeption {
        super(adjacentMatrixRows);
        checkMatrix(super.adjacentMatrix);
    }

    public NonOrientedGraph(String[][] adjacentMatrix) throws AdjacentMatrixCreationExeption {
        super(adjacentMatrix);
        checkMatrix(super.adjacentMatrix);
    }

    public NonOrientedGraph(char[][] adjacentMatrix) throws AdjacentMatrixCreationExeption {
        super(adjacentMatrix);
        checkMatrix(super.adjacentMatrix);
    }

    public NonOrientedGraph(String adjacentString) throws AdjacentMatrixCreationExeption {
        super(adjacentString);
        checkMatrix(super.adjacentMatrix);
    }

    public NonOrientedGraph(int n) {
        super(n);
    }

    public NonOrientedGraph(File file) throws FileNotFoundException, AdjacentMatrixCreationExeption {
        super(file);
        checkMatrix(super.adjacentMatrix);
    }

    @Override
    protected void checkMatrix(int[][] adjacentMatrix) throws AdjacentMatrixCreationExeption {
        int len = adjacentMatrix.length;
        for (int i = 0; i < len; i++) {
            if (len != adjacentMatrix[i].length)
                throw new AdjacentMatrixCreationExeption("Matrix is not quadratic!");
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (adjacentMatrix[i][j] != 0 && adjacentMatrix[i][j] != 1)
                    throw new AdjacentMatrixCreationExeption("Values in matrix must be 1 or 0");
                if (adjacentMatrix[i][j]!=adjacentMatrix[j][i])
                    throw new AdjacentMatrixCreationExeption("Matrix is not symmetrical");
            }
        }
    }

    public String getPathDepth (String start, String finish) {
        reset();
        int bfc = 0;
        String result = "";
        Stack<Integer> track = new Stack<>();
        for (int i = 0; i < adjacentMatrix.length; i++) {
            nodes.add(new Node("" + (i + 1)));
        }

        Node startNode = func.getNode(start, nodes);
        Node finishNode = func.getNode(finish, nodes);

        startNode.setBfc(bfc++);
        track.push(nodes.indexOf(startNode));
        while (true) {
            for (int i = 0; i < adjacentMatrix.length; i++) {
                if (adjacentMatrix[track.peek()][i] == 1 && nodes.get(i).getBfc() == -1) {
                    if (nodes.get(i) == finishNode) {
                        track.push(nodes.indexOf(finishNode));
                        result += (track.get(0)+1);
                        for (int j = 1; j < track.size(); j++) {
                            result += "-" + (track.get(j) + 1);
                        }
                        return result;
                    } else {
                        nodes.get(i).setBfc(bfc++);
                        track.push(nodes.indexOf(nodes.get(i)));
                    }
                }
            }

            track.pop();
            if (track.isEmpty())
                return null;
        }
    }

    public String getPathWidth (String start, String finish) {
        reset();
        int bfc = 0;
        String result = "";
        Queue<Integer> track = new LinkedList<>();
        for (int i = 0; i < adjacentMatrix.length; i++) {
            nodes.add(new Node("" + (i + 1)));
        }

        Node startNode = func.getNode(start, nodes);
        Node finishNode = func.getNode(finish, nodes);

        startNode.setBfc(bfc++);
        track.add(nodes.indexOf(startNode));
        while (true) {
            for (int i = 0; i < adjacentMatrix.length; i++) {
                if (adjacentMatrix[track.peek()][i] == 1 && nodes.get(i).getBfc() == -1) {
                    if (nodes.get(i) == finishNode) {
                        track.add(nodes.indexOf(finishNode));
                        result += (track.poll()+1);
                        for (int j = 1; j < track.size(); j++) {
                            result += "-" + (track.poll() + 1);
                        }
                        return result;
                    } else {
                        nodes.get(i).setBfc(bfc++);
                        track.add(nodes.indexOf(nodes.get(i)));
                    }
                }
            }

            track.poll();
            if (track.isEmpty())
                return null;
        }

    }



}
