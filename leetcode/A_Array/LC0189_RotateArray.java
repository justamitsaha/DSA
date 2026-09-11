package leetcode.A_Array;

import java.util.Arrays;

/**
 * LeetCode 189: Rotate Array
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/rotate-array/
 *
 * Algorithm:
 * - Three-Step Array Reversal Algorithm (In-Place)
 *
 * Mathematical Intuition:
 * - Rotating an array of size n to the right by k steps means the last k elements move to the front.
 * - If k >= n, rotating by k is equivalent to rotating by k % n.
 * - The Reversal Algorithm operates in 3 steps:
 *     1. Reverse the entire array:             [0, n - 1]
 *     2. Reverse the first k elements:         [0, k - 1]
 *     3. Reverse the remaining n - k elements: [k, n - 1]
 *
 * Example trace for nums = [1, 2, 3, 4, 5, 6, 7], k = 3:
 *   1. Reverse all:         [7, 6, 5, 4, 3, 2, 1]
 *   2. Reverse first 3:     [5, 6, 7, 4, 3, 2, 1]
 *   3. Reverse remaining 4: [5, 6, 7, 1, 2, 3, 4] -> Finished!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Each element is reversed at most twice.
 * - Space Complexity: O(1) - In-place modification without extra arrays.
 */
public class LC0189_RotateArray {

    /**
     * Rotates the array nums to the right by k steps in-place.
     *
     * @param nums array to rotate
     * @param k number of positions to rotate right
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int n = nums.length;
        k = k % n; // Handle k >= n

        if (k == 0) {
            return; // No rotation needed
        }

        // Step 1: Reverse the entire array
        reverse(nums, 0, n - 1);
        // Step 2: Reverse the first k elements
        reverse(nums, 0, k - 1);
        // Step 3: Reverse the remaining n - k elements
        reverse(nums, k, n - 1);
    }

    /**
     * Helper method to reverse a subarray between start and end indices using two pointers.
     */
    private void reverse(int[] nums, int start, int end) {
        int i = start;
        int j = end;

        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        LC0189_RotateArray solver = new LC0189_RotateArray();

        // Scenario 1: Standard rotation
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        solver.rotate(nums1, k1);
        System.out.println("Scenario 1 - Standard (k = 3):    " + Arrays.toString(nums1));
        System.out.println("Expected:                           [5, 6, 7, 1, 2, 3, 4]\n");

        // Scenario 2: k is larger than the array length (k = 6 for length 4 -> effective k = 2)
        int[] nums2 = {-1, -100, 3, 99};
        int k2 = 6;
        solver.rotate(nums2, k2);
        System.out.println("Scenario 2 - k > length (k = 6):   " + Arrays.toString(nums2));
        System.out.println("Expected:                           [3, 99, -1, -100]\n");

        // Scenario 3: Full cycle rotation (k == n) -> array unchanged
        int[] nums3 = {1, 2, 3};
        int k3 = 3;
        solver.rotate(nums3, k3);
        System.out.println("Scenario 3 - Full Cycle (k = 3):   " + Arrays.toString(nums3));
        System.out.println("Expected:                           [1, 2, 3]\n");

        // Scenario 4: Single element array
        int[] nums4 = {42};
        int k4 = 5;
        solver.rotate(nums4, k4);
        System.out.println("Scenario 4 - Single Element:        " + Arrays.toString(nums4));
        System.out.println("Expected:                           [42]");
    }
}
