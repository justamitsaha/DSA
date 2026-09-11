package leetcode.A_Array;

import java.util.Arrays;

/**
 * LeetCode 238: Product of Array Except Self
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/product-of-array-except-self/
 *
 * Algorithm:
 * - Prefix & Suffix Products (Prefix-Suffix Decomposition)
 *
 * Problem Constraint:
 * - You must write an algorithm that runs in O(n) time and WITHOUT using the division operation '/'.
 *
 * Concepts:
 * - The product of all numbers except nums[i] is equal to:
 *     (product of all elements to the left of i) * (product of all elements to the right of i)
 * - We can precompute left prefix products and right suffix products, then multiply them together.
 * - In Approach 2 (Space-Optimized), we compute the prefix product directly in the output array,
 *   and keep a running right product variable in a backward pass, achieving O(1) auxiliary space.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Two linear scans of the array.
 * - Space Complexity:
 *     - Approach 1: O(n) auxiliary space for left and right arrays.
 *     - Approach 2: O(1) auxiliary space (output array does not count towards extra space per problem description).
 */
public class LC0238_ProductOfArrayExceptSelf {

    /**
     * Approach 1: Explicit Prefix and Suffix Arrays (Intuitive).
     *
     * @param nums input integer array
     * @return array where output[i] is product of all nums except nums[i]
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] output = new int[n];

        // Base cases: nothing to the left of index 0, nothing to the right of index n-1
        left[0] = 1;
        right[n - 1] = 1;

        // Fill prefix products from left to right
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        // Fill suffix products from right to left
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        // Combine left prefix and right suffix products
        for (int i = 0; i < n; i++) {
            output[i] = left[i] * right[i];
        }

        return output;
    }

    /**
     * Approach 2: O(1) Auxiliary Space Optimization.
     * Uses the output array to store prefix products, and maintains a running suffix product.
     */
    public int[] productExceptSelfOptimized(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];

        // Pass 1: output[i] stores product of all elements to the left of i
        output[0] = 1;
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        // Pass 2: Multiply with running product from the right
        int runningRightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            output[i] = output[i] * runningRightProduct;
            runningRightProduct *= nums[i];
        }

        return output;
    }

    public static void main(String[] args) {
        LC0238_ProductOfArrayExceptSelf solver = new LC0238_ProductOfArrayExceptSelf();

        // Scenario 1: Standard positive integers
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("Scenario 1 - Standard: " + Arrays.toString(nums1));
        System.out.println("Output (Approach 1):  " + Arrays.toString(solver.productExceptSelf(nums1)));
        System.out.println("Output (Optimized):   " + Arrays.toString(solver.productExceptSelfOptimized(nums1)));
        System.out.println("Expected:             [24, 12, 8, 6]\n");

        // Scenario 2: Array containing a single zero
        int[] nums2 = {-1, 1, 0, -3, 3};
        System.out.println("Scenario 2 - Single Zero: " + Arrays.toString(nums2));
        System.out.println("Output (Optimized):       " + Arrays.toString(solver.productExceptSelfOptimized(nums2)));
        System.out.println("Expected:                 [0, 0, 9, 0, 0]\n");

        // Scenario 3: Array containing multiple zeroes
        int[] nums3 = {0, 4, 0};
        System.out.println("Scenario 3 - Multiple Zeroes: " + Arrays.toString(nums3));
        System.out.println("Output (Optimized):           " + Arrays.toString(solver.productExceptSelfOptimized(nums3)));
        System.out.println("Expected:                     [0, 0, 0]\n");

        // Scenario 4: Smallest valid array (length = 2)
        int[] nums4 = {2, 5};
        System.out.println("Scenario 4 - Two Elements: " + Arrays.toString(nums4));
        System.out.println("Output (Optimized):        " + Arrays.toString(solver.productExceptSelfOptimized(nums4)));
        System.out.println("Expected:                  [5, 2]");
    }
}
