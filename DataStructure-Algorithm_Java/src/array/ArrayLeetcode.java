package array;

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
}
