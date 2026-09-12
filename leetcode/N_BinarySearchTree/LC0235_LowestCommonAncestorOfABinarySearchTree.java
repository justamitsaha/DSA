package leetcode.N_BinarySearchTree;

import leetcode.common.TreeNode;

/**
 * LeetCode 235: Lowest Common Ancestor of a Binary Search Tree
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Algorithm:
 * - BST Split-Point Traversal (Iterative & Recursive)
 *
 * Concepts:
 * - In a Binary Search Tree, we can find the LCA in O(H) time without traversing both subtrees by exploiting the BST ordering:
 *   - Case 1: Both `p` and `q` have values smaller than `root.val`:
 *     Both nodes lie strictly in the left subtree -> recurse/step to `root.left`.
 *   - Case 2: Both `p` and `q` have values greater than `root.val`:
 *     Both nodes lie strictly in the right subtree -> recurse/step to `root.right`.
 *   - Case 3: The "Split Point":
 *     One node is on the left and the other on the right, OR one of the nodes is the current `root` itself.
 *     At this point, `root` is the Lowest Common Ancestor!
 *
 * Complexity:
 * - Time Complexity:  O(H) - Where H is the tree height (O(log n) balanced, O(n) skewed).
 * - Space Complexity: O(1) for the iterative implementation; O(H) for recursive stack.
 */
public class LC0235_LowestCommonAncestorOfABinarySearchTree {

    /**
     * Iterative traversal (O(1) auxiliary space).
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else {
                return curr; // Found the split point
            }
        }

        return null;
    }

    /**
     * Recursive approach.
     */
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestorRecursive(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestorRecursive(root.right, p, q);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        LC0235_LowestCommonAncestorOfABinarySearchTree solver = new LC0235_LowestCommonAncestorOfABinarySearchTree();

        // Construct BST:
        //               6
        //             /   \
        //            2     8
        //           / \   / \
        //          0   4 7   9
        //             / \
        //            3   5
        TreeNode node0 = new TreeNode(0);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4, node3, node5);
        TreeNode node2 = new TreeNode(2, node0, node4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node8 = new TreeNode(8, node7, node9);
        TreeNode root = new TreeNode(6, node2, node8);

        // Scenario 1: LCA of 2 and 8 is 6 (split across root)
        TreeNode lca1 = solver.lowestCommonAncestor(root, node2, node8);
        System.out.println("LCA of 2 and 8: " + (lca1 != null ? lca1.val : "null") + " (Expected: 6)");

        // Scenario 2: LCA of 2 and 4 is 2 (ancestor is one of the nodes)
        TreeNode lca2 = solver.lowestCommonAncestor(root, node2, node4);
        System.out.println("LCA of 2 and 4: " + (lca2 != null ? lca2.val : "null") + " (Expected: 2)");

        // Scenario 3: LCA of 3 and 5 is 4 (both in left subtree of root)
        TreeNode lca3 = solver.lowestCommonAncestor(root, node3, node5);
        System.out.println("LCA of 3 and 5: " + (lca3 != null ? lca3.val : "null") + " (Expected: 4)");
    }
}
