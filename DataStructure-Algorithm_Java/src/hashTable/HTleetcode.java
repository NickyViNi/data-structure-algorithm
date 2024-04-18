package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HTleetcode {
    public static void main(String[] args) {

        int[] array1 = {1, 3, 5};
        int[] array2 = {2, 4, 6, 5};

        System.out.println(itemInCommon(array1, array2));
    }

    public static boolean itemInCommon(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            hmap.put(arr1[i], i);
        }

        for (int i = 0; i < arr2.length; i++) {
            if (hmap.get(arr2[i]) != null) return true;
        }

        return false;
    }

    public static List<Integer> findDuplicates(int[] arr) {
        Map<Integer, Boolean> hmap = new HashMap<>();
        Set<Integer> result = new HashSet<>();
        for (int e : arr) {
            if (hmap.get(e) == null) {
                hmap.put(e, true);
            } else {
                result.add(e);
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>(result);
        return arrayList;
    }

    public static ArrayList<Integer> findDuplicates2(int[] nums) {

        // Create a new HashMap to store the count of occurrences
        // of each integer.
        Map<Integer, Integer> numCounts = new HashMap<>();

        // Loop through each integer in the input array and update
        // its count in the HashMap.
        for (int num : nums) {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
        }

        // Create a new ArrayList to store the duplicate integers.
        List<Integer> duplicates = new ArrayList<>();

        // Loop through each entry in the HashMap and check if the
        // count of occurrences is greater than 1.
        for (Map.Entry<Integer, Integer> entry : numCounts.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }

        // Return the ArrayList of duplicate integers.
        return duplicates;
    }

}
