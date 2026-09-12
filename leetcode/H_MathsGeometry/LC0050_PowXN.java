package leetcode.H_MathsGeometry;

/**
 * LeetCode 50: Pow(x, n)
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/powx-n/
 *
 * Algorithm:
 * - Binary Exponentiation (Exponentiation by Squaring / Fast Power)
 *
 * Concepts:
 * - Computing x^n naively by multiplying x, n times takes O(n) time, which causes TLE for large n (up to 2^31 - 1).
 * - Key Mathematical Property:
 *     - If n is even: x^n = (x^2)^(n/2)
 *     - If n is odd:  x^n = x * (x^2)^((n-1)/2)
 * - Overflow Trap:
 *     - n can be Integer.MIN_VALUE (-2147483648). Negating -2147483648 in a 32-bit signed int overflows back to -2147483648!
 *     - To safely handle negative powers, promote `n` to a 64-bit `long N = n`. If N < 0, invert base `x = 1.0 / x` and `N = -N`.
 *
 * Complexity:
 * - Time Complexity:  O(log n) - Halving the exponent in each iteration.
 * - Space Complexity: O(1)     - Iterative implementation uses constant memory (O(log n) for recursion stack).
 */
public class LC0050_PowXN {

    /**
     * Iterative Binary Exponentiation (O(1) auxiliary memory).
     */
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1.0 / x;
            N = -N;
        }

        double result = 1.0;
        double currentProduct = x;

        while (N > 0) {
            if ((N % 2) == 1) {
                result *= currentProduct;
            }
            currentProduct *= currentProduct;
            N /= 2;
        }

        return result;
    }

    /**
     * Recursive Binary Exponentiation (Divide and Conquer).
     */
    public double myPowRecursive(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        double half = myPowRecursive(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            if (n > 0) {
                return half * half * x;
            } else {
                return (half * half) / x;
            }
        }
    }

    public static void main(String[] args) {
        LC0050_PowXN solver = new LC0050_PowXN();

        // Scenario 1: Positive power
        double x1 = 2.00000;
        int n1 = 10;
        System.out.println("2.0^10 = " + solver.myPow(x1, n1) + " (Expected: 1024.0)");

        // Scenario 2: Fractional base with positive power
        double x2 = 2.10000;
        int n2 = 3;
        System.out.printf("2.1^3 = %.5f (Expected: 9.26100)\n", solver.myPow(x2, n2));

        // Scenario 3: Negative power
        double x3 = 2.00000;
        int n3 = -2;
        System.out.println("2.0^-2 = " + solver.myPow(x3, n3) + " (Expected: 0.25)");

        // Scenario 4: Base power of 0
        System.out.println("5.0^0 = " + solver.myPow(5.0, 0) + " (Expected: 1.0)");

        // Scenario 5: Large negative boundary (Integer.MIN_VALUE overflow test)
        double x5 = 1.0000000000001;
        int n5 = Integer.MIN_VALUE;
        System.out.printf("x^MIN_VALUE = %.5f\n", solver.myPow(x5, n5));
    }
}
