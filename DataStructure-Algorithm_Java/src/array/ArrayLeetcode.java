package array;

import java.util.HashMap;
import java.util.Map;

public class ArrayLeetcode {

    public static int[] findMaxMin(int[] myList) {
        int max = myList[0];
        int min = myList[0];
        for (int num : myList) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        return new int[] {max, min};
    }

    //way1: remove all occurrences of val in-place from the array and return the new length of the array after removal.
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length - count; i++) {
            if (nums[i] == val) {

                for (int k = i; k < nums.length - count - 1; k++) {
                    nums[k] = nums[k + 1];
                }
                count++;
                i--;
            }
        }
        return nums.length - count;
    }
    //way2:
    public static int removeElement2(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    //find a first longest string in a string array
    public static String findLongestString(String[] stringList) {
        String longest = "";
        for ( String str : stringList) {
            if (str.length() > longest.length()) {
                longest = str;
            }
        }
        return longest;
    }

    //removes all duplicate occurrences of integers from a sorted array in-place such that each unique element appears only once.
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int moveIdx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[moveIdx] = nums[i];
                moveIdx++;
            }
        }
        return moveIdx;
    }

    // 121 easy: best time to sell and buy stock
    public static int maxProfit1(int[] prices) {
        if (prices.length == 0) return 0;
        int buy = prices[0];
        int maxEarn = 0;
        for (int price : prices) {
            if (price < buy) {
                buy = price;
            } else if (price - buy > maxEarn) {
                maxEarn = price - buy;
            }
        }

        return maxEarn;
    }

    public static int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            buy = Math.min(buy, price);
            int tempProfit = price - buy;
            profit = Math.max(tempProfit, profit);
        }

        return profit;
    }

    //189 medium: rotate an array by k steps:
    // O(n)
    public static void rotate2(int[] nums, int k) {
        k = k % nums.length;
        // Reverse the first part
        for (int start = 0, end = nums.length - k - 1; start < end; start++, end--) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        // Reverse the second part
        for (int start = nums.length - k, end = nums.length - 1; start < end; start++, end--) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        // Reverse the whole array
        for (int start = 0, end = nums.length - 1; start < end; start++, end--) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
    }
    //O(n)
    public void rotate(int[] nums, int k) {
        k = k%nums.length;
        // reverse complete array
        reverseArray(nums, 0, nums.length-1);
        // reverse left part of array excluding kth element
        reverseArray(nums, 0, k-1);
        // reverse right part of array starting with kth element
        reverseArray(nums, k, nums.length-1);

    }
    public void reverseArray(int[] a, int l, int r){
        while(l < r){
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            l++; r--;
        }
    }
    //O(n^2)
    public static void rotate1(int[] nums, int k) {
        if (k == nums.length) return;
        int steps = k % nums.length;
        for (int i = 0; i < steps; i++) {
            int last = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }

    //53 medium -> maximum subarray
    public static int maxSubarray(int[] nums) {
        if (nums.length == 0) return 0;
        int maxSum = nums[0];
        int currSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    //88 easy -> merge sorted array:
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        int idx = n + m - 1;
        //place the greater elements at the end of nums1
        while (n > 0 && m > 0) {
            if (nums2[n-1] >= nums1[m-1]) {
                nums1[idx] = nums2[n-1];
                n--;
            } else {
                nums1[idx] = nums1[m-1];
                m--;
            }
            idx--;
        }

        while (n > 0) {
            nums1[idx] = nums2[n-1];
            n--;
            idx--;
        }
    }

    //80 medium -> remove duplicates more than two from sorted array
    public int removeDuplicates2(int[] nums) {
        int k = 1;
        for (int i = 1; i < nums.length; i++) {
            if ( k == 1 || nums[i] != nums[k - 2] ) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }

    //169 easy -> majority element
    // time: O(n), space: O(n)
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> hash = new HashMap<>();
        for (int num : nums) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);
        }
        int n = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            if (entry.getValue() > n) {
                return entry.getKey();
            }
        }
       return -1;
    }
    //time: O(n), space: O(1)
    public int majorityElement2(int[] nums) {
        //boyer-moore voting algorithm
        int count = 0;
        Integer candidate = null;
        //find an element that appears more frequently
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }
        //check the element is majority element, if not, return -1;
        int times = 0;
        for (int num : nums) {
            if (num == candidate) times++;
        }
        if (times > nums.length / 2) return candidate;
        return -1;
    }

}
