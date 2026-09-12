package leetcode.F_SlidingWindow;

/**
 * LeetCode 424: Longest Repeating Character Replacement
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * Algorithm:
 * - Dynamic Sliding Window with Frequency Table
 *
 * Window Invariant:
 * - In any valid window [start, end], the number of characters that need to be replaced is:
 *     replacementsNeeded = windowLength - maxFrequencyInWindow = (end - start + 1) - maxCount
 * - If `replacementsNeeded <= k`: the entire window can be transformed into the most frequent character.
 * - If `replacementsNeeded > k`: the window is invalid, so shrink it from the left (`start++`).
 *
 * Key Optimization (Non-Decreasing Window):
 * - We only care about finding a window LARGER than the current maximum.
 * - Therefore, `maxCount` only needs to be updated when a new character count exceeds it.
 * - We do NOT need to decrement `maxCount` when shrinking from the left, because a smaller `maxCount`
 *   would only produce a smaller window, which cannot beat our best answer!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Both `start` and `end` pointers advance at most n times.
 * - Space Complexity: O(1) - Fixed 26-element array for uppercase English letters.
 */
public class LC0424_LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] count = new int[26];
        int maxCount = 0;
        int maxLength = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            // Include current character into frequency window
            int charIndex = s.charAt(end) - 'A';
            count[charIndex]++;
            maxCount = Math.max(maxCount, count[charIndex]);

            // If characters to replace exceed k, shrink window from left
            while ((end - start + 1) - maxCount > k) {
                count[s.charAt(start) - 'A']--;
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LC0424_LongestRepeatingCharacterReplacement solver = new LC0424_LongestRepeatingCharacterReplacement();

        // Scenario 1: "ABAB", k = 2 -> 4 (Replace two 'A's with 'B's or vice-versa)
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("Scenario 1 (\"ABAB\", k = 2):");
        System.out.println("  Result:   " + solver.characterReplacement(s1, k1) + " | Expected: 4\n");

        // Scenario 2: "AABABBA", k = 1 -> 4 (Replace middle 'A' with 'B' to form "BBBB")
        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("Scenario 2 (\"AABABBA\", k = 1):");
        System.out.println("  Result:   " + solver.characterReplacement(s2, k2) + " | Expected: 4\n");

        // Scenario 3: All same characters "AAAA", k = 2 -> 4
        String s3 = "AAAA";
        int k3 = 2;
        System.out.println("Scenario 3 (\"AAAA\", k = 2):");
        System.out.println("  Result:   " + solver.characterReplacement(s3, k3) + " | Expected: 4\n");

        // Scenario 4: k = 0 (No replacements allowed)
        String s4 = "ABBB";
        int k4 = 0;
        System.out.println("Scenario 4 (\"ABBB\", k = 0):");
        System.out.println("  Result:   " + solver.characterReplacement(s4, k4) + " | Expected: 3");
    }
}
