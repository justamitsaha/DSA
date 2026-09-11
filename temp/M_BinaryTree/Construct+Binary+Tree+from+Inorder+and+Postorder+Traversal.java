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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> positionMp = new HashMap<>();
        for(int i = 0; i < postorder.length; i++) {
            positionMp.put(postorder[i], i);
        }

        return constructTree(inorder, positionMp, 0, inorder.length - 1);
    }

    public TreeNode constructTree(int[] inorder, Map<Integer, Integer> positionMap, int left, int right) {
        if (left > right) {
            return null;
        }

        int mostPositionNumber = inorder[left];
        int mostPosition = positionMap.get(inorder[left]);
        int mostPositionInInorder = left;

        for(int i = left + 1; i <= right; i++) {
            int currentPosition = positionMap.get(inorder[i]);
            if (currentPosition > mostPosition) {
                mostPosition = currentPosition;
                mostPositionNumber = inorder[i];
                mostPositionInInorder = i;
            }
        }

        TreeNode node = new TreeNode(mostPositionNumber);
        node.left = constructTree(inorder, positionMap, left, mostPositionInInorder - 1);
        node.right = constructTree(inorder, positionMap, mostPositionInInorder + 1, right);

        return node;
    }
}
