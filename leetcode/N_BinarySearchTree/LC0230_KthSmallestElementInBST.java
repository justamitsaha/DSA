package leetcode.N_BinarySearchTree;

import leetcode.common.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 230: Kth Smallest Element in a BST
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Algorithm:
 * - In-Order Traversal with Early Stopping (Iterative & Recursive)
 *
 * Concepts:
 * - A fundamental property of a Binary Search Tree (BST) is that an In-Order traversal
 *   (Left -> Node -> Right) visits node values in strictly non-decreasing sorted order!
 * - Therefore, the k-th node visited during an in-order traversal is guaranteed to be the k-th smallest element.
 * - Optimization (Early Termination):
 *   - Rather than collecting all n nodes into an array and indexing at k - 1, we can stop the traversal
 *     immediately once exactly `k` nodes have been visited.
 *   - The iterative approach using an explicit stack executes only k steps, achieving O(H + k) time.
 *
 * Complexity:
 * - Time Complexity:  O(H + k) - Where H is tree height; visits only up to the k-th node.
 * - Space Complexity: O(H)     - Stack space proportional to tree height (O(log n) balanced, O(n) skewed).
 */
public class LC0230_KthSmallestElementInBST {

    private int count = 0;
    private int result = -1;

    /**
     * Recursive In-Order Traversal with early stopping.
     */
    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = -1;
        inOrder(root, k);
        return result;
    }

    private void inOrder(TreeNode node, int k) {
        if (node == null || result != -1) {
            return;
        }

        inOrder(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inOrder(node.right, k);
    }

    /**
     * Iterative In-Order Traversal using an explicit stack (O(H + k) time, stops exactly at step k).
     */
    public int kthSmallestIterative(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            k--;
            if (k == 0) {
                return curr.val;
            }

            curr = curr.right;
        }

        return -1;
    }

    public static void main(String[] args) {
        LC0230_KthSmallestElementInBST solver = new LC0230_KthSmallestElementInBST();

        // Scenario 1: [3, 1, 4, null, 2], k = 1
        //       3
        //      / \
        //     1   4
        //      \
        //       2
        TreeNode root1 = new TreeNode(3,
            new TreeNode(1, null, new TreeNode(2)),
            new TreeNode(4)
        );
        System.out.println("Tree 1, k = 1: " + solver.kthSmallest(root1, 1) + " (Expected: 1)");
        System.out.println("Tree 1, k = 1 (Iterative): " + solver.kthSmallestIterative(root1, 1) + " (Expected: 1)");

        // Scenario 2: [5, 3, 6, 2, 4, null, null, 1], k = 3
        //         5
        //        / \
        //       3   6
        //      / \
        //     2   4
        //    /
        //   1
        TreeNode root2 = new TreeNode(5,
            new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
            new TreeNode(6)
        );
        System.out.println("Tree 2, k = 3: " + solver.kthSmallest(root2, 3) + " (Expected: 3)");
        System.out.println("Tree 2, k = 3 (Iterative): " + solver.kthSmallestIterative(root2, 3) + " (Expected: 3)");

        // Scenario 3: k = 5 (largest element in 5-node tree)
        System.out.println("Tree 2, k = 5: " + solver.kthSmallest(root2, 5) + " (Expected: 5)");
    }
}
