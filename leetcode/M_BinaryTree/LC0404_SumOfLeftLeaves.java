package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 404: Sum of Left Leaves
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/sum-of-left-leaves/
 *
 * Algorithm:
 * - Recursive DFS Tree Traversal with Left-Child Validation
 *
 * Concepts:
 * - A left leaf is a node that:
 *   1. Has no children (both `left == null` and `right == null`).
 *   2. Is the LEFT child of its parent node.
 * - Single-Pass Identification:
 *   - At each node, inspect its left child:
 *     - If `node.left` is a leaf (`node.left.left == null && node.left.right == null`),
 *       add `node.left.val` directly to the sum!
 *     - If `node.left` is not a leaf, recursively accumulate left leaves within `node.left`.
 *   - Always recursively accumulate left leaves within `node.right`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Visits every node once.
 * - Space Complexity: O(h) - Call stack bounded by tree height.
 */
public class LC0404_SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        // Check if the left child is a leaf
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                sum += sumOfLeftLeaves(root.left);
            }
        }

        // Always recurse on the right child
        sum += sumOfLeftLeaves(root.right);

        return sum;
    }

    public static void main(String[] args) {
        LC0404_SumOfLeftLeaves solver = new LC0404_SumOfLeftLeaves();

        // Scenario 1: [3, 9, 20, null, null, 15, 7]
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        // Left leaves: 9 and 15 -> sum = 24
        TreeNode root1 = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Tree 1 sum of left leaves: " + solver.sumOfLeftLeaves(root1) + " (Expected: 24)");

        // Scenario 2: Single node [1] -> No left child, sum = 0
        TreeNode root2 = new TreeNode(1);
        System.out.println("Single node [1]: " + solver.sumOfLeftLeaves(root2) + " (Expected: 0)");

        // Scenario 3: Only right children [1, null, 2] -> sum = 0
        TreeNode root3 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("Right-skewed tree: " + solver.sumOfLeftLeaves(root3) + " (Expected: 0)");
    }
}
