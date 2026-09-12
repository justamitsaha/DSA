package leetcode.Q_BitManipulation;

/**
 * LeetCode 136: Single Number
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/single-number/
 *
 * Algorithm:
 * - Bitwise XOR Accumulation
 *
 * Concepts:
 * - Every element in the array appears twice except for one unique element.
 * - Key Bitwise XOR Properties:
 *   1. Identity:     `x ^ 0 = x`
 *   2. Self-Inverse: `x ^ x = 0`
 *   3. Commutative & Associative: The order of XOR operations does not matter:
 *      `(a ^ b ^ a) = (a ^ a) ^ b = 0 ^ b = b`.
 * - By XORing all elements across the entire array, every duplicate number annihilates itself to 0,
 *   leaving only the single non-duplicated number!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single linear pass through the array.
 * - Space Complexity: O(1) - Constant auxiliary memory.
 */
public class LC0136_SingleNumber {

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public static void main(String[] args) {
        LC0136_SingleNumber solver = new LC0136_SingleNumber();

        // Scenario 1: [2, 2, 1] -> 1
        int[] nums1 = {2, 2, 1};
        System.out.println("Single in [2, 2, 1]: " + solver.singleNumber(nums1) + " (Expected: 1)");

        // Scenario 2: [4, 1, 2, 1, 2] -> 4
        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("Single in [4, 1, 2, 1, 2]: " + solver.singleNumber(nums2) + " (Expected: 4)");

        // Scenario 3: Single element [1] -> 1
        int[] nums3 = {1};
        System.out.println("Single in [1]: " + solver.singleNumber(nums3) + " (Expected: 1)");
    }
}
