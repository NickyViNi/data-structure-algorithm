package linkedList;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache {
    Map<Integer, Integer> mapCache;
    LinkedList<Integer> queue;
    int size;

    public LRUCache(int capacity) {
        this.size = capacity;
        this.mapCache = new HashMap<>(capacity);
        this.queue = new LinkedList<>();
    }

    public int get(int key) {
        if (!mapCache.containsKey(key)) return -1;
        queue.removeFirstOccurrence(key); // O(n)
        queue.add(key);
        return mapCache.get(key);
    }

    public void put(int key, int value) {
        if (mapCache.containsKey(key)) {
            queue.removeFirstOccurrence(key);
        } else if (size == queue.size()) {
            int removeKey = queue.poll();
            mapCache.remove(removeKey);
        }
        mapCache.put(key, value);
        queue.add(key);
    }
}
