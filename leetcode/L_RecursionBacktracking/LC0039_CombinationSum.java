package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 39: Combination Sum
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/combination-sum/
 *
 * Algorithm:
 * - Backtracking with Pruning (Unbounded Knapsack-style Decision Tree)
 *
 * Concepts:
 * - Given an array of distinct integers `candidates` and a `target`, find all unique combinations
 *   where candidate numbers sum to `target`. The same number may be chosen an unlimited number of times.
 * - Decision Tree:
 *   - Sort `candidates` ascending to enable early pruning (stop exploring as soon as `candidates[i] > remainingTarget`).
 *   - At each step, iterate through candidates starting from index `start`:
 *     - If `candidates[i] > remainingTarget`: break immediately (pruning).
 *     - Add `candidates[i]` to `currentCombination`.
 *     - Recurse passing `start = i` (allowing the current candidate to be reused).
 *     - Backtrack (remove last added candidate).
 *   - Base Case: When `remainingTarget == 0`, add a snapshot of `currentCombination` to results.
 *
 * Complexity:
 * - Time Complexity:  O(2^t * k) - Where t is target / min(candidate) representing the maximum recursion depth,
 *                     and k is the average length of a combination.
 * - Space Complexity: O(t)       - Recursion stack depth bounded by target / min(candidate).
 */
public class LC0039_CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return answer;
        }

        // Sort candidates to enable early termination / pruning
        Arrays.sort(candidates);

        List<Integer> currentCombination = new ArrayList<>();
        backtrack(0, target, currentCombination, candidates, answer);
        return answer;
    }

    private void backtrack(int start, int remaining, List<Integer> current, int[] candidates, List<List<Integer>> answer) {
        if (remaining == 0) {
            answer.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Prune search tree: since array is sorted, all subsequent elements will also exceed remaining
            if (candidates[i] > remaining) {
                break;
            }

            current.add(candidates[i]);
            // Notice we pass 'i' (not 'i + 1') because elements can be reused
            backtrack(i, remaining - candidates[i], current, candidates, answer);
            current.remove(current.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        LC0039_CombinationSum solver = new LC0039_CombinationSum();

        // Scenario 1: Standard target
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Candidates [2, 3, 6, 7], target 7: " + solver.combinationSum(candidates1, target1));
        // Expected: [[2, 2, 3], [7]]

        // Scenario 2: Multiple combinations
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println("Candidates [2, 3, 5], target 8: " + solver.combinationSum(candidates2, target2));
        // Expected: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]

        // Scenario 3: Impossible target
        int[] candidates3 = {2};
        int target3 = 1;
        System.out.println("Candidates [2], target 1: " + solver.combinationSum(candidates3, target3));
        // Expected: []
    }
}
