package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 543: Diameter of Binary Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * Algorithm:
 * - Post-Order DFS with Bottom-Up Diameter Maximization
 *
 * Concepts:
 * - The diameter of a binary tree is the length of the longest path between any two nodes in a tree,
 *   measured by the number of EDGES on the path.
 * - This path may or may not pass through the root.
 * - Key Observation:
 *   - At any node `X`, the longest path that has `X` as its highest turning point has length:
 *     `longestPathThroughX = depth(X.left) + depth(X.right)`.
 *   - Maintain a running maximum `maxDiameter`.
 *   - Return the node's own depth `1 + max(depth(left), depth(right))` to its parent.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is visited once.
 * - Space Complexity: O(h) - Where h is tree height for recursive call stack.
 */
public class LC0543_DiameterOfBinaryTree {

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        depth(root);
        return maxDiameter;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // Longest path through the current node
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // Return height of current subtree
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {
        LC0543_DiameterOfBinaryTree solver = new LC0543_DiameterOfBinaryTree();

        // Scenario 1: [1, 2, 3, 4, 5]
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        // Longest path: [4 -> 2 -> 1 -> 3] or [5 -> 2 -> 1 -> 3] -> 3 edges
        TreeNode root1 = new TreeNode(1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3)
        );
        System.out.println("Tree 1 diameter: " + solver.diameterOfBinaryTree(root1) + " (Expected: 3)");

        // Scenario 2: Two nodes [1, 2] -> 1 edge
        TreeNode root2 = new TreeNode(1, new TreeNode(2), null);
        System.out.println("Tree 2 diameter: " + solver.diameterOfBinaryTree(root2) + " (Expected: 1)");

        // Scenario 3: Single node [1] -> 0 edges
        TreeNode root3 = new TreeNode(1);
        System.out.println("Single node diameter: " + solver.diameterOfBinaryTree(root3) + " (Expected: 0)");
    }
}
