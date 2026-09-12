package leetcode.P_DynamicPrograming;

/**
 * LeetCode 198: House Robber
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/house-robber/
 *
 * Algorithm:
 * - Dynamic Programming (Non-Adjacent Maximum Subsequence Sum - O(n) Time, O(1) Space)
 *
 * Concepts:
 * - Determine the maximum amount of money you can rob tonight without robbing adjacent houses.
 * - At house `i`, the robber has two choices:
 *   1. Rob house `i`: Gain `nums[i]` plus the maximum robbed up to house `i - 2` (`robPrev2 + nums[i]`).
 *   2. Do not rob house `i`: Retain the maximum robbed up to house `i - 1` (`robPrev1`).
 * - Recurrence:
 *   `current = max(robPrev1, robPrev2 + nums[i])`
 * - Space Optimization:
 *   - Only two variables (`robPrev1` and `robPrev2`) are required to track previous states.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through nums array.
 * - Space Complexity: O(1) - Constant auxiliary space.
 */
public class LC0198_HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int robPrev2 = 0; // max money robbing up to house i - 2
        int robPrev1 = 0; // max money robbing up to house i - 1

        for (int num : nums) {
            int current = Math.max(robPrev1, robPrev2 + num);
            robPrev2 = robPrev1;
            robPrev1 = current;
        }

        return robPrev1;
    }

    public static void main(String[] args) {
        LC0198_HouseRobber solver = new LC0198_HouseRobber();

        // Scenario 1: [1, 2, 3, 1] -> 4 (rob house 1 and 3: 1 + 3 = 4)
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Rob [1, 2, 3, 1]: " + solver.rob(nums1) + " (Expected: 4)");

        // Scenario 2: [2, 7, 9, 3, 1] -> 12 (rob house 1, 3, 5: 2 + 9 + 1 = 12)
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("Rob [2, 7, 9, 3, 1]: " + solver.rob(nums2) + " (Expected: 12)");

        // Scenario 3: Single house [5] -> 5
        int[] nums3 = {5};
        System.out.println("Rob [5]: " + solver.rob(nums3) + " (Expected: 5)");

        // Scenario 4: Two houses [2, 3] -> 3
        int[] nums4 = {2, 3};
        System.out.println("Rob [2, 3]: " + solver.rob(nums4) + " (Expected: 3)");
    }
}
