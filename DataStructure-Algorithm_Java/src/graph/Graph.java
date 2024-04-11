package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<String, ArrayList<String>> adjList = new HashMap<>();

    public HashMap<String, ArrayList<String>> getAdjList() {
        return adjList;
    }

    public void printGraph() {
        System.out.println(adjList);
    }

	//ADDVERTEX METHOD: O(1) //
	public boolean addVertex(String vertex) {
	    if (this.adjList.get(vertex) == null) {
	        adjList.put(vertex, new ArrayList<String>());
	        return true;
	    }
	    return false;
	}
}
