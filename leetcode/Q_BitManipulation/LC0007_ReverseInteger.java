package leetcode.Q_BitManipulation;

/**
 * LeetCode 7: Reverse Integer
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/reverse-integer/
 *
 * Algorithm:
 * - Mathematical Digit Reversal with 32-bit Overflow Detection
 *
 * Concepts:
 * - Given a signed 32-bit integer `x`, return `x` with its digits reversed.
 * - If reversing `x` causes the value to go outside the signed 32-bit integer range `[-2^31, 2^31 - 1]`, return 0.
 * - Overflow Detection Strategies:
 *   1. Arithmetic Check (Pre-Multiply):
 *      Before executing `reversed = reversed * 10 + digit`:
 *      - If `reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7)`, return 0.
 *      - If `reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8)`, return 0.
 *   2. Post-Reversal Reversibility Verification (as used in Udemy course):
 *      `int newReversed = reversed * 10 + digit;`
 *      `if ((newReversed - digit) / 10 != reversed) return 0;`
 *
 * Complexity:
 * - Time Complexity:  O(log10 |x|) - Roughly at most 10 iterations for a 32-bit integer.
 * - Space Complexity: O(1)          - Constant memory.
 */
public class LC0007_ReverseInteger {

    public int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;

            // Check for potential positive overflow
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            // Check for potential negative underflow
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return reversed;
    }

    public static void main(String[] args) {
        LC0007_ReverseInteger solver = new LC0007_ReverseInteger();

        // Scenario 1: Positive number
        System.out.println("Reverse 123: " + solver.reverse(123) + " (Expected: 321)");

        // Scenario 2: Negative number
        System.out.println("Reverse -123: " + solver.reverse(-123) + " (Expected: -321)");

        // Scenario 3: Trailing zeros
        System.out.println("Reverse 120: " + solver.reverse(120) + " (Expected: 21)");

        // Scenario 4: Overflow case (reversing 1534236469 exceeds Integer.MAX_VALUE)
        System.out.println("Reverse 1534236469 (overflow): " + solver.reverse(1534236469) + " (Expected: 0)");

        // Scenario 5: Single digit
        System.out.println("Reverse 0: " + solver.reverse(0) + " (Expected: 0)");
    }
}
