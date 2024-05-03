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
}
