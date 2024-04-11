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

	//T: O(1)
	public boolean addVertex(String vertex) {
	    if (this.adjList.get(vertex) == null) {
	        adjList.put(vertex, new ArrayList<String>());
	        return true;
	    }
	    return false;
	}

    //T: O(1)
    public boolean addEdge(String vertex1, String vertex2) {
        if (this.adjList.get(vertex1) != null &&  this.adjList.get(vertex2) != null ) {
            this.adjList.get(vertex1).add(vertex2);
            this.adjList.get(vertex2).add(vertex1);
            return true;
        }
        return false;
    }
}
