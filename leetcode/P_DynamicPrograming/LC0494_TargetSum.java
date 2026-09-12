package leetcode.P_DynamicPrograming;

/**
 * LeetCode 494: Target Sum
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/target-sum/
 *
 * Algorithm:
 * - Method 1: Mathematical Reduction to 0/1 Knapsack Subset Sum (O(n * S) Time, O(S) Space)
 * - Method 2: 2D Memoized Recursion / DFS
 *
 * Concepts:
 * - Assign '+' or '-' to each number in `nums` such that the sum equals `target`.
 * - Mathematical Reduction to Subset Sum:
 *   - Partition `nums` into positive subset `P` and negative subset `N`:
 *     1. `sum(P) - sum(N) = target`
 *     2. `sum(P) + sum(N) = totalSum`
 *   - Adding both equations:
 *     `2 * sum(P) = target + totalSum`  ===>  `sum(P) = (target + totalSum) / 2`
 *   - Necessary validity conditions:
 *     1. `target + totalSum` must be non-negative.
 *     2. `target + totalSum` must be EVEN (divisible by 2).
 *   - If valid, the problem becomes: Count the number of subsets with sum equal to `s = (target + totalSum) / 2`!
 *
 * Complexity:
 * - Time Complexity:  O(n * s) - Where s = (target + totalSum) / 2.
 * - Space Complexity: O(s)     - 1D DP array of size s + 1.
 */
public class LC0494_TargetSum {

    /**
     * Optimal 1D Subset Sum DP.
     */
    public int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Validity check: target must be reachable and sum must be non-negative and even
        if (Math.abs(target) > totalSum || (target + totalSum) % 2 != 0) {
            return 0;
        }

        int subsetSum = (target + totalSum) / 2;
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1; // 1 way to form sum 0 (empty subset)

        for (int num : nums) {
            for (int s = subsetSum; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }

        return dp[subsetSum];
    }

    public static void main(String[] args) {
        LC0494_TargetSum solver = new LC0494_TargetSum();

        // Scenario 1: [1, 1, 1, 1, 1], target = 3 -> 5 ways
        int[] nums1 = {1, 1, 1, 1, 1};
        System.out.println("Target 3 with [1, 1, 1, 1, 1]: " + solver.findTargetSumWays(nums1, 3) + " (Expected: 5)");

        // Scenario 2: [1], target = 1 -> 1 way (+1)
        int[] nums2 = {1};
        System.out.println("Target 1 with [1]: " + solver.findTargetSumWays(nums2, 1) + " (Expected: 1)");

        // Scenario 3: Target larger than sum -> 0
        int[] nums3 = {1};
        System.out.println("Target 2 with [1]: " + solver.findTargetSumWays(nums3, 2) + " (Expected: 0)");

        // Scenario 4: Elements containing zeros [0, 0, 0, 0, 0, 0, 0, 0, 1], target = 1
        int[] nums4 = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        System.out.println("Zeros with target 1: " + solver.findTargetSumWays(nums4, 1) + " (Expected: 256)");
    }
}
