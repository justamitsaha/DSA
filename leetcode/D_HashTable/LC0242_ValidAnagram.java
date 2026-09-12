package leetcode.D_HashTable;

import java.util.Arrays;

/**
 * LeetCode 242: Valid Anagram
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/valid-anagram/
 *
 * Algorithms:
 * 1. Fixed-Size Frequency Array (26 letters) -> Optimal O(n) Time, O(1) Space
 * 2. Character Array Sorting                 -> Time O(n log n), Space O(n)
 *
 * Frequency Array Intuition:
 * - Two strings are anagrams if and only if they contain the exact same characters with identical counts.
 * - If their lengths differ, they cannot be anagrams -> return false immediately.
 * - For strings composed of lowercase English letters, a fixed integer array of size 26 acts as a frequency bucket:
 *     - Increment `count[s.charAt(i) - 'a']++`
 *     - Decrement `count[t.charAt(i) - 'a']--`
 * - If any bucket is non-zero after the scan, the strings are not anagrams.
 *
 * Complexity:
 * - Frequency Array: Time O(n), Space O(1) (fixed 26-element array).
 * - Sorting:          Time O(n log n), Space O(n).
 */
public class LC0242_ValidAnagram {

    /**
     * Approach 1: Fixed-size 26-element Frequency Array (Optimal).
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Approach 2: Character Array Sorting (Intuitive).
     */
    public boolean isAnagramSorting(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Arrays.sort(sArr);
        Arrays.sort(tArr);

        return Arrays.equals(sArr, tArr);
    }

    public static void main(String[] args) {
        LC0242_ValidAnagram solver = new LC0242_ValidAnagram();

        // Scenario 1: Valid anagrams "anagram" and "nagaram"
        String s1 = "anagram", t1 = "nagaram";
        System.out.println("Scenario 1 (\"anagram\", \"nagaram\"):");
        System.out.println("  Frequency Array: " + solver.isAnagram(s1, t1) + " | Expected: true");
        System.out.println("  Sorting:         " + solver.isAnagramSorting(s1, t1) + " | Expected: true\n");

        // Scenario 2: Non-anagrams with different frequencies "rat" and "car"
        String s2 = "rat", t2 = "car";
        System.out.println("Scenario 2 (\"rat\", \"car\"):");
        System.out.println("  Is Anagram: " + solver.isAnagram(s2, t2) + " | Expected: false\n");

        // Scenario 3: Different lengths "a" and "ab"
        String s3 = "a", t3 = "ab";
        System.out.println("Scenario 3 (\"a\", \"ab\"):");
        System.out.println("  Is Anagram: " + solver.isAnagram(s3, t3) + " | Expected: false");
    }
}
