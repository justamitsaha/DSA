package leetcode.P_DynamicPrograming;

/**
 * LeetCode 416: Partition Equal Subset Sum
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * Algorithm:
 * - 0/1 Knapsack Dynamic Programming (1D Space-Optimized Boolean Subset Sum)
 *
 * Concepts:
 * - Given an array `nums`, determine if it can be partitioned into two subsets with equal sums.
 * - Reduction to 0/1 Knapsack:
 *   - Let `totalSum = sum(nums)`.
 *   - If `totalSum` is odd, equal integer partitioning is mathematically impossible -> return false immediately!
 *   - If `totalSum` is even, we need to find a subset that sums exactly to `target = totalSum / 2`.
 * - 1D DP Array:
 *   - `dp[t]` represents whether a subset summing to `t` is achievable.
 *   - Base Case: `dp[0] = true` (empty subset).
 *   - For each number `num` in `nums`:
 *     - Iterate `t` backwards from `target` down to `num`:
 *       `dp[t] = dp[t] || dp[t - num]`.
 *     - (Iterating backwards prevents using the same number more than once in the same phase).
 *
 * Complexity:
 * - Time Complexity:  O(n * target) - Where target = totalSum / 2.
 * - Space Complexity: O(target)     - 1D boolean array of size target + 1.
 */
public class LC0416_PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // An odd sum cannot be divided into two equal integers
        if (totalSum % 2 != 0) {
            return false;
        }

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int t = target; t >= num; t--) {
                dp[t] = dp[t] || dp[t - num];
            }
            if (dp[target]) {
                return true; // Early exit
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        LC0416_PartitionEqualSubsetSum solver = new LC0416_PartitionEqualSubsetSum();

        // Scenario 1: [1, 5, 11, 5] -> true (subsets [1, 5, 5] and [11])
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Can partition [1, 5, 11, 5]: " + solver.canPartition(nums1) + " (Expected: true)");

        // Scenario 2: [1, 2, 3, 5] -> false (total sum = 11, odd)
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Can partition [1, 2, 3, 5]: " + solver.canPartition(nums2) + " (Expected: false)");

        // Scenario 3: [1, 2, 5] -> false (even sum = 8, target = 4, but no subset sums to 4)
        int[] nums3 = {1, 2, 5};
        System.out.println("Can partition [1, 2, 5]: " + solver.canPartition(nums3) + " (Expected: false)");
    }
}
