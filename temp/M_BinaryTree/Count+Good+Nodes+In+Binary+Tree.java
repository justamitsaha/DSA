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
    int count = 0;
    public int goodNodes(TreeNode root) {
        f(root, null);
        return count;
    }
    
    public void f(TreeNode node, Integer maxTillParent) {
        if (node != null) {
            int maxTillMe = node.val;
            if (maxTillParent == null) {
                count++;
            }
            else {
                if (node.val >= maxTillParent) {
                    count++;
                }
                maxTillMe = Math.max(maxTillMe, maxTillParent);
            }
            f(node.left, maxTillMe);
            f(node.right, maxTillMe);
        }
    }
}
