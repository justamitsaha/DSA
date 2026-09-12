package leetcode.D_HashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128: Longest Consecutive Sequence
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Algorithm:
 * - Hash Set Sequence Starter Identification (Strictly O(n) Time)
 *
 * Problem Constraint:
 * - You must write an algorithm that runs in O(n) time complexity (ruling out O(n log n) sorting).
 *
 * Concepts:
 * - Insert all numbers into a `HashSet` for O(1) average lookup time.
 * - Key Optimization (Sequence Starter):
 *     - Only attempt to build a sequence starting at `num` if `num - 1` is NOT in the set.
 *     - If `num - 1` exists, then `num` is part of a longer sequence that will be counted from its true beginning.
 *     - If `num - 1` does NOT exist, `num` is the sequence starter! We count forward `num + 1, num + 2...`
 *       until the streak ends.
 * - Because inner while-loops only run for the start of each sequence, each number is visited at most twice!
 *   This ensures strict O(n) total time complexity.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Linear passes over the array and set.
 * - Space Complexity: O(n) - Storage for the hash set.
 */
public class LC0128_LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxStreak = 0;

        for (int num : set) {
            // Only start counting if 'num' is the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                }
            }
        }

        return maxStreak;
    }

    public static void main(String[] args) {
        LC0128_LongestConsecutiveSequence solver = new LC0128_LongestConsecutiveSequence();

        // Scenario 1: Standard consecutive sequence [100, 4, 200, 1, 3, 2] -> 4 (1, 2, 3, 4)
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Scenario 1: " + Arrays.toString(nums1));
        System.out.println("  Longest Streak: " + solver.longestConsecutive(nums1) + " | Expected: 4\n");

        // Scenario 2: Longer sequence with duplicates [0, 3, 7, 2, 5, 8, 4, 6, 0, 1] -> 9 (0 to 8)
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("Scenario 2: " + Arrays.toString(nums2));
        System.out.println("  Longest Streak: " + solver.longestConsecutive(nums2) + " | Expected: 9\n");

        // Scenario 3: Single element array [9] -> 1
        int[] nums3 = {9};
        System.out.println("Scenario 3: " + Arrays.toString(nums3));
        System.out.println("  Longest Streak: " + solver.longestConsecutive(nums3) + " | Expected: 1\n");

        // Scenario 4: Empty array
        int[] nums4 = {};
        System.out.println("Scenario 4 - Empty: " + solver.longestConsecutive(nums4) + " | Expected: 0");
    }
}
