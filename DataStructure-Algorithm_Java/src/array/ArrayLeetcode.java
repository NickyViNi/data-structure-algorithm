package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

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

    //13 easy -> roman to integer
    public int romanToInt(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        hash.put('I', 1);
        hash.put('V', 5);
        hash.put('X', 10);
        hash.put('L', 50);
        hash.put('C', 100);
        hash.put('D', 500);
        hash.put('M', 1000);
        int number = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (hash.get(s.charAt(i)) >= hash.get(s.charAt(i + 1))) {
                number +=  hash.get(s.charAt(i));
            } else {
                number -= hash.get(s.charAt(i));
            }
        }
        return number + hash.get(s.charAt(s.length() - 1));
    }

    //122 medium -> best time to sell and buy stock II
    public int maxProfitII(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            buy = Math.min(buy, price);
            if (price > buy) {
                profit += price - buy;
                buy = price;
            }
        }
        return profit;
    }
    //122
    public int maxProfitII2(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    //209 medium -> Minumum size subarray sum
    /*Given an array of positive integers nums and a positive integer target, return the minimal length of a
    subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead. */
    public int minSubArrayLen(int target, int[] nums) {

        int left = 0, right = 0, currSum = 0, minLen = Integer.MAX_VALUE;

        while (right < nums.length) {
            currSum += nums[right];
            right++;
            while (currSum >= target) {
                currSum -= nums[left];
                minLen = Math.min(minLen, right - left );
                left++;
            }
        }
        minLen = minLen == Integer.MAX_VALUE ? 0 : minLen;
        return minLen;
    }

    //3 medium -> Given a string s, find the length of the longest substring without repeating characters.
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxLen = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            while(!set.add(s.charAt(right))) {
                set.remove(s.charAt(right));
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    //3 medium -> solution2
    public int lengthOfLongestSubstring2(String s) {
        int maxLen = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            while (right != s.indexOf(s.charAt(right), left)) {
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    //125 easy -> valid palindrome
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (!Character.isLetterOrDigit(l)) {
                left++;
            } else if (!Character.isLetterOrDigit(r)) {
                right--;
            } else if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    //392 easy -> is subsequence
    public boolean isSubsequence(String s, String t) {
        int p1 = 0, p2 = 0;
        while (p1 < s.length() && p2 < t.length()) {
            if (s.charAt(p1) == t.charAt(p2)) {
                p1++;
            }
            p2++;
        }
        return p1 == s.length();
    }

    //167 medium -> two sum II
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (numbers[left] + numbers[right] != target) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[] {left + 1, right + 1};
    }

    //15 medium -> 3 sum
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int l = i + 1, r = nums.length - 1;
            while ( l < r ) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                }
            }
        }
        return result;
    }
    //18 medium -> 4 sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        result.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;
                        left++;
                        right--;
                    }
                }
                while (j < nums.length - 2 && nums[j] == nums[j+1]) j++;
            }
            while (i < nums.length - 3 && nums[i] == nums[i+1]) i++;
        }
        return result;
    }
    //12 medium -> Integer to Roman
    public String intToRoman(int num) {
        int[] integer = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String result = "";
        for (int i = 0; i < 13; i++) {
            while (num >= integer[i]) {
                result += roman[i];
                num -= integer[i];
            }
        }
        return result;
    }

    //75 medium -> sort color
    public void sortColors(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.getOrDefault(0, 0) > 0) {
                nums[i] = 0;
                map.put(0, map.get(0) - 1);
            } else if (map.getOrDefault(1, 0) > 0) {
                nums[i] = 1;
                map.put(1, map.get(1) - 1);
            } else if (map.getOrDefault(2, 0) > 0) {
                nums[i] = 2;
                map.put(2, map.get(2) - 1);
            }
        }
    }
    //The Dutch National Flag algorithm
    public void sortColors2(int[] nums) {
        int low = 0, mid = 0, high = nums.length -1;
        while (mid <= high) {
             if (nums[mid] == 0) {
                 if (nums[low] != 0) swap(nums, low, mid);
                 low++;
                 mid++;
             } else if (nums[mid] == 1) {
                 mid++;
             } else {
                 if (nums[high] != 2) swap(nums, mid, high);
                 high--;
             }
        }
     }
     private void swap(int[] nums, int idx1, int idx2) {
         int temp = nums[idx1];
         nums[idx1] = nums[idx2];
         nums[idx2] = temp;
     }

     //96 medium -> unique binary search tree, dynamic programming, catalan numbers: Cn = 1/(n+1) * (2n)!/(n!n!)
     public int numTrees(int n) {
        int[] bstCount = new int[n + 1];
        bstCount[0] = 1;
        bstCount[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                bstCount[i] += bstCount[j - 1] * bstCount[i - j];
            }
        }
        return bstCount[n];
    }

    //58 super easy ->length of last word
    public int lengthOfLastWord(String s) {
        int lastLen = 0;
        int currLen = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') currLen++;
            else currLen = 0;
            lastLen = currLen > 0 ? currLen : lastLen;
        }
        return lastLen;
    }
    public int lengthOfLastWord2(String s) {
        String[] words = s.split(" ");
        return words[words.length - 1].length();
    }

    //11 medium -> cotainer with most water : two pointers
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
        while (left < right) {
            int curr = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(curr, maxArea);
            if (height[left] > height[right]) right--;
            else left++;
        }
        return maxArea;
    }

    //215 medium -> Kth largest element in an array
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) return nums[0];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            maxHeap.add(num);
        }
        int count = 0;
        while (maxHeap.size() > 0) {
            int knum = maxHeap.poll();
            count++;
            if (count == k) return knum;
        }
        return -1;
    }

    //35 easy -> binary search insert position
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length -1;
        int mid = (end - start) / 2;
        while (start <= end) {
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
             mid = (end - start) / 2 + start;
        }
        return start;
    }

    //28 easy -> Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
    public int strStr(String haystack, String needle) {
        if (needle.length() > haystack.length()) return -1;
        int h = 0, n = 0;
        while (h <= haystack.length() - needle.length()) {
            while (haystack.charAt(h + n) == needle.charAt(n)) {
                n++;
                if (n == needle.length()) return h;
            }
            h++;
            n = 0;
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int strStr3(String haystack, String needle) {
        int start = 0;
        int end = needle.length();
        while (end <= haystack.length()) {
            String substr = haystack.substring(start, end);
            if (substr.equals(needle)) return start;
            start++;
            end++;
        }
        return -1;
    }

    //14 easy -> longest common prefix
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        int len = prefix.length();
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            // len = str.length() < len ? str.length() : len;
            // prefix = prefix.substring(0, len);
            if (str.length() < len) {
                len = str.length();
                prefix = prefix.substring(0, len);
            }
            while (!str.substring(0, len).equals(prefix)) {
                len--;
                if (len == 0) return "";
                prefix = prefix.substring(0, len);
            }
        }
        return prefix;
    }
    //2001 medium -> number of pairs of interchangeable rectangles
    public long interchangeableRectangles(int[][] rectangles) {
        long count = 0;
        Map<Double, Long> hm = new HashMap<>();
        for (int[] rec : rectangles) {
            double ratio = (double) rec[0] / rec[1];
            count += hm.getOrDefault(ratio, 0L);
            hm.put(ratio, hm.getOrDefault(ratio, 0L) + 1);
        }
        return count;
    }

    //55 medium -> Jump Game
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;
        int run = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > run) return false;
            run = Math.max(run, i + nums[i]);
        }
        return true;
    }

    //45 medium -> Jump Game II
    public int jumpII(int[] nums) {
        if (nums.length <= 1) return 0;
        int jumps = 0, currEnd = 0, currFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            currFarthest = Math.max(currFarthest, i + nums[i]);
            if (i == currEnd) {
                jumps++;
                currEnd = currFarthest;
            }
        }
        return jumps;
    }

    //67 easy -> add binary
    public String addBinary(String a, String b) {
        int idxa = a.length() - 1;
        int idxb = b.length() - 1;
        int carry = 0;
        String result = "";
        while (idxa >= 0 || idxb >= 0 || carry == 1) {
            if (idxa >= 0) carry += a.charAt(idxa--) - '0';
            if (idxb >= 0) carry += b.charAt(idxb--) - '0';
            result = String.valueOf(carry % 2) + result;
            carry /= 2;
        }
        return result;
    }
    //151 medium -> reverse words in a string
    public String reverseWords(String s) {
        String[] strArr = s.trim().split(" ");
        String reverse = "";
        for (String str : strArr) {
            if (!str.equals("")) reverse = str + " " + reverse;
        }
        return reverse.trim();
        // return Arrays.asList(s.split(" ")).reversed().stream().filter(e -> !e.equals("")).collect(Collectors.joining(" "));
    }

    //274 medium -> H-index
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int count = 1;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= count) count++;
        }
        return count - 1;
    }
    //1768 easy ->Merge String Alternately
    public String mergeAlternately(String word1, String word2) {
        String merge = "";
        int len1 = 0;
        int len2 = 0;
        while (len1 < word1.length() && len2 < word2.length()) {
            merge += String.valueOf(word1.charAt(len1++)) + String.valueOf(word2.charAt(len2++));
        }
        if (len1 < word1.length()) merge += word1.substring(len1, word1.length());
        if (len2 < word2.length()) merge += word2.substring(len2, word2.length());
        return merge;
    }
    public String mergeAlternately2(String word1, String word2) {
        StringBuilder merge = new StringBuilder();
        int maxLen = Math.max(word1.length(), word2.length());
        for (int i = 0; i < maxLen; i++) {
            if (i < word1.length()) merge.append(word1.charAt(i));
            if (i < word2.length()) merge.append(word2.charAt(i));
        }
        return merge.toString();
    }
    //66 easy -> plus one
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
    //1071 easy -> greatest common divisor of string
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        int gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);
    }
    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
    public String gcdOfStrings2(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        String divisor = "", temp = "";
        int len1 = str1.length(), len2 = str2.length();
        int minLen = len1 > len2 ? len2 : len1;
        for (int i = 0; i < minLen; i++) {
            temp += str1.charAt(i);
            int len = temp.length();
            if (len1 % len == 0 && temp.repeat(len2 / len).equals(str2)) {
                divisor = temp;
            }
        }
        return divisor;
    }
    //1431 super easy -> kids with the greatesr number of candies
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        int max = 0;
        for (int candy : candies) {
            max = Math.max(candy, max);
        }
        for (int num : candies) {
            result.add(num + extraCandies >= max);
        }
        return result;
    }
    //605 easy -> can place flowers
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) &&
            (i == flowerbed.length - 1 || flowerbed[i+1] == 0)) {
                n--;
                flowerbed[i] = 1;
                if (n == 0) return true;
            }
        }
        return false;
    }
    //69 sqrt(x)
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        //using binary search
        int start = 1, mid = 1, end = x;
        while (start <= end) {
            mid = (end - start) / 2 + start;
            long sq = (long) mid * mid;
            if (sq == x) return mid;
            else if (sq < x) start = mid + 1;
            else end = mid - 1;
        }
        return end;
    }

    //238 medium -> Product of array except self
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;
        //traverse from left to right:
        int curr = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result[i] = curr;
            curr *= nums[i];
        }
        //traverse from right to left:
        curr = nums[nums.length - 1];
        for (int j = nums.length - 2; j >= 0; j--) {
            result[j] *= curr;
            curr *= nums[j];
        }
        return result;
    }
    //134 medium -> gas station
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) return -1;
        int totalGasLeft = 0, currGasLeft = 0, startStation = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGasLeft += gas[i] - cost[i];
            currGasLeft += gas[i] - cost[i];
            if (currGasLeft < 0) {
                startStation = i + 1;
                currGasLeft = 0;
            }
        }
        return totalGasLeft >= 0 ? startStation : -1;
    }
    //6 medium -> zigZag conversion
    public String convert(String s, int numRows) {
        if (s.length() <= 1 || numRows >= s.length()) return s;
        Map<Integer, String> map = new HashMap<>();
        int row = 1;
        boolean status = false;
        for (int i = 0; i < s.length(); i++) {
            String ch = String.valueOf(s.charAt(i));
            map.put(row, map.getOrDefault(row, "") + ch);
            row += status ? -1 : 1;
            if (row == 1) status = false;
            else if (row == numRows) status = true;
        }

        StringBuilder result = new StringBuilder();
        for (String str : map.values()) {
          result.append(str);
        }
        return result.toString();
    }

    public String convert2(String s, int numRows) {
        if (s.length() <= 1 || numRows == 1 || numRows >= s.length()) return s;
        StringBuilder[] zags = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            zags[i] = new StringBuilder();
        }
        int row = 0;
        boolean status = false;
        for (int i = 0; i < s.length(); i++) {
            zags[row].append(s.charAt(i));
            row += status ? -1 : 1;
            if (row == 0) status = false;
            else if (row + 1 == numRows) status = true;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder str : zags) {
            res.append(str);
        }
        return res.toString();
    }

    //56 medium -> merge intervals
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<int[]> merges = new ArrayList<>();
        int[] curr = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (curr[1] >= intervals[i][0]) {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                merges.add(curr);
                curr = intervals[i];
            }
        }
        merges.add(curr);
        return merges.toArray(new int[merges.size()][2]);
    }

    //57 medium -> Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};

        int[][] temp = new int[intervals.length + 1][2];
        List<int[]> res = new ArrayList<>();

        //insert newInterval into temp matrix:
        boolean add = true;
        int idx = 0;
        for (int i = 0; i < temp.length; i++) {
            if (add && idx < intervals.length && newInterval[0] <= intervals[idx][0]) {
                temp[i] = newInterval;
                add = false;
            } else if (idx < intervals.length){
                temp[i] = intervals[idx++];
            }
        }
        if(temp[temp.length - 1][0] == 0) temp[temp.length - 1] = newInterval;

        //merge overlap array:
        int[] curr = temp[0];
        for (int j = 1; j < temp.length; j++) {
            if (curr[1] < temp[j][0]) {
                res.add(curr);
                curr = temp[j];
            } else {
                curr[1] = Math.max(curr[1], temp[j][1]);
            }
        }
        res.add(curr);

        return res.toArray(new int[res.size()][2]);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};
        List<int[]> res = new ArrayList<>();
        boolean added = false;
        for (int i = 0; i < intervals.length; i++) {
            int maxStart = Math.max(intervals[i][0], newInterval[0]);
            int minEnd = Math.min(intervals[i][1], newInterval[1]);
            if (maxStart > minEnd) {
                if (newInterval[1] < intervals[i][1] && added == false) {
                    res.add(newInterval);
                    added = true;
                }
                res.add(intervals[i]);
            } else { //maxStart > minEnd, has overlap
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }
        if (added == false) res.add(newInterval);
        return res.toArray(new int[res.size()][2]);
    }

    //452 medium -> Minimum number of arrows to burst balloons
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int xend = points[0][1];
        int arrows = 1;
        for (int i = 1; i < points.length; i++) {
            if (xend < points[i][0]) {
                arrows++;
                xend = points[i][1];
            }
            xend = Math.min(xend, points[i][1]);
        }
        return arrows;
    }
    public int findMinArrowShots2(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int[] curr = points[0];
        int arrows = 1;
        for (int i = 1; i < points.length; i++) {
            if (curr[1] >= points[i][0]) {
                curr[0] = Math.max(curr[0], points[i][0]);
                curr[1] = Math.min(curr[1], points[i][1]);
            } else {
                arrows++;
                curr = points[i];
            }
        }
        return arrows;
    }
    //678 medium -> valid parenthesis string
    public boolean checkValidString(String s) {
        int min = 0; //The minimum demand of ')'
        int max = 0; //The maximum demand of ')'
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                min++;
                max++;
            } else if (ch == ')') {
                if (min > 0) min--;
                max--;
            } else if (ch == '*') {
                if (min > 0) min--; // let '*' as ')'
                max++; // let '*' as '('
            }
            if (max < 0) return false;
        }
        return min == 0;
    }

    public boolean checkValidString2(String s) {
        int stars = 0, opens = 0, closes = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') opens++;
            else if (ch == ')') closes++;
            else stars++;
            if (closes > opens) {
                if (stars == 0) return false;
                stars--;
                closes--;
            }
        }
        stars = opens = closes = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') opens++;
            else if (ch == ')') closes++;
            else stars++;
            if (opens > closes) {
                if (stars == 0) return false;
                stars--;
                opens--;
            }
        }
        return true;
    }

    //918 medium -> maximum sum circular subarray
    //Kadane's algorithm
    public int maxSubarraySumCircular(int[] nums) {
        if (nums.length == 0) return 0;
        int maxGlobalSum = nums[0];
        int maxCurrSum = nums[0];
        int minGlobalSum = nums[0];
        int minCurrSum = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxCurrSum = Math.max(nums[i], nums[i] + maxCurrSum);
            maxGlobalSum = Math.max(maxCurrSum, maxGlobalSum);
            minCurrSum = Math.min(nums[i], nums[i] + minCurrSum);
            minGlobalSum = Math.min(minCurrSum, minGlobalSum);
            sum += nums[i];
        }
        if (sum == minGlobalSum) return maxGlobalSum;
        return Math.max(maxGlobalSum, sum - minGlobalSum);
    }

    //162 medium -> find peek element
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        int start = 0;
        int end = nums.length - 1;
        if (nums[start] > nums[start + 1]) return start;
        if (nums[end] > nums[end - 1]) return end;
        start += 1;
        end -= 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid-1] < nums[mid] && nums[mid] > nums[mid+1]) return mid;
            else if (nums[mid-1] > nums[mid]) end = mid - 1;
            else if (nums[mid+1] > nums[mid]) start = mid + 1;
        }
        return -1;
    }

    public int findPeakElement2(int[] nums) {
        if (nums.length == 1) return 0;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int prev = mid - 1 >= 0 ? nums[mid-1] : Integer.MIN_VALUE;
            int after = mid + 1 < nums.length ? nums[mid+1] : Integer.MIN_VALUE;
            int curr = nums[mid];
            if (prev < curr && curr > after) return mid;
            else if (prev > curr) end = mid - 1;
            else if (after > curr) start = mid + 1;
        }
        return -1;
    }

    //17 letters combination of phone number
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        String[] letterCombs = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        for(int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            while (queue.peek().length() == i) {
                String letters = letterCombs[digit];
                String str = queue.poll();
                for (char letter : letters.toCharArray()) {
                    queue.offer(str + letter);
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            result.add(queue.poll());
        }
        return result;
    }
    // way2: recursive
    List<String> combination = new ArrayList<>();
    public List<String> letterCombinations2(String digits) {
        String[] letterCombs = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits.length() > 0) combinationHelper(letterCombs, digits, 0, "");
        return combination;
    }
    private void combinationHelper(String[] letterCombs, String digits, int idx, String str) {
        if (str.length() == digits.length()) {
            combination.add(str);
            return;
        }
        int digit = digits.charAt(idx) - '0';
        String letters = letterCombs[digit];
        for (int i = 0; i < letters.length(); i++) {
            combinationHelper(letterCombs, digits, idx + 1, str + letters.charAt(i));
        }
    }

    //172 medium -> Factorial trailing zeroes
    public int trailingZeroes(int n) { //T: O(logn)
        // int zero = 0;
        // while (n > 0) {
        //     n /= 5;
        //     zero += n;
        // }
        // return zero;
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
    /*trailing zeroes in the factorial of a number are caused by the multiplication of 2 and 5,
    and since there are usually more factors of 2 than 5,
    the number of factors of 5 determines the number of trailing zeroes. */

    //149 hard -> Max points on a line
    public int maxPoints(int[][] points) {
        int len = points.length;
        if (len <= 2) return len;
        int finaMax = 1;

        for (int i = 0; i < len; i++) {
            HashMap<Double, Integer> slopeMap = new HashMap<>();
            int samePoints = 0;
            int currMax = 0;
            for (int j = i + 1; j < len; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 && y1 == y2) {
                    samePoints++;
                    continue;
                }
                double slope = x1 == x2 ? Double.POSITIVE_INFINITY : y1 == y2 ? 0 : ((double) (y2 - y1)) / (x2 - x1);
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                currMax = Math.max(currMax, slopeMap.get(slope));
            }
            finaMax = Math.max(finaMax, currMax + samePoints + 1);
        }
        return finaMax;
    }

    //34 medium -> binary search: find first and last element position in sorted array
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[] {-1, -1};
        int left = findPosition(nums, target, true);
        int right = findPosition(nums, target, false);
        return new int[] {left, right};
    }
    private int findPosition(int[] nums, int target, boolean isFindLeft) {
        int l = 0, r = nums.length -1, idx = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] > target) r = m - 1;
            else if (nums[m] < target) l = m + 1;
            else {
                idx = m;
                if (isFindLeft) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }
        return idx;
    }

    //153 medium -> binary search:find minimum in rotated sorted array
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        if (nums[left] <= nums[right]) return nums[left];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[left] <= nums[right] ? nums[left] : nums[right];
    }

    public int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] <= nums[right]) return nums[left];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] > nums[mid]) {
                if (nums[mid] > nums[mid - 1]) {
                    right = mid - 1;
                } else {
                    return nums[mid];
                }
            }  else {
                left = mid + 1;
                if (nums[mid] > nums[left]) return nums[left];
            }
        }
        return -1;
    }

    //4 hard -> median of two sorted arrays
    //time: O(log(min(m, n)))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // Ensure nums1 is the smaller array
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = m;
        int halfLen = (m + n + 1) / 2;

        while (left <= right) {
            int partitionX = left + (right - left) / 2;
            int partitionY = halfLen - partitionX;

            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
                } else {
                    return Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }
        throw new IllegalArgumentException();
    }

    //345 easy -> reverse vowels in a string
    public String reverseVowels(String s) {
        char[] charStr = s.toCharArray();
        String vowels = "aeiouAEIOU";
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && vowels.indexOf(charStr[left]) == -1) {
                left++;
            }
            while (left < right &&vowels.indexOf(charStr[right]) == -1) {
                right--;
            }
            char temp = charStr[left];
            charStr[left] = charStr[right];
            charStr[right] = temp;
            left++;
            right--;
        }
        return new String(charStr);
    }

    public String reverseVowels2(String s) {
        char[] charStr = s.toCharArray();
        // Set<Character> set = new HashSet<>(Arrays.asList('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'));
        Set<Character> set = Set.of('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U');
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !set.contains(charStr[left])) {
                left++;
            }
            while (left < right && !set.contains(charStr[right])) {
                right--;
            }
            char temp = charStr[left];
            charStr[left] = charStr[right];
            charStr[right] = temp;
            left++;
            right--;
        }
        return new String(charStr);
    }

    //643 easy -> maximum average subarray I
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0, currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                sum += nums[i];
                currSum = sum;
            } else {
                currSum += nums[i] - nums[i - k];
                sum = Math.max(sum, currSum);
            }
        }
        return (double) sum / k;
    }

    //520 easy -> detect capital
    public boolean detectCapitalUse(String word) {
        if (word.length() <= 1) return true;
        boolean firIsUpper = Character.isUpperCase(word.charAt(0));
        boolean secIsUpper = Character.isUpperCase(word.charAt(1));

        if (word.length() == 2) {
            if (!firIsUpper && secIsUpper) return false;
        }

        for (int i = 2; i < word.length(); i++) {
            if ((firIsUpper && secIsUpper && Character.isLowerCase(word.charAt(i)))
            || (firIsUpper && !secIsUpper && Character.isUpperCase(word.charAt(i)))
            || (!firIsUpper && (secIsUpper || Character.isUpperCase(word.charAt(i))))) {
                return false;
            }
        }

        return true;
    }
    /*Python solution:
     * def detectCapitalUse(self, word: str) -> bool:
        if word.isupper() or word.islower() or word.title() == word:
            return True
        else:
            return False
     */

    //334 medium -> Increasing Triplet Subsequence
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int secMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) {
                min = num;
            } else if (num <= secMin) {
                secMin = num;
            } else if (num > secMin) {
                return true;
            }
        }
        return false;
    }
    //443 medium -> String Compression
    public int compress(char[] chars) {
        char curr = chars[0];
        int idx = 0, count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == curr) {
                count++;
            } else {
                chars[idx++] = curr;
                if (count != 1) {
                    char[] counts = Integer.toString(count).toCharArray();
                    for (char ch : counts) {
                        chars[idx++] = ch;
                    }
                }
                curr = chars[i];
                count = 1;
            }
        }
        chars[idx++] = curr;
        if (count != 1) {
            char[] counts = Integer.toString(count).toCharArray();
            for (char ch : counts) {
                chars[idx++] = ch;
            }
        }
        return idx;
    }

    //1679 medium -> max number of k-sum pairs
    public int maxOperations(int[] nums, int k) {
        int left = 0, right = nums.length - 1, count = 0;
        Arrays.sort(nums);
        while (left < right) {
            if (nums[left] + nums[right] == k) {
                left++;
                right--;
                count++;
            } else if (nums[left] + nums[right] > k) {
                right--;
            } else {
                left++;
            }
        }
        return count;
    }
}
