package leetcode.P_DynamicPrograming;

/**
 * LeetCode 5: Longest Palindromic Substring
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Algorithm:
 * - Method 1: Expand Around Center (O(n^2) Time, O(1) Space - Optimal)
 * - Method 2: 2D Dynamic Programming Tabulation (O(n^2) Time, O(n^2) Space)
 *
 * Concepts:
 * - A palindrome reads the same backwards as forwards.
 * - Expand Around Center:
 *   - A palindrome mirrors around its center.
 *   - There are `2n - 1` such centers:
 *     - `n` odd-length centers: centered on a single character `(i, i)`.
 *     - `n - 1` even-length centers: centered between two characters `(i, i + 1)`.
 *   - For each center, expand outward as long as both boundary characters match.
 *   - Extremely fast in practice with zero heap allocations.
 *
 * Complexity:
 * - Method 1: Time: O(n^2), Space: O(1)
 * - Method 2: Time: O(n^2), Space: O(n^2)
 */
public class LC0005_LongestPalindromicSubstring {

    /**
     * Expand Around Center (O(1) auxiliary space).
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0;
        int maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome centered at i
            int len1 = expand(s, i, i);
            // Even length palindrome centered between i and i + 1
            int len2 = expand(s, i, i + 1);

            int currentMax = Math.max(len1, len2);
            if (currentMax > maxLen) {
                maxLen = currentMax;
                start = i - (currentMax - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 2D DP Table approach.
     */
    public String longestPalindromeDP(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLen = 1;

        // Substrings of length 1 are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Substrings of length >= 2
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (len > maxLen) {
                            maxLen = len;
                            start = i;
                        }
                    }
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        LC0005_LongestPalindromicSubstring solver = new LC0005_LongestPalindromicSubstring();

        // Scenario 1: "babad" -> "bab" or "aba"
        System.out.println("Longest palindrome in \"babad\": " + solver.longestPalindrome("babad"));

        // Scenario 2: "cbbd" -> "bb"
        System.out.println("Longest palindrome in \"cbbd\": " + solver.longestPalindrome("cbbd") + " (Expected: bb)");

        // Scenario 3: Single character "a" -> "a"
        System.out.println("Longest palindrome in \"a\": " + solver.longestPalindrome("a") + " (Expected: a)");

        // Scenario 4: Full palindrome "racecar" -> "racecar"
        System.out.println("Longest palindrome in \"racecar\": " + solver.longestPalindrome("racecar") + " (Expected: racecar)");
    }
}
