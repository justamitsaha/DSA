package leetcode.A_Array;

import java.util.Arrays;

/**
 * LeetCode 53: Maximum Subarray
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-subarray/
 *
 * Problem:
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Concepts: Kadane's Algorithm, Dynamic Programming
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class LC0053_MaximumSubarray {

    /**
     * Kadane's Algorithm:
     * Keep track of current sum. If currentSum becomes negative, reset it to 0
     * because a negative prefix never helps maximize future subarray sums.
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = 0;
            }
            currentSum = currentSum + nums[i];
            if (currentSum > maxSoFar) {
                maxSoFar = currentSum;
            }
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        LC0053_MaximumSubarray solver = new LC0053_MaximumSubarray();

        // Scenario 1: Standard mixed array
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Scenario 1 - Standard:     " + Arrays.toString(nums1));
        System.out.println("Max Subarray Sum:          " + solver.maxSubArray(nums1) + " | Expected: 6 ([4, -1, 2, 1])\n");

        // Scenario 2: Single positive element
        int[] nums2 = {1};
        System.out.println("Scenario 2 - Single Value: " + solver.maxSubArray(nums2) + " | Expected: 1\n");

        // Scenario 3: All positive values
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println("Scenario 3 - Mostly Pos:   " + solver.maxSubArray(nums3) + " | Expected: 23\n");

        // Scenario 4: All negative numbers (must pick the least negative number)
        int[] nums4 = {-5, -3, -2, -8};
        System.out.println("Scenario 4 - All Negative: " + solver.maxSubArray(nums4) + " | Expected: -2");
    }
}
