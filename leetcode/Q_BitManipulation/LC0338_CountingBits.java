package leetcode.Q_BitManipulation;

import java.util.Arrays;

/**
 * LeetCode 338: Counting Bits
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/counting-bits/
 *
 * Algorithm:
 * - Dynamic Programming with Bit Manipulation (O(n) Optimal)
 *
 * Concepts:
 * - Given an integer `n`, return an array `ans` of length `n + 1` where `ans[i]` is the number of 1's in the binary representation of `i`.
 * - Linear DP Recurrence Relations:
 *   1. Right Shift Relation (`i >> 1`):
 *      `ans[i] = ans[i >> 1] + (i & 1)`
 *      - Shifting right (`i >> 1`) removes the least significant bit, whose bit count was already computed at `ans[i / 2]`.
 *      - Add `(i & 1)` to count whether the removed bit was a '1'.
 *   2. Lowest-Set-Bit Relation (`i & (i - 1)`):
 *      `ans[i] = ans[i & (i - 1)] + 1`
 *      - `i & (i - 1)` clears the lowest set bit of `i`.
 *      - The bit count of `i` is simply 1 plus the count of the number with its lowest bit cleared!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass filling DP array.
 * - Space Complexity: O(1) - Excluding the output array.
 */
public class LC0338_CountingBits {

    /**
     * O(n) DP using Right Shift `ans[i >> 1] + (i & 1)`.
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }

        return ans;
    }

    /**
     * O(n) DP using Brian Kernighan's subproblem `ans[i & (i - 1)] + 1`.
     */
    public int[] countBitsLowestSetBit(int n) {
        int[] ans = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        LC0338_CountingBits solver = new LC0338_CountingBits();

        // Scenario 1: n = 2 -> [0, 1, 1]
        System.out.println("n = 2: " + Arrays.toString(solver.countBits(2)) + " (Expected: [0, 1, 1])");

        // Scenario 2: n = 5 -> [0, 1, 1, 2, 1, 2]
        System.out.println("n = 5: " + Arrays.toString(solver.countBits(5)) + " (Expected: [0, 1, 1, 2, 1, 2])");
        System.out.println("n = 5 (LSB DP): " + Arrays.toString(solver.countBitsLowestSetBit(5)) + " (Expected: [0, 1, 1, 2, 1, 2])");

        // Scenario 3: n = 0 -> [0]
        System.out.println("n = 0: " + Arrays.toString(solver.countBits(0)) + " (Expected: [0])");
    }
}
