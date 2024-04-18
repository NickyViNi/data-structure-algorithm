package hashTable;

import java.util.HashMap;

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

}
