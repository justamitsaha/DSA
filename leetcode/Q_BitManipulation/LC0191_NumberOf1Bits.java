package leetcode.Q_BitManipulation;

/**
 * LeetCode 191: Number of 1 Bits (Hamming Weight)
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/number-of-1-bits/
 *
 * Algorithm:
 * - Method 1: Brian Kernighan's Algorithm (`n & (n - 1)`) - O(number of set bits)
 * - Method 2: Bit-by-Bit 32-pass check (`n & (1 << i)`)
 *
 * Concepts:
 * - The Hamming weight is the number of '1' bits in the binary representation of an integer.
 * - Brian Kernighan's Bit Trick:
 *   - Subtracting 1 from a number flips all bits after the rightmost set bit, including the rightmost set bit itself.
 *   - Therefore, `n & (n - 1)` clears the lowest/rightmost set bit of `n`!
 *   - Each iteration eliminates exactly one '1' bit, running in time proportional ONLY to the number of set bits (at most 32).
 *
 * Complexity:
 * - Time Complexity:  O(k) where k is the number of set bits (k <= 32).
 * - Space Complexity: O(1) - Constant space.
 */
public class LC0191_NumberOf1Bits {

    /**
     * Brian Kernighan's optimal algorithm.
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // Clears the lowest set bit
            count++;
        }
        return count;
    }

    /**
     * Standard bit-mask checking each of 32 bits.
     */
    public int hammingWeightBitShift(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LC0191_NumberOf1Bits solver = new LC0191_NumberOf1Bits();

        // Scenario 1: 11 (binary 1011) -> 3 set bits
        System.out.println("Hamming weight of 11: " + solver.hammingWeight(11) + " (Expected: 3)");

        // Scenario 2: 128 (binary 10000000) -> 1 set bit
        System.out.println("Hamming weight of 128: " + solver.hammingWeight(128) + " (Expected: 1)");

        // Scenario 3: 2147483645 (binary 01111111111111111111111111111101) -> 30 set bits
        System.out.println("Hamming weight of 2147483645: " + solver.hammingWeight(2147483645) + " (Expected: 30)");

        // Scenario 4: 0 -> 0 set bits
        System.out.println("Hamming weight of 0: " + solver.hammingWeight(0) + " (Expected: 0)");
    }
}
