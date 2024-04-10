package tree;

import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        /* Class TreeMap<K,V> A Red-Black tree based NavigableMap implementation.
        The map is sorted according to the natural ordering of its keys,
        or by a Comparator provided at map creation time, depending on which constructor is used.
        Complexity: TreeMap operations like insertion, deletion, and retrieval have a time complexity of O(log n),
        where n is the number of elements in the TreeMap.
        This is due to the self-balancing nature of the underlying Red-Black Tree. */
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "C");
        treeMap.put(2, "B");
        treeMap.put(1, "A");
        System.out.println(treeMap);
    }
}
