package leetcode.F_SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3: Longest Substring Without Repeating Characters
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Problem:
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Examples:
 * - "abcabcbb" -> 3 ("abc")
 * - "bbbbb"    -> 1 ("b")
 * - "pwwkew"   -> 3 ("wke")
 *
 * Complexities:
 * - Time Complexity:  O(n)
 * - Space Complexity: O(min(n, m)) where m is alphabet size (e.g. 128 for ASCII)
 */
public class LC0003_LongestSubstringWithoutRepeatingCharacters {

    /**
     * Optimal Sliding Window:
     * Maintains a window [left, right]. When character at 'right' is seen,
     * jump 'left' to max(left, lastSeenIndex + 1).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(min(n, 128))
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen.containsKey(c)) {
                // Move the left boundary past the previous occurrence
                left = Math.max(left, lastSeen.get(c) + 1);
            }
            lastSeen.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LC0003_LongestSubstringWithoutRepeatingCharacters solver = new LC0003_LongestSubstringWithoutRepeatingCharacters();

        System.out.println("abca: " + solver.lengthOfLongestSubstring("abca"));       // Expected: 3
        System.out.println("abcabcbb: " + solver.lengthOfLongestSubstring("abcabcbb")); // Expected: 3
        System.out.println("bbbbb: " + solver.lengthOfLongestSubstring("bbbbb"));       // Expected: 1
        System.out.println("pwwkew: " + solver.lengthOfLongestSubstring("pwwkew"));     // Expected: 3
        System.out.println("empty: " + solver.lengthOfLongestSubstring(""));           // Expected: 0
    }
}
