package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 106: Construct Binary Tree from Inorder and Postorder Traversal
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * Algorithm:
 * - Divide and Conquer with Hash Map Indexing (Optimal O(n))
 *
 * Concepts:
 * - Traversal properties:
 *   - Inorder:   [Left Subtree] -> Root -> [Right Subtree]
 *   - Postorder: [Left Subtree] -> [Right Subtree] -> Root
 * - The LAST element of `postorder` is always the root of the current subtree.
 * - By looking up this root value in `inorder`:
 *   - All elements to its left belong to the LEFT subtree.
 *   - All elements to its right belong to the RIGHT subtree.
 * - Postorder Traversal Order:
 *   - Because postorder processes Left -> Right -> Root, traversing backwards from the end of `postorder`
 *     encounters nodes in Root -> Right -> Left order!
 *   - Therefore, when recursing, we must build `root.right` BEFORE `root.left`!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Building HashMap takes O(n); each node is constructed in O(1).
 * - Space Complexity: O(n) - HashMap takes O(n); recursion stack takes O(h).
 */
public class LC0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

    private int postIndex;
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }

        postIndex = postorder.length - 1;
        inorderIndexMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildSubtree(postorder, 0, inorder.length - 1);
    }

    private TreeNode buildSubtree(int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }

        // The current root is at postorder[postIndex]
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        int rootIndexInInorder = inorderIndexMap.get(rootVal);

        // Crucial: Must construct RIGHT subtree before LEFT subtree when consuming postorder backwards
        root.right = buildSubtree(postorder, rootIndexInInorder + 1, inRight);
        root.left = buildSubtree(postorder, inLeft, rootIndexInInorder - 1);

        return root;
    }

    public static void main(String[] args) {
        LC0106_ConstructBinaryTreeFromInorderAndPostorderTraversal solver =
            new LC0106_ConstructBinaryTreeFromInorderAndPostorderTraversal();

        // Scenario 1: inorder = [9, 3, 15, 20, 7], postorder = [9, 15, 7, 20, 3]
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};
        TreeNode root1 = solver.buildTree(inorder1, postorder1);
        System.out.println("Root: " + (root1 != null ? root1.val : "null") + " (Expected: 3)");
        System.out.println("Left child: " + (root1.left != null ? root1.left.val : "null") + " (Expected: 9)");
        System.out.println("Right child: " + (root1.right != null ? root1.right.val : "null") + " (Expected: 20)");

        // Scenario 2: Single node
        int[] inorder2 = {-1};
        int[] postorder2 = {-1};
        TreeNode root2 = solver.buildTree(inorder2, postorder2);
        System.out.println("Single node root: " + root2.val + " (Expected: -1)");
    }
}
