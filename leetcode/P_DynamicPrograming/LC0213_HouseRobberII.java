package leetcode.P_DynamicPrograming;

/**
 * LeetCode 213: House Robber II
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/house-robber-ii/
 *
 * Algorithm:
 * - Dynamic Programming on Circular Array (Two Linear Subproblem Split)
 *
 * Concepts:
 * - All houses at this place are arranged in a CIRCLE.
 * - This means the first house is the neighbor of the last one!
 * - Crucial Insight:
 *   - The first house and the last house CANNOT both be robbed together.
 *   - Therefore, the problem reduces into finding the maximum of two simple linear House Robber I subproblems:
 *     1. Case 1: Rob houses from index `0` to `n - 2` (ignoring the last house).
 *     2. Case 2: Rob houses from index `1` to `n - 1` (ignoring the first house).
 *   - Final result is `max(case1, case2)`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Two linear passes over the array.
 * - Space Complexity: O(1) - Constant auxiliary space.
 */
public class LC0213_HouseRobberII {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int n = nums.length;
        // Option 1: Rob from house 0 to n - 2
        int option1 = robLinear(nums, 0, n - 2);
        // Option 2: Rob from house 1 to n - 1
        int option2 = robLinear(nums, 1, n - 1);

        return Math.max(option1, option2);
    }

    private int robLinear(int[] nums, int start, int end) {
        int robPrev2 = 0;
        int robPrev1 = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(robPrev1, robPrev2 + nums[i]);
            robPrev2 = robPrev1;
            robPrev1 = current;
        }

        return robPrev1;
    }

    public static void main(String[] args) {
        LC0213_HouseRobberII solver = new LC0213_HouseRobberII();

        // Scenario 1: [2, 3, 2] -> 3 (cannot rob house 0 and house 2 together)
        int[] nums1 = {2, 3, 2};
        System.out.println("Rob circle [2, 3, 2]: " + solver.rob(nums1) + " (Expected: 3)");

        // Scenario 2: [1, 2, 3, 1] -> 4 (rob house 1 and 3: 1 + 3 = 4)
        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Rob circle [1, 2, 3, 1]: " + solver.rob(nums2) + " (Expected: 4)");

        // Scenario 3: [1, 2, 3] -> 3
        int[] nums3 = {1, 2, 3};
        System.out.println("Rob circle [1, 2, 3]: " + solver.rob(nums3) + " (Expected: 3)");
    }
}
