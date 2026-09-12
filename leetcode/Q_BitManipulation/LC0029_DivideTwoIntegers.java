package leetcode.Q_BitManipulation;

/**
 * LeetCode 29: Divide Two Integers
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/divide-two-integers/
 *
 * Algorithm:
 * - Bitwise Exponential Subtraction (Doubling Divisor via Left Shift)
 *
 * Concepts:
 * - Divide two integers without using multiplication, division, and mod operator (`*`, `/`, `%`).
 * - Overflow Edge Case:
 *   - `dividend = Integer.MIN_VALUE` and `divisor = -1` yields `2^31`, which exceeds `Integer.MAX_VALUE` (2^31 - 1).
 *   - We must clamp the result to `Integer.MAX_VALUE`.
 * - Bitwise Exponential Doubling:
 *   - Rather than subtracting `b` one-by-one (which is O(dividend) and causes TLE), we shift `b` left:
 *     `b << 1, b << 2, b << 3 ...` effectively subtracting `b * 2^i` in each phase.
 *   - When `b << i` is the largest power of two multiple that fits into `a`:
 *     - Subtract `b << i` from `a`.
 *     - Add `1 << i` to the quotient.
 *     - Repeat until `a < b`.
 *
 * Complexity:
 * - Time Complexity:  O((log n)^2) or O(32) - At most 32 bits to shift.
 * - Space Complexity: O(1)                 - Constant space.
 */
public class LC0029_DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        // Corner case: 32-bit integer overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign of the result
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert both to positive 64-bit long to prevent overflow
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long quotient = 0;

        while (a >= b) {
            int shift = 0;
            // Find the highest power of 2 such that (b << (shift + 1)) <= a
            while (a >= (b << (shift + 1))) {
                shift++;
            }

            quotient += (1L << shift);
            a -= (b << shift);
        }

        return isNegative ? (int) -quotient : (int) quotient;
    }

    public static void main(String[] args) {
        LC0029_DivideTwoIntegers solver = new LC0029_DivideTwoIntegers();

        // Scenario 1: Positive division (10 / 3 = 3)
        System.out.println("10 / 3 = " + solver.divide(10, 3) + " (Expected: 3)");

        // Scenario 2: Negative result (7 / -3 = -2)
        System.out.println("7 / -3 = " + solver.divide(7, -3) + " (Expected: -2)");

        // Scenario 3: Overflow boundary (Integer.MIN_VALUE / -1)
        System.out.println("MIN_VALUE / -1 = " + solver.divide(Integer.MIN_VALUE, -1) + " (Expected: " + Integer.MAX_VALUE + ")");

        // Scenario 4: Equal numbers (42 / 42 = 1)
        System.out.println("42 / 42 = " + solver.divide(42, 42) + " (Expected: 1)");

        // Scenario 5: Divisor larger than dividend (3 / 5 = 0)
        System.out.println("3 / 5 = " + solver.divide(3, 5) + " (Expected: 0)");
    }
}
