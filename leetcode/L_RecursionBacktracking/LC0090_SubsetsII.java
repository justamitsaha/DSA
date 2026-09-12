package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 90: Subsets II
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/subsets-ii/
 *
 * Algorithm:
 * - Backtracking with Duplicate Pruning (Sort + Skip Adjacent Identical Elements)
 *
 * Concepts:
 * - Given an integer array `nums` that may contain DUPLICATES, return all possible subsets (power set) without duplicates.
 * - Why simple backtracking produces duplicates:
 *   - In `[1, 2, 2]`, picking the first '2' then stopping gives `[1, 2]`. Skipping the first '2' and picking the second '2'
 *     also produces `[1, 2]`.
 * - Optimal Duplicate Pruning:
 *   - Sort `nums` first so duplicate numbers become contiguous.
 *   - In the recursive backtracking loop over index `i` from `start` to `nums.length - 1`:
 *     - If `i > start && nums[i] == nums[i - 1]`, continue (skip duplicate siblings at the same recursion depth).
 *     - Add `nums[i]` to `current`, recurse with `start = i + 1`, then backtrack.
 *   - This generates every unique subset directly without needing an expensive `HashSet<List<Integer>>`.
 *
 * Complexity:
 * - Time Complexity:  O(2^n * n) - At most 2^n subsets generated; copying each subset takes O(n).
 * - Space Complexity: O(n)       - Recursion stack depth is n.
 */
public class LC0090_SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }

        // Sort to bring duplicates together
        Arrays.sort(nums);

        List<Integer> current = new ArrayList<>();
        backtrack(nums, 0, current, result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // Every combination prefix is a unique subset
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicate sibling branches at the current recursion depth
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        LC0090_SubsetsII solver = new LC0090_SubsetsII();

        // Scenario 1: Array with duplicates [1, 2, 2]
        int[] nums1 = {1, 2, 2};
        System.out.println("Subsets of [1, 2, 2]: " + solver.subsetsWithDup(nums1));
        // Expected: [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]] (6 subsets instead of 2^3=8)

        // Scenario 2: Single element with duplicates [0]
        int[] nums2 = {0};
        System.out.println("Subsets of [0]: " + solver.subsetsWithDup(nums2));
        // Expected: [[], [0]]

        // Scenario 3: All identical elements [4, 4, 4, 1, 4]
        int[] nums3 = {4, 4, 4, 1, 4};
        List<List<Integer>> res3 = solver.subsetsWithDup(nums3);
        System.out.println("Subsets count of [4, 4, 4, 1, 4]: " + res3.size() + " (Expected: 10)");
    }
}
