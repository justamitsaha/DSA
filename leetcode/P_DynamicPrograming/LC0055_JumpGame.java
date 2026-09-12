package leetcode.P_DynamicPrograming;

/**
 * LeetCode 55: Jump Game
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/jump-game/
 *
 * Algorithm:
 * - Method 1: Forward Greedy Reachability Horizon (Optimal O(n) Time, O(1) Space)
 * - Method 2: Backward Target Shift (DP / Greedy Verification)
 *
 * Concepts:
 * - You are given an integer array `nums` where `nums[i]` is the maximum jump length from index `i`.
 * - Determine if you can reach the last index (`nums.length - 1`).
 * - Forward Reachability:
 *   - Maintain `maxReach`, the furthest index reachable from any previously visited position.
 *   - At each index `i`:
 *     - If `i > maxReach`, the current index is stranded and unreachable -> return false.
 *     - Update `maxReach = max(maxReach, i + nums[i])`.
 *     - If `maxReach >= nums.length - 1`, we can already reach the end -> return true early!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the array.
 * - Space Complexity: O(1) - Constant auxiliary space.
 */
public class LC0055_JumpGame {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int maxReach = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false; // Cannot bridge this gap
            }

            maxReach = Math.max(maxReach, i + nums[i]);

            if (maxReach >= n - 1) {
                return true; // Already can reach the last index
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LC0055_JumpGame solver = new LC0055_JumpGame();

        // Scenario 1: [2, 3, 1, 1, 4] -> true (jump 1 step to 1, then 3 steps to last)
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump [2, 3, 1, 1, 4]: " + solver.canJump(nums1) + " (Expected: true)");

        // Scenario 2: [3, 2, 1, 0, 4] -> false (always arrive at index 3 with 0 jump length)
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("Can jump [3, 2, 1, 0, 4]: " + solver.canJump(nums2) + " (Expected: false)");

        // Scenario 3: Single element [0] -> true (already at last index)
        int[] nums3 = {0};
        System.out.println("Can jump [0]: " + solver.canJump(nums3) + " (Expected: true)");
    }
}
