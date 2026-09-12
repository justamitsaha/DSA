package leetcode.P_DynamicPrograming;

/**
 * LeetCode 746: Min Cost Climbing Stairs
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/min-cost-climbing-stairs/
 *
 * Algorithm:
 * - Dynamic Programming (Rolling Minimum Cost - O(n) Time, O(1) Space)
 *
 * Concepts:
 * - Given an array `cost` where `cost[i]` is the cost of the `i`th step on a staircase.
 * - Once you pay the cost, you can climb 1 or 2 steps. You may begin at step 0 or step 1.
 * - Return the minimum cost to reach the top of the floor (step `n`).
 * - Recurrence Relation:
 *   - Let `dp[i]` be the minimum cost to reach step `i`:
 *     `dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])`
 *   - Base cases: `dp[0] = 0`, `dp[1] = 0` (starting at step 0 or 1 is free).
 * - Optimization:
 *   - Only the previous two values (`prev1`, `prev2`) are needed, reducing space to O(1).
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through cost array.
 * - Space Complexity: O(1) - Two rolling variables.
 */
public class LC0746_MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length < 2) {
            return 0;
        }

        int prev2 = 0; // min cost to reach step 0
        int prev1 = 0; // min cost to reach step 1

        for (int i = 2; i <= cost.length; i++) {
            int current = Math.min(prev1 + cost[i - 1], prev2 + cost[i - 2]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        LC0746_MinCostClimbingStairs solver = new LC0746_MinCostClimbingStairs();

        // Scenario 1: [10, 15, 20] -> 15 (start at index 1, jump 2 steps to top)
        int[] cost1 = {10, 15, 20};
        System.out.println("Cost [10, 15, 20]: " + solver.minCostClimbingStairs(cost1) + " (Expected: 15)");

        // Scenario 2: [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] -> 6
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println("Cost [1, 100, 1, ...]: " + solver.minCostClimbingStairs(cost2) + " (Expected: 6)");
    }
}
