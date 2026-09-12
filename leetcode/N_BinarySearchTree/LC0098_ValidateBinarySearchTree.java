package leetcode.N_BinarySearchTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 98: Validate Binary Search Tree
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Algorithm:
 * - Method 1: Range-Bound Recursive Validation (Min/Max Envelope)
 * - Method 2: In-Order Traversal Monotonicity Check
 *
 * Concepts:
 * - A valid BST is defined as follows:
 *   - The left subtree of a node contains only nodes with keys strictly less than the node's key.
 *   - The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 *   - Both the left and right subtrees must also be binary search trees.
 * - Common Trap:
 *   - Simply checking `node.left.val < node.val` and `node.right.val > node.val` is INSUFFICIENT!
 *     All nodes in the left subtree must be less than all ancestors above them.
 * - Range Envelope:
 *   - Each node must satisfy: `lower < node.val < upper`.
 *   - Left child inherits bounds: `(lower, node.val)`.
 *   - Right child inherits bounds: `(node.val, upper)`.
 *   - We use `Long` for bounds to safely handle nodes whose values equal `Integer.MIN_VALUE` or `Integer.MAX_VALUE`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is visited once.
 * - Space Complexity: O(h) - Where h is the tree height (O(log n) balanced, O(n) skewed).
 */
public class LC0098_ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode node, Long lower, Long upper) {
        if (node == null) {
            return true;
        }

        if (lower != null && node.val <= lower) {
            return false;
        }
        if (upper != null && node.val >= upper) {
            return false;
        }

        return isValid(node.left, lower, (long) node.val)
            && isValid(node.right, (long) node.val, upper);
    }

    public static void main(String[] args) {
        LC0098_ValidateBinarySearchTree solver = new LC0098_ValidateBinarySearchTree();

        // Scenario 1: Valid BST [2, 1, 3]
        //       2
        //      / \
        //     1   3
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println("Tree [2, 1, 3] valid BST: " + solver.isValidBST(root1) + " (Expected: true)");

        // Scenario 2: Invalid BST [5, 1, 4, null, null, 3, 6]
        //       5
        //      / \
        //     1   4
        //        / \
        //       3   6
        TreeNode root2 = new TreeNode(5,
            new TreeNode(1),
            new TreeNode(4, new TreeNode(3), new TreeNode(6))
        );
        System.out.println("Tree [5, 1, 4, null, null, 3, 6] valid BST: " + solver.isValidBST(root2) + " (Expected: false)");

        // Scenario 3: Single node
        TreeNode root3 = new TreeNode(Integer.MAX_VALUE);
        System.out.println("Single node [MAX_VALUE]: " + solver.isValidBST(root3) + " (Expected: true)");

        // Scenario 4: Subtree violation (right child of left subtree > root)
        //       5
        //      / \
        //     4   6
        //      \
        //       7 (invalid: 7 > 5)
        TreeNode root4 = new TreeNode(5,
            new TreeNode(4, null, new TreeNode(7)),
            new TreeNode(6)
        );
        System.out.println("Tree with ancestor violation: " + solver.isValidBST(root4) + " (Expected: false)");
    }
}
