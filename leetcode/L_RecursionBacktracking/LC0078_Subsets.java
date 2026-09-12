package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 78: Subsets
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/subsets/
 *
 * Algorithm:
 * - Backtracking / Cascading / Bit Manipulation (Power Set Generation)
 *
 * Concepts:
 * - Given an integer array `nums` of UNIQUE elements, return all possible subsets (the power set).
 * - Total subsets for a set of size n is 2^n.
 * - Backtracking Formulation:
 *   - At each level, every intermediate prefix `current` is a valid subset: snapshot `current` into `subsets`.
 *   - Iterate `i` from `start` to `nums.length - 1`:
 *     - Include `nums[i]` in `current`.
 *     - Recurse on remaining elements (`start = i + 1`).
 *     - Backtrack (remove `nums[i]`).
 *
 * Complexity:
 * - Time Complexity:  O(2^n * n) - 2^n subsets generated, each taking O(n) to copy into result list.
 * - Space Complexity: O(n)       - Maximum recursion depth is n.
 */
public class LC0078_Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }

        List<Integer> current = new ArrayList<>();
        backtrack(nums, 0, current, result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Every prefix generated along the path is a valid subset
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }

    /**
     * Alternative: Binary Decision Tree (Include / Exclude at each index).
     */
    public List<List<Integer>> subsetsIncludeExclude(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackBinary(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrackBinary(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Choice 1: Exclude nums[index]
        backtrackBinary(nums, index + 1, current, result);

        // Choice 2: Include nums[index]
        current.add(nums[index]);
        backtrackBinary(nums, index + 1, current, result);
        current.remove(current.size() - 1); // backtrack
    }

    public static void main(String[] args) {
        LC0078_Subsets solver = new LC0078_Subsets();

        // Scenario 1: 3 elements (2^3 = 8 subsets)
        int[] nums1 = {1, 2, 3};
        System.out.println("Subsets of [1, 2, 3]: " + solver.subsets(nums1));
        // Expected: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]

        // Scenario 2: Single element
        int[] nums2 = {0};
        System.out.println("Subsets of [0]: " + solver.subsets(nums2));
        // Expected: [[], [0]]

        // Scenario 3: Empty array
        int[] nums3 = {};
        System.out.println("Subsets of []: " + solver.subsets(nums3));
        // Expected: [[]]
    }
}
