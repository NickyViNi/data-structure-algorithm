package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
//133 medium -> deep clone graph
public class CloneGraph133 {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    //way 1:
    public Node cloneGraph1(Node node) {
        if (node == null) return node;
        HashMap<Node, Node> oldToNewMap = new HashMap<>();
        return copyGraph(node, oldToNewMap);
    }

    private Node copyGraph(Node node, HashMap<Node, Node> map) {
        Node copy = new Node(node.val, new ArrayList<Node>());
        map.put(node, copy);
        for (Node nei : node.neighbors) {
            if (map.containsKey(nei)) {
                copy.neighbors.add(map.get(nei));
            } else {
                copy.neighbors.add(copyGraph(nei, map));
            }
        }
        return copy;
    }

    //way 2:
    public Node cloneGraph2(Node node) {
        if (node == null) return node;
        HashMap<Node, Node> oldToNewMap = new HashMap<>();
        copyNode(node, oldToNewMap);
        copyNeighbors(node, oldToNewMap, new HashSet<Node>());
        return  oldToNewMap.get(node);
    }

    private void copyNode(Node node, HashMap<Node, Node> map) {
        if (map.containsKey(node)) return;
        map.put(node, new Node(node.val, new ArrayList<Node>()));
        for (Node old : node.neighbors) {
            copyNode(old, map);
        }
    }

    private void copyNeighbors(Node node, HashMap<Node, Node> map, HashSet<Node> set) {
        if (set.contains(node)) return;
        set.add(node);
        for (Node nei : node.neighbors) {
            map.get(node).neighbors.add(map.get(nei));
            copyNeighbors(nei, map, set);
        }
    }

}
