package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 46: Permutations
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/permutations/
 *
 * Algorithm:
 * - Backtracking with Visited Tracking (State-Space Tree Search)
 *
 * Concepts:
 * - Given an array `nums` of distinct integers, return all possible permutations.
 * - Total permutations of n distinct elements is n!.
 * - Decision Tree:
 *   - At each recursion level, select an unused element from `nums`.
 *   - Track whether an element has been used via a `boolean[] used` array.
 *   - Add the candidate to `currentPermutation`, mark `used[i] = true`.
 *   - Recurse to depth + 1.
 *   - Backtrack: remove last element and unmark `used[i] = false`.
 *   - Base case: When `currentPermutation.size() == nums.length`, a full permutation has been constructed.
 *
 * Complexity:
 * - Time Complexity:  O(n! * n) - There are n! leaf nodes, and copying each permutation takes O(n).
 * - Space Complexity: O(n)       - Auxiliary space for `used` array, current list, and call stack depth.
 */
public class LC0046_Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return answer;
        }

        boolean[] used = new boolean[nums.length];
        List<Integer> current = new ArrayList<>();
        backtrack(nums, used, current, answer);
        return answer;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> answer) {
        if (current.size() == nums.length) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);

                backtrack(nums, used, current, answer);

                // Backtrack
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        LC0046_Permutations solver = new LC0046_Permutations();

        // Scenario 1: 3 elements (3! = 6 permutations)
        int[] nums1 = {1, 2, 3};
        System.out.println("Permutations of [1, 2, 3]: " + solver.permute(nums1));
        // Expected: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

        // Scenario 2: 2 elements (2! = 2 permutations)
        int[] nums2 = {0, 1};
        System.out.println("Permutations of [0, 1]: " + solver.permute(nums2));
        // Expected: [[0, 1], [1, 0]]

        // Scenario 3: Single element (1! = 1 permutation)
        int[] nums3 = {1};
        System.out.println("Permutations of [1]: " + solver.permute(nums3));
        // Expected: [[1]]
    }
}
