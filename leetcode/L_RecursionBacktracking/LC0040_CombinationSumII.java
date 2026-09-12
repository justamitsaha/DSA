package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 40: Combination Sum II
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/combination-sum-ii/
 *
 * Algorithm:
 * - Backtracking with Duplicate Pruning (0/1 Knapsack style with Sort-Skip)
 *
 * Concepts:
 * - Given a collection of candidate numbers `candidates` (which may contain duplicates) and a target number `target`,
 *   find all unique combinations where candidate numbers sum to `target`.
 * - Each number may only be used ONCE in each combination.
 * - Crucial Difference from Combination Sum I:
 *   - Duplicate elements exist in the input array.
 *   - Each element can only be picked at most once per branch.
 * - Duplicate Avoidance Strategy:
 *   - Sort the array first: `Arrays.sort(candidates)`.
 *   - At each recursive level, loop from `start` to `candidates.length - 1`.
 *   - If `i > start && candidates[i] == candidates[i - 1]`, skip `candidates[i]` because choosing it would create
 *     identical subtrees at the same decision depth!
 *   - Recurse with `start = i + 1` (since each element can only be used once).
 *
 * Complexity:
 * - Time Complexity:  O(2^n * k) - In the worst case, generates all 2^n subsets; k is average combination length.
 * - Space Complexity: O(n)       - Maximum recursion stack depth and current combination buffer.
 */
public class LC0040_CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return answer;
        }

        // Sort to group duplicates and enable pruning
        Arrays.sort(candidates);

        List<Integer> current = new ArrayList<>();
        backtrack(0, target, current, candidates, answer);
        return answer;
    }

    private void backtrack(int start, int remaining, List<Integer> current, int[] candidates, List<List<Integer>> answer) {
        if (remaining == 0) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early pruning: subsequent candidates will exceed remaining
            if (candidates[i] > remaining) {
                break;
            }

            // Skip duplicate values at the same decision level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            current.add(candidates[i]);
            // Recurse with i + 1 because each element can only be used once
            backtrack(i + 1, remaining - candidates[i], current, candidates, answer);
            current.remove(current.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        LC0040_CombinationSumII solver = new LC0040_CombinationSumII();

        // Scenario 1: Array with duplicates
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        System.out.println("Scenario 1: " + solver.combinationSum2(candidates1, target1));
        // Expected: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]

        // Scenario 2: Duplicates summing to target
        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        System.out.println("Scenario 2: " + solver.combinationSum2(candidates2, target2));
        // Expected: [[1, 2, 2], [5]]

        // Scenario 3: All elements larger than target
        int[] candidates3 = {10, 20};
        int target3 = 5;
        System.out.println("Scenario 3: " + solver.combinationSum2(candidates3, target3));
        // Expected: []
    }
}
