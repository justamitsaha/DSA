/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null ");
                continue;
            }
            sb.append(node.val).append(" ");
            queue.offer(node.left);
            queue.offer(node.right);
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null;

        String[] nodes = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < nodes.length; i++) {
            TreeNode parent = queue.poll();
            if (!nodes[i].equals("null")) {
                TreeNode leftChild = new TreeNode(Integer.parseInt(nodes[i]));
                parent.left = leftChild;
                queue.offer(leftChild);
            }
            if (!nodes[++i].equals("null")) {
                TreeNode rightChild = new TreeNode(Integer.parseInt(nodes[i]));
                parent.right = rightChild;
                queue.offer(rightChild);
            }
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
