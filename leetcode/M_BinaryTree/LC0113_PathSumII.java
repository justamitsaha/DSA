package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 113: Path Sum II
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/path-sum-ii/
 *
 * Algorithm:
 * - DFS Tree Traversal with Backtracking (Root-to-Leaf Path Collection)
 *
 * Concepts:
 * - Return ALL root-to-leaf paths where the sum of the node values equals `targetSum`.
 * - Backtracking Pattern:
 *   1. Push current node's value onto `currentPath`.
 *   2. Base Case: If the current node is a leaf and its value equals the remaining sum,
 *      copy the entire path snapshot into `answer`.
 *   3. Recursive exploration: Search left and right subtrees with `remainingSum - node.val`.
 *   4. Backtrack: Remove the last node from `currentPath` before returning to the parent.
 *
 * Complexity:
 * - Time Complexity:  O(n * h) - In the worst case (full binary tree with all paths valid), copying paths takes O(h).
 * - Space Complexity: O(h)     - Recursion stack and current path buffer bounded by tree height.
 */
public class LC0113_PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }

        List<Integer> currentPath = new ArrayList<>();
        findPaths(root, targetSum, currentPath, answer);
        return answer;
    }

    private void findPaths(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> answer) {
        if (node == null) {
            return;
        }

        currentPath.add(node.val);

        // Check if we reached a leaf and the sum matches
        if (node.left == null && node.right == null && remainingSum == node.val) {
            answer.add(new ArrayList<>(currentPath));
        } else {
            findPaths(node.left, remainingSum - node.val, currentPath, answer);
            findPaths(node.right, remainingSum - node.val, currentPath, answer);
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        LC0113_PathSumII solver = new LC0113_PathSumII();

        // Scenario 1: Multiple matching paths for target 22
        //               5
        //              / \
        //             4   8
        //            /   / \
        //           11  13  4
        //          /  \    / \
        //         7    2  5   1
        TreeNode root1 = new TreeNode(5,
            new TreeNode(4,
                new TreeNode(11, new TreeNode(7), new TreeNode(2)),
                null
            ),
            new TreeNode(8,
                new TreeNode(13),
                new TreeNode(4, new TreeNode(5), new TreeNode(1))
            )
        );
        System.out.println("Paths with sum 22: " + solver.pathSum(root1, 22));
        // Expected: [[5, 4, 11, 2], [5, 8, 4, 5]]

        // Scenario 2: No valid path
        System.out.println("Paths with sum 100: " + solver.pathSum(root1, 100));
        // Expected: []
    }
}
