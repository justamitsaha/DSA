package leetcode.P_DynamicPrograming;

/**
 * LeetCode 70: Climbing Stairs
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/climbing-stairs/
 *
 * Algorithm:
 * - Dynamic Programming (Fibonacci Sequence Optimization - O(n) Time, O(1) Space)
 *
 * Concepts:
 * - You are climbing a staircase. It takes `n` steps to reach the top.
 * - Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * - Recurrence Relation:
 *   - To reach step `i`, you must come from step `i - 1` (1 step) or step `i - 2` (2 steps).
 *   - `dp[i] = dp[i - 1] + dp[i - 2]`.
 *   - Base cases: `dp[1] = 1`, `dp[2] = 2`.
 * - Space Optimization:
 *   - Since computing `dp[i]` only depends on the previous two states, we only need two rolling variables.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single loop up to n.
 * - Space Complexity: O(1) - Constant auxiliary space.
 */
public class LC0070_ClimbingStairs {

    /**
     * Space-optimized O(1) rolling variables.
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1; // ways to reach step 1
        int prev1 = 2; // ways to reach step 2

        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    /**
     * Tabulation with O(n) DP array.
     */
    public int climbStairsArray(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        LC0070_ClimbingStairs solver = new LC0070_ClimbingStairs();

        // Scenario 1: n = 2 -> 2 ways (1+1, 2)
        System.out.println("Climb 2 stairs: " + solver.climbStairs(2) + " (Expected: 2)");

        // Scenario 2: n = 3 -> 3 ways (1+1+1, 1+2, 2+1)
        System.out.println("Climb 3 stairs: " + solver.climbStairs(3) + " (Expected: 3)");

        // Scenario 3: n = 5 -> 8 ways
        System.out.println("Climb 5 stairs: " + solver.climbStairs(5) + " (Expected: 8)");

        // Scenario 4: n = 1 -> 1 way
        System.out.println("Climb 1 stair: " + solver.climbStairs(1) + " (Expected: 1)");
    }
}
