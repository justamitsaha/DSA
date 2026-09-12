package leetcode.Q_BitManipulation;

/**
 * LeetCode 268: Missing Number
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/missing-number/
 *
 * Algorithm:
 * - Method 1: Bitwise XOR Index Cancellation (No integer overflow risk)
 * - Method 2: Gauss's Sum Formula (n * (n + 1) / 2)
 *
 * Concepts:
 * - Given an array `nums` containing `n` distinct numbers in the range `[0, n]`, return the only missing number.
 * - Bitwise XOR Approach:
 *   - The numbers present plus their indices form pairs of duplicate values:
 *     Every present number `k` matches an index `k`, EXCEPT for the missing number which has an index without a matching value!
 *   - XORing all indices from `0` to `n` and all values in `nums` leaves ONLY the missing number!
 *   - Unlike arithmetic summation, bitwise XOR is completely immune to integer overflow.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the array.
 * - Space Complexity: O(1) - Constant memory.
 */
public class LC0268_MissingNumber {

    /**
     * Bitwise XOR cancellation approach.
     */
    public int missingNumber(int[] nums) {
        int missing = nums.length;

        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }

        return missing;
    }

    /**
     * Gauss's Summation approach: sum(0..n) - sum(nums).
     */
    public int missingNumberSum(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        LC0268_MissingNumber solver = new LC0268_MissingNumber();

        // Scenario 1: [3, 0, 1] -> missing 2
        int[] nums1 = {3, 0, 1};
        System.out.println("Missing in [3, 0, 1] (XOR): " + solver.missingNumber(nums1) + " (Expected: 2)");
        System.out.println("Missing in [3, 0, 1] (Sum): " + solver.missingNumberSum(nums1) + " (Expected: 2)");

        // Scenario 2: [0, 1] -> missing 2
        int[] nums2 = {0, 1};
        System.out.println("Missing in [0, 1]: " + solver.missingNumber(nums2) + " (Expected: 2)");

        // Scenario 3: [9,6,4,2,3,5,7,0,1] -> missing 8
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println("Missing in [9,6,4,2,3,5,7,0,1]: " + solver.missingNumber(nums3) + " (Expected: 8)");
    }
}
