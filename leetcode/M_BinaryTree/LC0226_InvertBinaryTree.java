package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 226: Invert Binary Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/invert-binary-tree/
 *
 * Algorithm:
 * - Recursive DFS Post-Order Swapping & Iterative BFS Queue Inversion
 *
 * Concepts:
 * - Inverting a binary tree (making a mirror reflection of it) involves swapping the left and right child pointers
 *   for every node in the tree.
 * - Recursive formulation:
 *   - Base Case: If node is null, return null.
 *   - Invert left subtree and right subtree recursively.
 *   - Swap `node.left` with `node.right`.
 *   - Return `node`.
 * - Iterative BFS:
 *   - Push root to queue. While not empty, poll node, swap its left and right children, and enqueue any non-null children.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Visits every node once.
 * - Space Complexity: O(h) - Recursive call stack depth bounded by tree height.
 */
public class LC0226_InvertBinaryTree {

    /**
     * Recursive DFS inversion.
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftInverted = invertTree(root.left);
        TreeNode rightInverted = invertTree(root.right);

        root.left = rightInverted;
        root.right = leftInverted;

        return root;
    }

    /**
     * Iterative BFS inversion using Queue.
     */
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // Swap left and right children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        LC0226_InvertBinaryTree solver = new LC0226_InvertBinaryTree();

        // Scenario 1: [4, 2, 7, 1, 3, 6, 9]
        //         4                 4
        //       /   \             /   \
        //      2     7    ===>   7     2
        //     / \   / \         / \   / \
        //    1   3 6   9       9   6 3   1
        TreeNode root1 = new TreeNode(4,
            new TreeNode(2, new TreeNode(1), new TreeNode(3)),
            new TreeNode(7, new TreeNode(6), new TreeNode(9))
        );

        TreeNode inverted1 = solver.invertTree(root1);
        System.out.println("Root after inversion: " + inverted1.val + " (Expected: 4)");
        System.out.println("Left child: " + inverted1.left.val + " (Expected: 7)");
        System.out.println("Right child: " + inverted1.right.val + " (Expected: 2)");
        System.out.println("Left-left child: " + inverted1.left.left.val + " (Expected: 9)");
        System.out.println("Right-right child: " + inverted1.right.right.val + " (Expected: 1)");

        // Scenario 2: Null tree
        System.out.println("Invert null: " + solver.invertTree(null) + " (Expected: null)");
    }
}
