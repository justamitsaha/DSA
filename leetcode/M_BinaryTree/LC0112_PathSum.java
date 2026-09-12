package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 112: Path Sum
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/path-sum/
 *
 * Algorithm:
 * - Recursive DFS Target Subtraction (Root-to-Leaf Verification)
 *
 * Concepts:
 * - A root-to-leaf path begins at `root` and ends at any leaf node (node where both `left` and `right` are null).
 * - Target Reduction:
 *   - At each step, subtract `root.val` from `targetSum`.
 *   - When reaching a leaf node (`node.left == null && node.right == null`), check if `node.val == targetSum`.
 *   - For non-leaf nodes, recursively check if either the left or right subtree can achieve the remaining sum.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Visits each node at most once.
 * - Space Complexity: O(h) - Where h is tree height for call stack.
 */
public class LC0112_PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // Leaf node reached
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }

    public static void main(String[] args) {
        LC0112_PathSum solver = new LC0112_PathSum();

        // Scenario 1: Tree with target 22
        //               5
        //              / \
        //             4   8
        //            /   / \
        //           11  13  4
        //          /  \      \
        //         7    2      1
        TreeNode root1 = new TreeNode(5,
            new TreeNode(4,
                new TreeNode(11, new TreeNode(7), new TreeNode(2)),
                null
            ),
            new TreeNode(8,
                new TreeNode(13),
                new TreeNode(4, null, new TreeNode(1))
            )
        );
        System.out.println("Path sum 22 exists: " + solver.hasPathSum(root1, 22) + " (Expected: true)");

        // Scenario 2: Target 26 does not exist
        System.out.println("Path sum 26 exists: " + solver.hasPathSum(root1, 26) + " (Expected: false)");

        // Scenario 3: Null tree
        System.out.println("Null tree target 0: " + solver.hasPathSum(null, 0) + " (Expected: false)");

        // Scenario 4: Single node matching
        TreeNode root4 = new TreeNode(1);
        System.out.println("Single node (1) target 1: " + solver.hasPathSum(root4, 1) + " (Expected: true)");
    }
}
