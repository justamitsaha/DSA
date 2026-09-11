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
class Solution {
    public boolean isBalanced(TreeNode root) {
        int rootHeight = height(root);
        return rootHeight != -1 ? true : false;
    }
    
    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            
            if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            }
            
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
