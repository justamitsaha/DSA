package leetcode.A_Array;

import java.util.Arrays;

/**
 * LeetCode 152: Maximum Product Subarray
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-product-subarray/
 *
 * Algorithm:
 * - Min-Max Dynamic Programming (Modified Kadane's Algorithm for Products)
 *
 * Concepts:
 * - Unlike addition (in Maximum Subarray Sum), multiplication can produce larger positive numbers
 *   when multiplying two negative numbers (a negative times a negative is positive).
 * - Therefore, at each step i, we must track BOTH:
 *     1. `maxProduct`: Maximum product of a subarray ending at index i.
 *     2. `minProduct`: Minimum (most negative) product of a subarray ending at index i.
 * - When nums[i] < 0:
 *     - Multiplying by a negative number flips the signs: the previous minimum becomes the candidate for maximum,
 *       and the previous maximum becomes the candidate for minimum!
 *     - So we swap `maxProduct` and `minProduct` before updating.
 * - Transitions:
 *     maxProduct = max(nums[i], maxProduct * nums[i])
 *     minProduct = min(nums[i], minProduct * nums[i])
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the array.
 * - Space Complexity: O(1) - Tracks only current min/max products and global result.
 */
public class LC0152_MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // If current element is negative, min and max flip parity
            if (current < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            // Subarray either extends from previous or restarts at current
            maxProduct = Math.max(current, maxProduct * current);
            minProduct = Math.min(current, minProduct * current);

            globalMax = Math.max(globalMax, maxProduct);
        }

        return globalMax;
    }

    public static void main(String[] args) {
        LC0152_MaximumProductSubarray solver = new LC0152_MaximumProductSubarray();

        // Scenario 1: Standard positive and negative mix
        // [2, 3] produces max product 6
        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Scenario 1: " + Arrays.toString(nums1));
        System.out.println("Max Product: " + solver.maxProduct(nums1) + " | Expected: 6\n");

        // Scenario 2: Array with zero breaking contiguous product
        int[] nums2 = {-2, 0, -1};
        System.out.println("Scenario 2: " + Arrays.toString(nums2));
        System.out.println("Max Product: " + solver.maxProduct(nums2) + " | Expected: 0\n");

        // Scenario 3: Two negative numbers multiplying to a large positive
        // (-2) * (-3) * (-4) -> -24, but (-2)*(-3) = 6 or (-3)*(-4) = 12
        int[] nums3 = {-2, 3, -4};
        System.out.println("Scenario 3: " + Arrays.toString(nums3));
        System.out.println("Max Product: " + solver.maxProduct(nums3) + " | Expected: 24 ((-2) * 3 * (-4))\n");

        // Scenario 4: Single negative number
        int[] nums4 = {-2};
        System.out.println("Scenario 4: " + Arrays.toString(nums4));
        System.out.println("Max Product: " + solver.maxProduct(nums4) + " | Expected: -2");
    }
}
