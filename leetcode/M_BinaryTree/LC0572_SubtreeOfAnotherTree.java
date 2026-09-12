package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 572: Subtree of Another Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/subtree-of-another-tree/
 *
 * Algorithm:
 * - Recursive Tree Matcher with Subtree Identity Verification
 *
 * Concepts:
 * - A tree `subRoot` is a subtree of `root` if there exists a node `N` in `root` such that
 *   the subtree rooted at `N` is structurally and value-wise identical to `subRoot` (including all descendants).
 * - Formulation:
 *   1. Base Case: If `root == null`, it cannot contain `subRoot` -> return false.
 *   2. If the current tree at `root` is identical to `subRoot` (`isSameTree(root, subRoot)`), return true.
 *   3. Otherwise, search recursively in the left child OR the right child:
 *      `isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot)`.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - In the worst case, comparing subtrees takes O(n) for each of the m nodes in root.
 * - Space Complexity: O(h_root) - Call stack bounded by root tree height.
 */
public class LC0572_SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }

        return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    public static void main(String[] args) {
        LC0572_SubtreeOfAnotherTree solver = new LC0572_SubtreeOfAnotherTree();

        // Scenario 1: Valid subtree
        // Main tree: [3, 4, 5, 1, 2]
        // Subtree: [4, 1, 2]
        TreeNode root1 = new TreeNode(3,
            new TreeNode(4, new TreeNode(1), new TreeNode(2)),
            new TreeNode(5)
        );
        TreeNode sub1 = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        System.out.println("Scenario 1 isSubtree: " + solver.isSubtree(root1, sub1) + " (Expected: true)");

        // Scenario 2: Invalid subtree (extra node attached to 2)
        // Main tree: [3, 4, 5, 1, 2, null, null, null, null, 0]
        TreeNode root2 = new TreeNode(3,
            new TreeNode(4,
                new TreeNode(1),
                new TreeNode(2, new TreeNode(0), null)
            ),
            new TreeNode(5)
        );
        System.out.println("Scenario 2 isSubtree: " + solver.isSubtree(root2, sub1) + " (Expected: false)");

        // Scenario 3: Null subRoot
        System.out.println("Scenario 3 (null main): " + solver.isSubtree(null, sub1) + " (Expected: false)");
    }
}
