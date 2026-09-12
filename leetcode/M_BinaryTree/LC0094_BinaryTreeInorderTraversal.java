package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 94: Binary Tree Inorder Traversal
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * Algorithm:
 * - In-order Traversal (Left -> Root -> Right) via Recursion and Iterative Stack
 *
 * Concepts:
 * - Recursive: Recurse on left subtree, process root, recurse on right subtree.
 * - Iterative (Stack): Push current node and all its left descendants onto stack until null.
 *   Then pop, process node, and set `curr = curr.right`.
 * - Morris Traversal (O(1) space): Uses threaded binary tree representation to achieve O(n) time and O(1) space.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is visited once.
 * - Space Complexity: O(h) - Where h is tree height for stack frames (O(n) worst, O(log n) balanced).
 */
public class LC0094_BinaryTreeInorderTraversal {

    /**
     * Recursive Inorder Traversal.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        inorder(root, answer);
        return answer;
    }

    private void inorder(TreeNode node, List<Integer> answer) {
        if (node != null) {
            inorder(node.left, answer);
            answer.add(node.val);
            inorder(node.right, answer);
        }
    }

    /**
     * Iterative Inorder Traversal using explicit stack.
     */
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            answer.add(curr.val);
            curr = curr.right;
        }

        return answer;
    }

    public static void main(String[] args) {
        LC0094_BinaryTreeInorderTraversal solver = new LC0094_BinaryTreeInorderTraversal();

        // Scenario 1: [1, null, 2, 3]
        //   1
        //    \
        //     2
        //    /
        //   3
        TreeNode root1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println("Scenario 1: " + solver.inorderTraversal(root1) + " (Expected: [1, 3, 2])");
        System.out.println("Scenario 1 (Iterative): " + solver.inorderTraversalIterative(root1) + " (Expected: [1, 3, 2])");

        // Scenario 2: Empty tree
        System.out.println("Scenario 2 (empty): " + solver.inorderTraversal(null) + " (Expected: [])");

        // Scenario 3: Single node
        System.out.println("Scenario 3 (single): " + solver.inorderTraversal(new TreeNode(1)) + " (Expected: [1])");
    }
}
