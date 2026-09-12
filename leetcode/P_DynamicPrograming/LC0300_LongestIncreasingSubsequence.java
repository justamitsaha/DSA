package leetcode.P_DynamicPrograming;

import java.util.Arrays;

/**
 * LeetCode 300: Longest Increasing Subsequence
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Algorithm:
 * - Method 1: Patience Sorting with Binary Search (O(n log n) Time, O(n) Space - Optimal)
 * - Method 2: Classical Tabulation Dynamic Programming (O(n^2) Time, O(n) Space)
 *
 * Concepts:
 * - Given an integer array `nums`, return the length of the longest strictly increasing subsequence.
 * - Classical DP (O(n^2)):
 *   - Let `dp[i]` be the length of the LIS ending at index `i`.
 *   - `dp[i] = 1 + max(dp[j])` for all `0 <= j < i` such that `nums[j] < nums[i]`.
 * - Patience Sorting (O(n log n)):
 *   - Maintain an array `tails`, where `tails[len]` stores the smallest tail of all increasing subsequences of length `len + 1`.
 *   - For each number `x` in `nums`:
 *     - Binary search for the insertion point of `x` in `tails`.
 *     - If `x` is larger than all elements in `tails`, append `x` (extends the LIS length by 1).
 *     - Otherwise, replace the first element in `tails` that is `>= x` with `x` (greedy tightening).
 *   - The size of `tails` at the end is the exact length of the LIS!
 *
 * Complexity:
 * - Method 1: Time: O(n log n), Space: O(n)
 * - Method 2: Time: O(n^2),     Space: O(n)
 */
public class LC0300_LongestIncreasingSubsequence {

    /**
     * Optimal Patience Sorting with Binary Search (O(n log n)).
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] tails = new int[nums.length];
        int len = 0; // Current length of LIS

        for (int num : nums) {
            // Binary search for the lower bound of num in tails[0..len-1]
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            tails[left] = num;
            if (left == len) {
                len++; // Extended the longest increasing subsequence
            }
        }

        return len;
    }

    /**
     * Classical O(n^2) DP.
     */
    public int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLIS = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }

        return maxLIS;
    }

    public static void main(String[] args) {
        LC0300_LongestIncreasingSubsequence solver = new LC0300_LongestIncreasingSubsequence();

        // Scenario 1: [10, 9, 2, 5, 3, 7, 101, 18] -> 4 ([2, 3, 7, 101])
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS (Binary Search): " + solver.lengthOfLIS(nums1) + " (Expected: 4)");
        System.out.println("LIS (O(n^2) DP):     " + solver.lengthOfLIS_DP(nums1) + " (Expected: 4)");

        // Scenario 2: [0, 1, 0, 3, 2, 3] -> 4 ([0, 1, 2, 3])
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("LIS: " + solver.lengthOfLIS(nums2) + " (Expected: 4)");

        // Scenario 3: All equal elements [7, 7, 7, 7] -> 1
        int[] nums3 = {7, 7, 7, 7};
        System.out.println("LIS: " + solver.lengthOfLIS(nums3) + " (Expected: 1)");
    }
}
