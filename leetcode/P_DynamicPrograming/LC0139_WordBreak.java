package leetcode.P_DynamicPrograming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 139: Word Break
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/word-break/
 *
 * Algorithm:
 * - Dynamic Programming (Prefix Segmentation with Max Word-Length Pruning)
 *
 * Concepts:
 * - Given a string `s` and a dictionary of strings `wordDict`, return true if `s` can be segmented
 *   into a space-separated sequence of one or more dictionary words.
 * - Tabulation DP Formulation:
 *   - Let `dp[i]` be true if prefix `s[0..i - 1]` can be segmented into dictionary words.
 *   - Base Case: `dp[0] = true` (an empty prefix is valid).
 *   - Transitions:
 *     For each index `i` from 1 to `s.length()`:
 *       For each index `j` from `i - maxWordLength` up to `i - 1`:
 *         If `dp[j] == true && wordSet.contains(s.substring(j, i))`:
 *           `dp[i] = true`; break;
 *
 * Complexity:
 * - Time Complexity:  O(n * L^2) - Where n is s.length() and L is the maximum word length in wordDict.
 * - Space Complexity: O(n + W)   - For the DP array of size n + 1 and the HashSet of dictionary words.
 */
public class LC0139_WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return false;
        }

        Set<String> wordSet = new HashSet<>(wordDict);
        int maxWordLen = 0;
        for (String word : wordDict) {
            maxWordLen = Math.max(maxWordLen, word.length());
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            // Only examine substrings of length up to maxWordLen
            int start = Math.max(0, i - maxWordLen);
            for (int j = i - 1; j >= start; j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        LC0139_WordBreak solver = new LC0139_WordBreak();

        // Scenario 1: "leetcode", ["leet", "code"] -> true
        System.out.println("WordBreak(\"leetcode\"): " +
            solver.wordBreak("leetcode", Arrays.asList("leet", "code")) + " (Expected: true)");

        // Scenario 2: "applepenapple", ["apple", "pen"] -> true
        System.out.println("WordBreak(\"applepenapple\"): " +
            solver.wordBreak("applepenapple", Arrays.asList("apple", "pen")) + " (Expected: true)");

        // Scenario 3: "catsandog", ["cats", "dog", "sand", "and", "cat"] -> false
        System.out.println("WordBreak(\"catsandog\"): " +
            solver.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")) + " (Expected: false)");
    }
}
