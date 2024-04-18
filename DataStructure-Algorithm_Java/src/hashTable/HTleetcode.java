package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HTleetcode {
    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 2, 1, 4, 5, 4};
        List<Integer> duplicates = findDuplicates2(nums);
        System.out.println(duplicates);

        // int[] array1 = {1, 3, 5};
        // int[] array2 = {2, 4, 6, 5};
        // System.out.println(itemInCommon(array1, array2));
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

    public static List<Integer> findDuplicates2(int[] nums) {

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

    // First Non-Repeating Character
    public static Character firstNonRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for ( char c : str.toCharArray() ) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for ( char c : str.toCharArray() ) {
            if (map.get(c) == 1) {
                return c;
            }
        }
        return null;
    }

    //387: easy -> first unique character in a string
    public int firstUniqChar(String str) {
        int[] letters = new int[26];
        for (char c : str.toCharArray()) {
            letters[c - 'a']++;
        }
        for ( int i = 0; i < str.length(); i++ ) {
            if (letters[str.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    //1 easy -> two sum
    public static int[] twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int other = target - arr[i];
            if (map.containsKey(other)) {
                return new int[] {map.get(other), i};
            }
            map.put(arr[i], i);
        }

        return new int[]{};
    }

    public static List<Integer> removeDuplicates(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        List<Integer> arr = new ArrayList<>(set);

        return arr;
    }
}
