package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 110: Balanced Binary Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/balanced-binary-tree/
 *
 * Algorithm:
 * - Bottom-Up DFS with Sentinel Early-Termination (-1)
 *
 * Concepts:
 * - A binary tree is height-balanced if the left and right subtrees of every node differ in height by at most 1.
 * - Naive Top-Down approach: compute height for every node -> O(n^2) worst case.
 * - Optimal Bottom-Up approach:
 *   - Helper function returns the height of the subtree if it is balanced.
 *   - If any subtree is found to be unbalanced (or its child returned -1), immediately propagate `-1` upwards.
 *   - At the root: if height >= 0, the tree is balanced; if -1, it is unbalanced.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Each node is visited only once.
 * - Space Complexity: O(h) - Stack frames bounded by tree height (O(log n) balanced, O(n) skewed).
 */
public class LC0110_BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is unbalanced
        }

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is unbalanced
        }

        // Current node balance violation
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        LC0110_BalancedBinaryTree solver = new LC0110_BalancedBinaryTree();

        // Scenario 1: Balanced tree [3, 9, 20, null, null, 15, 7]
        TreeNode root1 = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Tree 1 balanced: " + solver.isBalanced(root1) + " (Expected: true)");

        // Scenario 2: Unbalanced tree [1, 2, 2, 3, 3, null, null, 4, 4]
        TreeNode root2 = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(3, new TreeNode(4), new TreeNode(4)),
                new TreeNode(3)
            ),
            new TreeNode(2)
        );
        System.out.println("Tree 2 balanced: " + solver.isBalanced(root2) + " (Expected: false)");

        // Scenario 3: Empty tree
        System.out.println("Empty tree balanced: " + solver.isBalanced(null) + " (Expected: true)");
    }
}
