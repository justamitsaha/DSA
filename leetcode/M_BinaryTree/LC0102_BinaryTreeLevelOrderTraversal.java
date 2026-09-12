package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102: Binary Tree Level Order Traversal
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Algorithm:
 * - Breadth-First Search (BFS) / Level-by-Level Queue Traversal
 *
 * Concepts:
 * - Given the root of a binary tree, return the level order traversal of its nodes' values (left to right, level by level).
 * - BFS Level Separation:
 *   - Use a queue initialized with `root`.
 *   - At the beginning of each outer loop iteration, `int size = queue.size()` gives the EXACT number of nodes on the current level.
 *   - Loop `size` times, dequeuing each node, adding its value to the current level's list, and enqueuing its non-null children.
 *   - Append `currentLevel` to the final result list.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is enqueued and dequeued once.
 * - Space Complexity: O(w) - Where w is the maximum width of the binary tree (up to n/2 in a complete binary tree).
 */
public class LC0102_BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        LC0102_BinaryTreeLevelOrderTraversal solver = new LC0102_BinaryTreeLevelOrderTraversal();

        // Scenario 1: [3, 9, 20, null, null, 15, 7]
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        TreeNode root1 = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Tree 1 level order: " + solver.levelOrder(root1));
        // Expected: [[3], [9, 20], [15, 7]]

        // Scenario 2: Single node
        TreeNode root2 = new TreeNode(1);
        System.out.println("Single node: " + solver.levelOrder(root2));
        // Expected: [[1]]

        // Scenario 3: Empty tree
        System.out.println("Empty tree: " + solver.levelOrder(null));
        // Expected: []
    }
}
