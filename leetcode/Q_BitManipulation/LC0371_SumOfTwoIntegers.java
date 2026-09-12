package leetcode.Q_BitManipulation;

/**
 * LeetCode 371: Sum of Two Integers
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/sum-of-two-integers/
 *
 * Algorithm:
 * - Bitwise Half-Adder Simulation (XOR for Sum, AND with Shift for Carry)
 *
 * Concepts:
 * - Calculate the sum of two integers `a` and `b` without using arithmetic operators `+` and `-`.
 * - Digital Circuit Logic:
 *   - The bitwise XOR operation (`a ^ b`) performs addition without carry (0+0=0, 0+1=1, 1+0=1, 1+1=0).
 *   - The bitwise AND operation followed by a left shift (`(a & b) << 1`) calculates the carry bits that need to be added to the next higher significance.
 *   - By iteratively setting `a = a ^ b` and `b = (a & b) << 1`, we propagate carries until `b == 0`.
 *
 * Complexity:
 * - Time Complexity:  O(1) - Loop runs at most 32 times for 32-bit integers.
 * - Space Complexity: O(1) - Constant space.
 */
public class LC0371_SumOfTwoIntegers {

    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        LC0371_SumOfTwoIntegers solver = new LC0371_SumOfTwoIntegers();

        // Scenario 1: Positive numbers (1 + 2 = 3)
        System.out.println("1 + 2 = " + solver.getSum(1, 2) + " (Expected: 3)");

        // Scenario 2: Two negatives (-2 + -3 = -5)
        System.out.println("-2 + -3 = " + solver.getSum(-2, -3) + " (Expected: -5)");

        // Scenario 3: Mixed signs (-2 + 3 = 1)
        System.out.println("-2 + 3 = " + solver.getSum(-2, 3) + " (Expected: 1)");

        // Scenario 4: Adding zero (0 + 42 = 42)
        System.out.println("0 + 42 = " + solver.getSum(0, 42) + " (Expected: 42)");
    }
}
