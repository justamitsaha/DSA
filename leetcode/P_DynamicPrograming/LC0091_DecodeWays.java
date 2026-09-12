package leetcode.P_DynamicPrograming;

/**
 * LeetCode 91: Decode Ways
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/decode-ways/
 *
 * Algorithm:
 * - Dynamic Programming (Fibonacci-Style String Partitioning - O(n) Time, O(1) Space)
 *
 * Concepts:
 * - A message containing letters A-Z is encoded using mapping: 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".
 * - Determine the total number of ways to decode a string of digits `s`.
 * - Valid Single-Digit Decodings:
 *   - Any character from '1' to '9' maps to a letter ('0' cannot be mapped alone!).
 *   - If valid, adds `dp[i - 1]` ways.
 * - Valid Two-Digit Decodings:
 *   - Any two-digit substring from "10" to "26" maps to a letter (e.g., "10" -> 'J', "26" -> 'Z').
 *   - If valid, adds `dp[i - 2]` ways.
 * - Space Optimization:
 *   - Like Fibonacci, `dp[i]` only depends on `dp[i - 1]` and `dp[i - 2]`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through string.
 * - Space Complexity: O(1) - Two rolling state variables.
 */
public class LC0091_DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int prev2 = 1; // Base case: dp[0] = 1 (empty prefix)
        int prev1 = 1; // Base case: dp[1] = 1 (first valid non-zero character)

        for (int i = 2; i <= n; i++) {
            int current = 0;

            // Single digit: s[i - 1]
            int singleDigit = s.charAt(i - 1) - '0';
            if (singleDigit >= 1 && singleDigit <= 9) {
                current += prev1;
            }

            // Two digits: s[i - 2..i - 1]
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigits >= 10 && twoDigits <= 26) {
                current += prev2;
            }

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        LC0091_DecodeWays solver = new LC0091_DecodeWays();

        // Scenario 1: "12" -> 2 ("AB" or "L")
        System.out.println("Decodings for \"12\": " + solver.numDecodings("12") + " (Expected: 2)");

        // Scenario 2: "226" -> 3 ("BZ", "VF", "BBF")
        System.out.println("Decodings for \"226\": " + solver.numDecodings("226") + " (Expected: 3)");

        // Scenario 3: "06" -> 0 (leading zero invalid)
        System.out.println("Decodings for \"06\": " + solver.numDecodings("06") + " (Expected: 0)");

        // Scenario 4: "10" -> 1 ('J')
        System.out.println("Decodings for \"10\": " + solver.numDecodings("10") + " (Expected: 1)");

        // Scenario 5: "2101" -> 1 ("2 10 1")
        System.out.println("Decodings for \"2101\": " + solver.numDecodings("2101") + " (Expected: 1)");
    }
}
