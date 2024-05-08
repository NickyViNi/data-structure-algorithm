package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//380 medium -> Insert, Delete, getRandom, O(1)
public class RandomizedSet {
    List<Integer> list;
    HashMap<Integer, Integer> map;
    Random random;
    public RandomizedSet() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int removeIdx = map.get(val);
        int lastIdx = list.size() - 1;
        list.set(removeIdx, list.get(lastIdx));
        map.put(list.get(lastIdx), removeIdx);
        list.remove(lastIdx);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        int ranIdx = random.nextInt(list.size());
        return list.get(ranIdx);
    }
}
