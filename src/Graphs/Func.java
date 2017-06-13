package Graphs;

import java.util.ArrayList;

/**
 * Created by Антон on 13.06.2017.
 */
@FunctionalInterface
public interface Func {
     Graph.Node getNode(String s, ArrayList<Graph.Node> list);
}
