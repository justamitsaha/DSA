package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 100: Same Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/same-tree/
 *
 * Algorithm:
 * - Simultaneous Recursive Tree Traversal (Structural & Value Equivalence)
 *
 * Concepts:
 * - Two binary trees are considered identical if they are structurally equivalent and corresponding nodes have identical values.
 * - Base Cases:
 *   - If both nodes are null: `return true` (matched empty subtree).
 *   - If only one is null: `return false` (structural mismatch).
 *   - If values differ: `return false` (value mismatch).
 * - Induction Step:
 *   - Both left subtrees must be identical AND both right subtrees must be identical.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Where n is the minimum number of nodes in either tree.
 * - Space Complexity: O(h) - Stack frames bounded by the tree height (O(log n) balanced, O(n) skewed).
 */
public class LC0100_SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both empty -> identical
        if (p == null && q == null) {
            return true;
        }

        // Structural mismatch: one is null, other is not
        if (p == null || q == null) {
            return false;
        }

        // Value mismatch
        if (p.val != q.val) {
            return false;
        }

        // Recurse on left and right children
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        LC0100_SameTree solver = new LC0100_SameTree();

        // Scenario 1: Identical trees [1, 2, 3] and [1, 2, 3]
        TreeNode p1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Scenario 1: " + solver.isSameTree(p1, q1) + " (Expected: true)");

        // Scenario 2: Structurally different [1, 2] vs [1, null, 2]
        TreeNode p2 = new TreeNode(1, new TreeNode(2), null);
        TreeNode q2 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("Scenario 2: " + solver.isSameTree(p2, q2) + " (Expected: false)");

        // Scenario 3: Values differ [1, 2, 1] vs [1, 1, 2]
        TreeNode p3 = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        TreeNode q3 = new TreeNode(1, new TreeNode(1), new TreeNode(2));
        System.out.println("Scenario 3: " + solver.isSameTree(p3, q3) + " (Expected: false)");

        // Scenario 4: Both null
        System.out.println("Scenario 4 (both null): " + solver.isSameTree(null, null) + " (Expected: true)");
    }
}
