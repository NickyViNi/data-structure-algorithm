package dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DynamicProgramming {
    //70 easy -> Climbing Stairs
    /*You are climbing a staircase. It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top? */
    public int climbStairs(int n) {
        if (n <= 3) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs2(int n) {
        if (n <= 3) return n;
        int prev2 = 2, prev1 = 3, curr = 3;
        for (int i = 4; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    //198 medium -> house robber
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        if (nums.length == 2) return dp[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }
    // sums: [2,7,9,3,1], dp: [2,7,11,11,12]

    //139 medium -> word break
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) { // from i = 1 and i == s.length() due to s.substring()
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    //120 medium -> triangle
    //way 1:
    public int minimumTotal1(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0 ; i--) {
            List<Integer> curr = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                int left = triangle.get(i + 1).get(j);
                int right = triangle.get(i + 1).get(j + 1);
                curr.set(j, Math.min(left, right) + curr.get(j));
            }
        }
        return triangle.get(0).get(0);
    }

    //way 2: Memoization - best runtime 99.32%
    Integer memo[][];
    public int minimumTotal(List<List<Integer>> triangle) {

        int len = triangle.size();
        memo = new Integer[len][len];
        return FindMinimum(triangle, 0 , 0, len);

    }
    private int FindMinimum(List<List<Integer>> triangle, int row, int col, int len){
        System.out.println(row + " - " + col);
        if(row == len - 1) return triangle.get(row).get(col);

        if(memo[row][col] != null) return memo[row][col];

        int down = triangle.get(row).get(col) + FindMinimum(triangle, row + 1, col, len);
        int downPlusOne = triangle.get(row).get(col) + FindMinimum(triangle, row + 1, col + 1, len);
        System.out.println("memo: " + down + " - " + downPlusOne);
        return memo[row][col] = Math.min(down, downPlusOne);
    }

    //way3: Tabulation
    public int minimumTotal3(List<List<Integer>> triangle) {

        int len = triangle.size();
        Integer[][] dp_array = new Integer[len][len];

        dp_array[0][0] = triangle.get(0).get(0);

        for(int i = 1 ; i < len ; i++){
            for(int j = 0 ; j <= i ; j++){

                if(j == 0){
                    dp_array[i][0] = triangle.get(i).get(0) + dp_array[i - 1][0]; // only up
                }else if(j == i){
                    dp_array[i][i] = triangle.get(i).get(i) + dp_array[i - 1][i - 1]; // only up - 1
                }else {
                    dp_array[i][j] = triangle.get(i).get(j) + Math.min(dp_array[i - 1][j], dp_array[i - 1][j - 1]); // min(up, up - 1);
                }
            }
        }

        int minPath = Integer.MAX_VALUE;
        for(int i = 0 ; i < len ; i++)
            minPath = Math.min(minPath, dp_array[len - 1][i]);

        return minPath;
    }

    //322 medium -> coin change
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) { //i means amount, keep minimum number of coins to make up each amout
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //300 medium -> Longest increasing subsequence
    //way1: binary search
    public int lengthOfLIS(int[] nums) {
        int[] sequence = new int[nums.length + 1]; // plus 1 bc sequence[0] will always be MAX_VALUE;
        Arrays.fill(sequence, Integer.MAX_VALUE);
        for (int num : nums) {
            int idx = binarySearch(sequence, num);
            sequence[idx] = num;
        }
        int len = 0;
        for (int e : sequence) {
            if (e != Integer.MAX_VALUE) len++;
        }
        return len;
    }
    private int binarySearch(int[] sequence, int target) {
        int start = 0, end = sequence.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (sequence[mid] == target) {
                return mid;
            } else if (sequence[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return end;
    }
    //way2: dp
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().orElse(0);
    }

    //746 easy -> Min Cost Climbing Stairs
    public int minCostClimbingStairs(int[] cost) {
        for (int i = cost.length - 3; i >= 0; i--) {
            cost[i] += Math.min(cost[i + 1], cost[i + 2]);
        }
        return Math.min(cost[0], cost[1]);
    }

    public int minCostClimbingStairs2(int[] cost) {
        int first = cost[0], second = cost[1];
        for (int i = 2; i < cost.length; i++) {
            int curr = cost[i] + Math.min(first, second);
            first = second;
            second = curr;
        }
        return Math.min(first, second);
    }

    public int minCostClimbingStairs3(int[] cost) {
        int len = cost.length;
        int[] stairsCost = new int[len];
        stairsCost[0] = cost[0];
        stairsCost[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            stairsCost[i] = cost[i] + Math.min(stairsCost[i - 1], stairsCost[i - 2]);
        }
        return Math.min(stairsCost[len - 1], stairsCost[len - 2]);
    }

    //1137 easy -> N-th Tribonacci Number
    public int tribonacci(int n) {
        if (n == 0) return 0;
        int first = 0, second = 1, third = 1;
        for (int i = 3; i <= n; i++) {
            int curr = first + second + third;
            first = second;
            second = third;
            third = curr;
        }
        return third;
    }
    public int tribonacci2(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        int[] tribo = new int[n + 1];
        tribo[1] = 1;
        tribo[2] = 1;
        for (int i = 3; i <= n; i++) {
            tribo[i] = tribo[i - 1] + tribo[i - 2] + tribo[i - 3];
        }
        return tribo[n];
    }

    //790 medium -> Domino and Tromino Tiling
    public int numTilings(int n) {
        long[] dp = new long[n + 3];
        dp[0] = 1; dp[1] = 2; dp[2] = 5;
        for (int i = 3; i < n; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % (1000000000 + 7);
        }
        return (int) dp[n - 1];
    }

    //338 medium -> Counting Bits
    //solution 1:
    //for even number, it's unit digit is 0; for odd number, it's unit digit is 1;
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
    //solution 2:
    public int[] countBits3(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = intToBit(i);
        }
        return ans;
    }
    private int intToBit(int num) {
        int numberOfOne = 0;
        while (num != 0) {
            numberOfOne += num & 1;
            num >>= 1;
        }
        return numberOfOne;
    }

    //62 medium -> unique paths
    public int uniquePaths(int m, int n) {
        int[] paths = new int[m];
        for (int i = 0; i < m; i++) {
            paths[i] = 1;
        }
        for (int col = 1; col < n; col++) {
            for (int row = 1; row < m; row++) {
                paths[row] += paths[row - 1];
            }
        }
        return paths[m - 1];
    }
    //m = 4, n = 5, result = 45
    /* edges only have one path
    1  1  1  1  1
    1  2  3  4  5
    1  3  6  10 15
    1  4  10 20 35
    */

    //1143 medium -> Longest Common Subsequence
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        char[] char1 = text1.toCharArray(), char2 = text2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; //diagonal value + 1
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // max value in left and above value
                }
            }
        }
        return dp[len1][len2];
    }

    //714 medium -> Best Time to Buy and Sell Stock with Transaction Fee
    public int maxProfit(int[] prices, int fee) {
        int bought = -prices[0], profit = 0;
        for (int i = 1; i < prices.length; i++) {
            bought = Math.max(bought, profit - prices[i]);
            profit = Math.max(profit, prices[i] + bought - fee);
        }
        return profit;
    }
}
