package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 993: Cousins in Binary Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/cousins-in-binary-tree/
 *
 * Algorithm:
 * - Depth-First Search (DFS) / BFS with Parent & Depth Tracking
 *
 * Concepts:
 * - Two nodes in a binary tree are defined as cousins if and only if:
 *   1. They are at the SAME depth/level in the tree (`depthX == depthY`).
 *   2. They have DIFFERENT parents (`parentX != parentY`).
 * - Strategy:
 *   - Traverse the tree recursively, maintaining the current `depth` and reference to the `parent` node.
 *   - When node `x` is found, record its parent and depth.
 *   - When node `y` is found, record its parent and depth.
 *   - After traversal, verify whether `depthX == depthY && parentX != parentY`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Visits every node once (or stops early once both nodes are located).
 * - Space Complexity: O(h) - Where h is tree height for recursive stack.
 */
public class LC0993_CousinsInBinaryTree {

    private TreeNode parentX = null;
    private TreeNode parentY = null;
    private int depthX = -1;
    private int depthY = -2;

    public boolean isCousins(TreeNode root, int x, int y) {
        parentX = null;
        parentY = null;
        depthX = -1;
        depthY = -2;

        dfs(root, 0, null, x, y);

        return (depthX == depthY) && (parentX != parentY);
    }

    private void dfs(TreeNode node, int depth, TreeNode parent, int x, int y) {
        if (node == null) {
            return;
        }

        if (node.val == x) {
            parentX = parent;
            depthX = depth;
        } else if (node.val == y) {
            parentY = parent;
            depthY = depth;
        }

        // Early termination if both nodes have already been found
        if (parentX != null && parentY != null) {
            return;
        }

        dfs(node.left, depth + 1, node, x, y);
        dfs(node.right, depth + 1, node, x, y);
    }

    public static void main(String[] args) {
        LC0993_CousinsInBinaryTree solver = new LC0993_CousinsInBinaryTree();

        // Scenario 1: [1, 2, 3, 4], x = 4, y = 3 -> false (different depths)
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        TreeNode root1 = new TreeNode(1,
            new TreeNode(2, new TreeNode(4), null),
            new TreeNode(3)
        );
        System.out.println("Tree 1, x=4, y=3 cousins: " + solver.isCousins(root1, 4, 3) + " (Expected: false)");

        // Scenario 2: [1, 2, 3, null, 4, null, 5], x = 5, y = 4 -> true (same depth 2, different parents)
        //       1
        //      / \
        //     2   3
        //      \   \
        //       4   5
        TreeNode root2 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(4)),
            new TreeNode(3, null, new TreeNode(5))
        );
        System.out.println("Tree 2, x=5, y=4 cousins: " + solver.isCousins(root2, 5, 4) + " (Expected: true)");

        // Scenario 3: [1, 2, 3, null, 4], x = 2, y = 3 -> false (siblings, same parent 1)
        System.out.println("Tree 2, x=2, y=3 cousins: " + solver.isCousins(root2, 2, 3) + " (Expected: false)");
    }
}
