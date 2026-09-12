package leetcode.P_DynamicPrograming;

/**
 * LeetCode 647: Palindromic Substrings
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/palindromic-substrings/
 *
 * Algorithm:
 * - Method 1: Expand Around Center (O(n^2) Time, O(1) Space - Optimal)
 * - Method 2: 2D Dynamic Programming (O(n^2) Time, O(n^2) Space)
 *
 * Concepts:
 * - Given a string `s`, count how many palindromic substrings it contains.
 * - Substrings with different start or end indices are counted as distinct even if they consist of the same characters.
 * - Expand Around Center:
 *   - Expand around 2n - 1 potential centers (odd length at `(i, i)`, even length at `(i, i + 1)`).
 *   - Every valid expansion step represents one additional palindromic substring!
 *
 * Complexity:
 * - Method 1: Time: O(n^2), Space: O(1)
 * - Method 2: Time: O(n^2), Space: O(n^2)
 */
public class LC0647_PalindromicSubstrings {

    /**
     * Expand Around Center (O(1) space).
     */
    public int countSubstrings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int totalCount = 0;
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes
            totalCount += countPalindromesAroundCenter(s, i, i);
            // Count even-length palindromes
            totalCount += countPalindromesAroundCenter(s, i, i + 1);
        }

        return totalCount;
    }

    private int countPalindromesAroundCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    /**
     * 2D DP Tabulation.
     */
    public int countSubstringsDP(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        LC0647_PalindromicSubstrings solver = new LC0647_PalindromicSubstrings();

        // Scenario 1: "abc" -> 3 ("a", "b", "c")
        System.out.println("Substrings in \"abc\": " + solver.countSubstrings("abc") + " (Expected: 3)");

        // Scenario 2: "aaa" -> 6 ("a", "a", "a", "aa", "aa", "aaa")
        System.out.println("Substrings in \"aaa\": " + solver.countSubstrings("aaa") + " (Expected: 6)");
        System.out.println("Substrings in \"aaa\" (DP): " + solver.countSubstringsDP("aaa") + " (Expected: 6)");

        // Scenario 3: Single character "z" -> 1
        System.out.println("Substrings in \"z\": " + solver.countSubstrings("z") + " (Expected: 1)");
    }
}
