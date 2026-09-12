package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 1448: Count Good Nodes in Binary Tree
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/count-good-nodes-in-binary-tree/
 *
 * Algorithm:
 * - Pre-Order DFS with Running Path Maximum Tracking
 *
 * Concepts:
 * - A node X in the binary tree is "good" if in the path from the root down to X, there are NO nodes with a value greater than X.
 * - (Equivalently, `X.val >= max(all ancestor values from root to X)`).
 * - Root is always a good node because it has no ancestors.
 * - DFS Traversal:
 *   - Carry forward `maxSoFar` representing the maximum value encountered along the path from root to current node.
 *   - If `node.val >= maxSoFar`:
 *     - This node is good! Count = 1.
 *     - Update running max: `newMax = max(maxSoFar, node.val)`.
 *   - Else: Count = 0, keep same `maxSoFar`.
 *   - Return `count + dfs(node.left, newMax) + dfs(node.right, newMax)`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is visited once.
 * - Space Complexity: O(h) - Stack frames bounded by tree height.
 */
public class LC1448_CountGoodNodesInBinaryTree {

    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.val >= maxSoFar) {
            count = 1;
            maxSoFar = node.val;
        }

        count += dfs(node.left, maxSoFar);
        count += dfs(node.right, maxSoFar);

        return count;
    }

    public static void main(String[] args) {
        LC1448_CountGoodNodesInBinaryTree solver = new LC1448_CountGoodNodesInBinaryTree();

        // Scenario 1: [3, 1, 4, 3, null, 1, 5]
        //         3*
        //        / \
        //       1   4*
        //      /   / \
        //     3*  1   5*
        // Good nodes: 3 (root), 4, 5, 3 (left-left) -> Total = 4
        TreeNode root1 = new TreeNode(3,
            new TreeNode(1, new TreeNode(3), null),
            new TreeNode(4, new TreeNode(1), new TreeNode(5))
        );
        System.out.println("Tree 1 good nodes: " + solver.goodNodes(root1) + " (Expected: 4)");

        // Scenario 2: [3, 3, null, 4, 2] -> Total = 3
        //        3*
        //       /
        //      3*
        //     / \
        //    4*  2
        TreeNode root2 = new TreeNode(3,
            new TreeNode(3, new TreeNode(4), new TreeNode(2)),
            null
        );
        System.out.println("Tree 2 good nodes: " + solver.goodNodes(root2) + " (Expected: 3)");

        // Scenario 3: Single node [1] -> Total = 1
        TreeNode root3 = new TreeNode(1);
        System.out.println("Single node good nodes: " + solver.goodNodes(root3) + " (Expected: 1)");
    }
}
