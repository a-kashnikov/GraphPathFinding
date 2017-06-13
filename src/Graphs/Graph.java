package Graphs;

import Exeptions.AdjacentMatrixCreationExeption;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Антон on 09.06.2017.
 */
public abstract class Graph {
    ArrayList<Node> nodes = new ArrayList<>();

    Func func = (s, list) -> {
        for (Node node: list
                ) {
            if (node.getName().equals(s))
                return node;
        }
        return null;
    };

    int [][] adjacentMatrix;

    protected Graph(int[][] adjacentMatrix) {
        this.adjacentMatrix = adjacentMatrix;
    }

    protected Graph(String[] adjacentMatrixRows) throws AdjacentMatrixCreationExeption {
        this.adjacentMatrix = new int[adjacentMatrixRows.length][];
        String [][] stringMatrix = new String[adjacentMatrixRows.length][];
        for (int i = 0; i < adjacentMatrixRows.length; i++) {
            stringMatrix[i] = adjacentMatrixRows[i].trim().split(" ");
            this.adjacentMatrix[i] = new int[stringMatrix[i].length];
        }
        for (int i = 0; i < stringMatrix.length; i++){
            for (int j = 0; j < stringMatrix[i].length; j++) {
                try {
                    this.adjacentMatrix[i][j] = Integer.parseInt(stringMatrix[i][j]);
                }
                catch (NumberFormatException e){
                    throw  new AdjacentMatrixCreationExeption("Wrong data format in String[].");
                }
            }
        }
    }

    protected Graph(String[][] adjacentMatrix) throws AdjacentMatrixCreationExeption {
        this.adjacentMatrix = new int [adjacentMatrix.length][];
        for (int i = 0; i < adjacentMatrix.length; i++){
            this.adjacentMatrix[i] = new int[adjacentMatrix[i].length];
            for (int j = 0; j < adjacentMatrix[i].length; j++) {
                try {
                    this.adjacentMatrix[i][j] = Integer.parseInt(adjacentMatrix[i][j]);
                }
                catch (NumberFormatException e){
                    throw  new AdjacentMatrixCreationExeption("Wrong data format in String[][].");
                }
            }
        }
    }

    protected Graph(char[][] adjacentMatrix) throws AdjacentMatrixCreationExeption {
        this.adjacentMatrix = new int [adjacentMatrix.length][];
        for (int i = 0; i < adjacentMatrix.length; i++){
            this.adjacentMatrix[i] = new int[adjacentMatrix[i].length];
            for (int j = 0; j < adjacentMatrix[i].length; j++) {
                try {
                    this.adjacentMatrix[i][j] = Integer.parseInt("" + adjacentMatrix[i][j]);
                }
                catch (NumberFormatException e){
                    throw  new AdjacentMatrixCreationExeption("Wrong data format in char[][].");
                }
            }
        }
    }

    protected Graph(String adjacentString) throws AdjacentMatrixCreationExeption {
        String [] rows = adjacentString.split("\\n");
        String [][] matrix = new String[rows.length][];
        this.adjacentMatrix = new int[matrix.length][];
        for (int i = 0; i < rows.length; i++) {
            matrix[i] = rows[i].trim().split(" ");
            this.adjacentMatrix[i] = new int[matrix[i].length];
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    this.adjacentMatrix[i][j] = Integer.parseInt(matrix[i][j]);
                }
                catch (NumberFormatException e){
                    throw  new AdjacentMatrixCreationExeption("Wrong data format in String.");
                }
            }
        }
    }

    protected Graph(int n) {
        this.adjacentMatrix = new int[n][n];
        Random random = new Random(Calendar.getInstance().getTimeInMillis());
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                this.adjacentMatrix[i][j] = random.nextInt(2);
                this.adjacentMatrix[j][i] = this.adjacentMatrix[i][j];
            }
        }
    }

    protected Graph(File file) throws FileNotFoundException, AdjacentMatrixCreationExeption {
        String adjacentString = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            adjacentString+=scanner.nextLine();
        }
        String [] rows = adjacentString.split("\\n");
        String [][] matrix = new String[rows.length][];
        this.adjacentMatrix = new int[matrix.length][];
        for (int i = 0; i < rows.length; i++) {
            matrix[i] = rows[i].trim().split(" ");
            this.adjacentMatrix[i] = new int[matrix[i].length];
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    this.adjacentMatrix[i][j] = Integer.parseInt(matrix[i][j]);
                }
                catch (NumberFormatException e){
                    throw  new AdjacentMatrixCreationExeption("Wrong data format in File.");
                }
            }
        }
    }

    protected abstract void checkMatrix(int[][] adjacentMatrix) throws AdjacentMatrixCreationExeption;

    class Node {
        Node(String name) {
            this.name = name;
        }
        private String name;
        int bfc = -1;

        void setBfc (int bfc){
            this.bfc = bfc;
        }

        void reset(){
            bfc = -1;
        }

        int getBfc() {
            return bfc;
        }

        String getName() {
            return name;
        }
    }

    void reset (){
        nodes = new ArrayList<>();
    }

}

