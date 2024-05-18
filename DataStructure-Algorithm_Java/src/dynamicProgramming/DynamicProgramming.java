package dynamicProgramming;

public class DynamicProgramming {
    //70 easy -> Climbing Stairs
    /*You are climbing a staircase. It takes n steps to reach the top.
    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top? */
    public int climbStairs(int n) {
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
}
