package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 297: Serialize and Deserialize Binary Tree
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Algorithm:
 * - Method 1: Level-Order BFS Serialization with Delimiters & Null Sentinels
 * - Method 2: Pre-Order DFS Serialization (Root -> Left -> Right)
 *
 * Concepts:
 * - Serialization is converting a data structure into a sequence of bits/string so that it can be stored in a file or
 *   memory buffer, or transmitted across a network connection link.
 * - Deserialization reconstructs the original tree from the string.
 * - Key to Unambiguous Reconstruction:
 *   - Recording explicit null markers (`"null"`) for missing children ensures that a single traversal
 *     (e.g., BFS or Pre-Order DFS) can uniquely reconstruct the binary tree without needing both inorder and preorder!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Both serialization and deserialization visit each node/null token once.
 * - Space Complexity: O(n) - Memory for the serialized string and queues.
 */
public class LC0297_SerializeAndDeserializeBinaryTree {

    private static final String NULL_NODE = "null";
    private static final String DELIMITER = ",";

    // Encodes a tree to a single string using Level-Order BFS.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (curr == null) {
                sb.append(NULL_NODE).append(DELIMITER);
            } else {
                sb.append(curr.val).append(DELIMITER);
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree using Level-Order BFS.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] tokens = data.split(DELIMITER);
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < tokens.length) {
            TreeNode parent = queue.poll();

            // Process left child
            if (!tokens[i].equals(NULL_NODE)) {
                TreeNode leftChild = new TreeNode(Integer.parseInt(tokens[i]));
                parent.left = leftChild;
                queue.offer(leftChild);
            }
            i++;

            // Process right child
            if (i < tokens.length && !tokens[i].equals(NULL_NODE)) {
                TreeNode rightChild = new TreeNode(Integer.parseInt(tokens[i]));
                parent.right = rightChild;
                queue.offer(rightChild);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        LC0297_SerializeAndDeserializeBinaryTree codec = new LC0297_SerializeAndDeserializeBinaryTree();

        // Scenario 1: [1, 2, 3, null, null, 4, 5]
        //       1
        //      / \
        //     2   3
        //        / \
        //       4   5
        TreeNode root1 = new TreeNode(1,
            new TreeNode(2),
            new TreeNode(3, new TreeNode(4), new TreeNode(5))
        );

        String serialized1 = codec.serialize(root1);
        System.out.println("Serialized: " + serialized1);

        TreeNode deserialized1 = codec.deserialize(serialized1);
        System.out.println("Deserialized Root: " + (deserialized1 != null ? deserialized1.val : "null") + " (Expected: 1)");
        System.out.println("Deserialized Left: " + deserialized1.left.val + " (Expected: 2)");
        System.out.println("Deserialized Right-Left: " + deserialized1.right.left.val + " (Expected: 4)");
        System.out.println("Deserialized Right-Right: " + deserialized1.right.right.val + " (Expected: 5)");

        // Scenario 2: Empty tree
        String serializedNull = codec.serialize(null);
        System.out.println("Empty tree serialized: \"" + serializedNull + "\"");
        TreeNode deserializedNull = codec.deserialize(serializedNull);
        System.out.println("Empty tree deserialized: " + deserializedNull + " (Expected: null)");
    }
}
