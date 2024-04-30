package hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HTleetcode {
    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 4, 5, 3};
        int[] arr2 = {2, 4, 6, 8, 10, 4};
        int target = 7;

        List<int[]> pairs = findPairs(arr1, arr2, target);
        for (int[] pair : pairs) {
            System.out.println(Arrays.toString(pair));
        }

        // int[] nums = {1, 2, 3, 2, 1, 4, 5, 4};
        // List<Integer> duplicates = findDuplicates2(nums);
        // System.out.println(duplicates);
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

    public static boolean hasUniqueChars (String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>(); // handle duplicate values
        for (int e: arr1) {
            set1.add(e);
        }

        List<int[]> result = new ArrayList<>();
        for ( int e : arr2) {
            int num = target - e;
            if (set1.contains(num) && !set2.contains(e)) {
                int[] temp = new int[]{num, e};
                result.add(temp);
                set2.add(e);
            }
        }
        return result;
    }

    //49 medium: group anagram
    public static List<List<String>> groupAnagrams(String[] str) {

        HashMap<String, List<String>> anagram = new HashMap<>();

        for (int i = 0; i < str.length; i++) {
            char[] ch = str[i].toCharArray();
            Arrays.sort(ch);
            String sorted = String.valueOf(ch); // new String(ch)

            if (anagram.containsKey(sorted)) {
                anagram.get(sorted).add(str[i]);

            } else {
                List<String> group = new ArrayList<>();
                group.add(str[i]);
                anagram.put(sorted, group);
            }
        }

        List<List<String>> values = new ArrayList<>(anagram.values());
        return values;
    }

    //udemy question57: subarray sum
    public static int[] subarraySum(int[] nums, int target) {
        Map<Integer, Integer> mapIdx = new HashMap<>();
        int sum = 0;
        mapIdx.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (mapIdx.containsKey(sum - target)) {
                return new int[] { mapIdx.get(sum - target) + 1, i };
            }
            mapIdx.put(sum, i);
        }

        return new int[]{};
    }

    //560 medium: Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
    public int subarraySumK(int[] nums, int k) {
        Map<Integer, Integer> mapIdx = new HashMap<>();
        int currSum = 0;
        int subarrayCount = 0;
        mapIdx.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if (mapIdx.containsKey(currSum - k)) {
                subarrayCount += mapIdx.get(currSum - k);
            }
            mapIdx.put(currSum, mapIdx.getOrDefault(currSum, 0) + 1);
        }

        return subarrayCount;
    }

    //128 medium: Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int maxLen = 0;

        for (int e : nums) {
            set.add(e);
        }

        for (int i = 0; i < nums.length; i++) {
            int currNum = nums[i];
            int currLen = 0;
            if (!set.contains(nums[i] + 1)) {
                while(set.contains(currNum)) {
                    currNum--;
                    currLen++;
                }
            }
            if (currLen > maxLen) maxLen = currLen;
        }

        return maxLen;
    }

    //383 easy -> random note
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        Map<Character, Integer> mag = new HashMap<>();

        for (int i = 0; i < magazine.length(); i++) {
            mag.put(magazine.charAt(i), mag.getOrDefault(magazine.charAt(i), 0) + 1);
        }

        for (char ran : ransomNote.toCharArray()) {
            if (mag.get(ran) == null || mag.get(ran) <= 0) return false;
            mag.put(ran, mag.get(ran)-1);
        }

        return true;
    }
    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] maga = new int[26];
        for (char ch : magazine.toCharArray()) {
            maga[ch - 'a']++;
        }

        for (char ra : ransomNote.toCharArray()) {
            if (maga[ra - 'a'] < 1) return false;
            maga[ra - 'a']--;
        }

        return true;
    }

    //202 easy -> happy number
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            slow = getSquareVal(slow);
            fast = getSquareVal(getSquareVal(fast));
        }
        while (fast != slow && fast != 1);
        return fast == 1;
    }

    private static int getSquareVal(int num) {
        int sum = 0;
        while (num != 0) {
            sum += (num % 10) * (num % 10);
            num /= 10;
        }
        return sum;
    }
    //205 easy -> isomorphic strings
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> smap = new HashMap<>();
        Map<Character, Character> tmap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char schar = s.charAt(i), tchar = t.charAt(i);
            if (smap.containsKey(schar)) {
                if (smap.get(schar) != tchar) return false;
            } else {
                if (tmap.containsKey(tchar)) return false;
                smap.put(schar, tchar);
                tmap.put(tchar, schar);
            }
        }
        return true;
    }
}
