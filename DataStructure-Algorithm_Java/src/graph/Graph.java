package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    // space complexity: AM(adjacency matrix)=> O(|V|^2), AL(adjacency list)=> O(|V|+|E|), V:Vertex numbers, E:Edge numbers
    // time: add a vertex: AM => O(|V|^2), AL => O(1); add edge: both are O(1);
    // time: remove edge: AM => O(1), AL => O(|E|); remove vertex: AM => O(|V|^2), AL => O(|V|+|E|)
    private HashMap<String, ArrayList<String>> adjList = new HashMap<>(); // Adjacency List

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

    //T: O(E)
    public boolean removeEdge(String vertex1, String vertex2) {
	    if (adjList.get(vertex1) != null && adjList.get(vertex2) != null) {
            adjList.get(vertex1).remove(vertex2);
            adjList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
	}

    //T: O(V+E)
    public boolean removeVertex(String vertex) {
        if(this.adjList.get(vertex) == null) return false;
        // for(String vert: adjList.get(vertex)) {
        //     adjList.get(vert).remove(vertex);
        // }
        adjList.get(vertex).forEach(vert -> adjList.get(vert).remove(vertex));
        adjList.remove(vertex);
        return true;
    }
}
