package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 101: Symmetric Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/symmetric-tree/
 *
 * Algorithm:
 * - Mirror Reflection Check (Recursive & Iterative BFS)
 *
 * Concepts:
 * - A tree is symmetric (a mirror of itself around its center) if:
 *   1. Their root values are equal.
 *   2. The left subtree of the left tree is a mirror of the right subtree of the right tree (`t1.left` == `t2.right`).
 *   3. The right subtree of the left tree is a mirror of the left subtree of the right tree (`t1.right` == `t2.left`).
 * - Can be implemented recursively via a 2-node helper or iteratively via a queue comparing pairs.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Visits every node once.
 * - Space Complexity: O(h) - Recursive call stack or queue size bounded by tree height / width.
 */
public class LC0101_SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
    }

    /**
     * Iterative BFS check using Queue comparing nodes pairwise.
     */
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }

            // Cross-pair children to verify reflection
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }

        return true;
    }

    public static void main(String[] args) {
        LC0101_SymmetricTree solver = new LC0101_SymmetricTree();

        // Scenario 1: Symmetric [1, 2, 2, 3, 4, 4, 3]
        //         1
        //       /   \
        //      2     2
        //     / \   / \
        //    3   4 4   3
        TreeNode root1 = new TreeNode(1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3))
        );
        System.out.println("Tree 1 symmetric: " + solver.isSymmetric(root1) + " (Expected: true)");
        System.out.println("Tree 1 symmetric (Iterative): " + solver.isSymmetricIterative(root1) + " (Expected: true)");

        // Scenario 2: Asymmetric [1, 2, 2, null, 3, null, 3]
        //         1
        //       /   \
        //      2     2
        //       \     \
        //        3     3
        TreeNode root2 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(3)),
            new TreeNode(2, null, new TreeNode(3))
        );
        System.out.println("Tree 2 symmetric: " + solver.isSymmetric(root2) + " (Expected: false)");
        System.out.println("Tree 2 symmetric (Iterative): " + solver.isSymmetricIterative(root2) + " (Expected: false)");
    }
}
