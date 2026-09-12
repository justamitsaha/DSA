package leetcode.P_DynamicPrograming;

/**
 * LeetCode 1143: Longest Common Subsequence
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-common-subsequence/
 *
 * Algorithm:
 * - Dynamic Programming (2D Tabulation with 1D Space Optimization)
 *
 * Concepts:
 * - Given two strings `text1` and `text2`, find the length of their longest common subsequence.
 * - Subsequence Property:
 *   - A subsequence retains relative order but doesn't require elements to be contiguous.
 * - 2D DP Recurrence:
 *   - Let `dp[i][j]` be the LCS of prefixes `text1[0..i-1]` and `text2[0..j-1]`:
 *     - If `text1.charAt(i - 1) == text2.charAt(j - 1)`:
 *       `dp[i][j] = 1 + dp[i - 1][j - 1]`
 *     - Otherwise:
 *       `dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])`
 * - Space Optimization:
 *   - Computing row `i` requires only row `i - 1`, reducible to 1D array of size `m + 1`.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Where m, n are string lengths.
 * - Space Complexity: O(min(m, n)) - With 1D space optimization.
 */
public class LC1143_LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }

        // Ensure text2 is the shorter string to optimize space
        if (text1.length() < text2.length()) {
            return longestCommonSubsequence(text2, text1);
        }

        int m = text1.length();
        int n = text2.length();

        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int prevDiag = 0; // Represents dp[i - 1][j - 1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = 1 + prevDiag;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prevDiag = temp;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        LC1143_LongestCommonSubsequence solver = new LC1143_LongestCommonSubsequence();

        // Scenario 1: "abcde" and "ace" -> 3 ("ace")
        System.out.println("LCS(\"abcde\", \"ace\"): " + solver.longestCommonSubsequence("abcde", "ace") + " (Expected: 3)");

        // Scenario 2: "abc" and "abc" -> 3 ("abc")
        System.out.println("LCS(\"abc\", \"abc\"): " + solver.longestCommonSubsequence("abc", "abc") + " (Expected: 3)");

        // Scenario 3: "abc" and "def" -> 0 (no common subsequence)
        System.out.println("LCS(\"abc\", \"def\"): " + solver.longestCommonSubsequence("abc", "def") + " (Expected: 0)");
    }
}
