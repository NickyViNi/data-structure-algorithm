package linkedList;

// import java.util.Map;
import java.util.HashMap;
// import java.util.LinkedList;

class LRUCache {
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private HashMap<Integer, Node> mapCache;
    private int capacity;
    private Node head; // oldest node
    private Node tail; // newest node

    public LRUCache(int capacity) {
        this.mapCache = new HashMap<>();
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        Node node = mapCache.get(key);
        if (node == null) return -1;
        if (node != this.tail) {
            if (node == this.head) {
                this.head = this.head.next;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            this.tail.next = node;
            node.prev = this.tail;
            node.next = null;
            this.tail = node;
        }
        return node.value;
    }

    public void put(int key, int value) {
        Node node = this.mapCache.get(key);
        if (node != null) {
            node.value = value;
            if (node != this.tail) {
            if (node == this.head) {
                this.head = this.head.next;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            this.tail.next = node;
            node.prev = this.tail;
            node.next = null;
            this.tail = node;
            }
        } else {
            Node newNode = new Node(key, value);
            if (this.capacity == 0) {
            Node temp = this.head;
            this.head = this.head.next;
            this.mapCache.remove(temp.key);
            this.capacity++;
            }
            if (this.head == null) {
                this.head = newNode;
            } else {
                this.tail.next = newNode;
                newNode.prev = this.tail;
            }
            this.mapCache.put(key, newNode);
            this.tail = newNode;
            this.capacity--;
        }
    }
}

//O(n)
// public class LRUCache {
//     Map<Integer, Integer> mapCache;
//     LinkedList<Integer> queue;
//     int size;

//     public LRUCache(int capacity) {
//         this.size = capacity;
//         this.mapCache = new HashMap<>(capacity);
//         this.queue = new LinkedList<>();
//     }

//     public int get(int key) {
//         if (!mapCache.containsKey(key)) return -1;
//         queue.removeFirstOccurrence(key); // O(n)
//         queue.add(key);
//         return mapCache.get(key);
//     }

//     public void put(int key, int value) {
//         if (mapCache.containsKey(key)) {
//             queue.removeFirstOccurrence(key);
//         } else if (size == queue.size()) {
//             int removeKey = queue.poll();
//             mapCache.remove(removeKey);
//         }
//         mapCache.put(key, value);
//         queue.add(key);
//     }
// }
