package leetcode.D_HashTable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 217: Contains Duplicate
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/contains-duplicate/
 *
 * Algorithms:
 * 1. Hash Set Lookup (Early Exit) -> Optimal Time O(n), Space O(n)
 * 2. Sorting Check                -> Time O(n log n), Space O(1) in-place
 *
 * Concepts:
 * - A Set stores unique elements.
 * - By checking `set.add(num)` (or `set.contains(num)`):
 *     - If `set.add(num)` returns false, the number has already been seen -> duplicate found, return true immediately!
 * - If the loop completes without early exit, all elements are unique -> return false.
 *
 * Complexity:
 * - Hash Set: Time O(n), Space O(n).
 * - Sorting:  Time O(n log n), Space O(1).
 */
public class LC0217_ContainsDuplicate {

    /**
     * Determines whether any value appears at least twice in the array.
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            // set.add(num) returns false if the element was already present
            if (!seen.add(num)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LC0217_ContainsDuplicate solver = new LC0217_ContainsDuplicate();

        // Scenario 1: Array with duplicates [1, 2, 3, 1] -> true
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Scenario 1: " + Arrays.toString(nums1));
        System.out.println("  Contains Duplicate: " + solver.containsDuplicate(nums1) + " | Expected: true\n");

        // Scenario 2: Array with all distinct elements [1, 2, 3, 4] -> false
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Scenario 2: " + Arrays.toString(nums2));
        System.out.println("  Contains Duplicate: " + solver.containsDuplicate(nums2) + " | Expected: false\n");

        // Scenario 3: Multiple occurrences and negative numbers [1, 1, 1, 3, 3, 4, 3, 2, 4, 2] -> true
        int[] nums3 = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        System.out.println("Scenario 3: " + Arrays.toString(nums3));
        System.out.println("  Contains Duplicate: " + solver.containsDuplicate(nums3) + " | Expected: true");
    }
}
