/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class Solution {
    private boolean answer = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        preOrder(root, 0, targetSum);
        return answer;
    }

    private void preOrder(TreeNode node, int sumTillParent, int targetSum) {
        if (node != null) {
            int sumTillMe = sumTillParent + node.val;

            if (node.left != null) {
                preOrder(node.left, sumTillMe, targetSum);
            }
            if (node.right != null) {
                preOrder(node.right, sumTillMe, targetSum);
            }

            if (sumTillMe == targetSum && node.left == null && node.right == null) {
                answer = true;
            }
        }
    }
}

