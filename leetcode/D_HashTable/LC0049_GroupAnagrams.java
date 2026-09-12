package leetcode.D_HashTable;

import java.util.*;

/**
 * LeetCode 49: Group Anagrams
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/group-anagrams/
 *
 * Algorithms:
 * 1. Categorize by Sorted String as HashMap Key -> O(N * K log K)
 * 2. Categorize by Character Count Array Key     -> O(N * K)
 *
 * Concepts:
 * - Two strings are anagrams if sorting their characters yields the exact same canonical string:
 *     "eat" -> "aet", "tea" -> "aet", "ate" -> "aet".
 * - By mapping each sorted string to a list of original words (`Map<String, List<String>>`),
 *   all anagrams naturally group together into the same bucket.
 * - Finally, return `new ArrayList<>(map.values())`.
 *
 * Complexity:
 * - Approach 1 (Sorted Key): Time O(N * K log K) where N is number of strings, and K is maximum length of a string.
 * - Space Complexity:        O(N * K) to store the grouped lists.
 */
public class LC0049_GroupAnagrams {

    /**
     * Groups strings that are anagrams together using sorted string keys.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        LC0049_GroupAnagrams solver = new LC0049_GroupAnagrams();

        // Scenario 1: Standard list with multiple anagram groups
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Scenario 1: " + Arrays.toString(strs1));
        System.out.println("  Grouped:  " + solver.groupAnagrams(strs1));
        System.out.println("  Expected: [[eat, tea, ate], [bat], [tan, nat]]\n");

        // Scenario 2: Single empty string [""]
        String[] strs2 = {""};
        System.out.println("Scenario 2: " + Arrays.toString(strs2));
        System.out.println("  Grouped:  " + solver.groupAnagrams(strs2));
        System.out.println("  Expected: [[\"\"]]\n");

        // Scenario 3: Single character string ["a"]
        String[] strs3 = {"a"};
        System.out.println("Scenario 3: " + Arrays.toString(strs3));
        System.out.println("  Grouped:  " + solver.groupAnagrams(strs3));
        System.out.println("  Expected: [[a]]");
    }
}
