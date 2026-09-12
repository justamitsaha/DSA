package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 124: Binary Tree Maximum Path Sum
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * Algorithm:
 * - Post-Order DFS with Bottom-Up Gain Accumulation
 *
 * Concepts:
 * - A path is defined as any sequence of nodes connected by edges, visiting each node at most once.
 * - The path does NOT need to pass through the root.
 * - At every node, we have two different considerations:
 *   1. Subtree Turning Point (Arch):
 *      The node serves as the apex/highest point connecting its left subtree and right subtree.
 *      `archSum = node.val + max(0, leftGain) + max(0, rightGain)`.
 *      Update the global `maxSum` with `archSum`.
 *   2. Contribution to Parent (Single Branch Extension):
 *      A path cannot branch both left and right AND continue upwards to its parent!
 *      Therefore, the node can only contribute at most one branch to its parent:
 *      `return node.val + max(0, max(leftGain, rightGain))`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Visits every node exactly once.
 * - Space Complexity: O(h) - Stack space bounded by tree height.
 */
public class LC0124_BinaryTreeMaximumPathSum {

    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Only include subtrees if they contribute positively (clamp at 0)
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // Price of the new path where `node` is the highest turn point
        int currentPathSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, currentPathSum);

        // Return the max single-branch gain extending upwards to the parent
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        LC0124_BinaryTreeMaximumPathSum solver = new LC0124_BinaryTreeMaximumPathSum();

        // Scenario 1: [1, 2, 3] -> 6 (2 + 1 + 3)
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Tree [1, 2, 3] max path sum: " + solver.maxPathSum(root1) + " (Expected: 6)");

        // Scenario 2: [-10, 9, 20, null, null, 15, 7] -> 42 (15 + 20 + 7)
        TreeNode root2 = new TreeNode(-10,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Tree [-10, 9, 20, null, null, 15, 7] max path sum: " + solver.maxPathSum(root2) + " (Expected: 42)");

        // Scenario 3: All negative values [-3] -> -3
        TreeNode root3 = new TreeNode(-3);
        System.out.println("Tree [-3] max path sum: " + solver.maxPathSum(root3) + " (Expected: -3)");
    }
}
