package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 104: Maximum Depth of Binary Tree
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Algorithm:
 * - Depth-First Search (Recursive) & Breadth-First Search (Iterative Level Counting)
 *
 * Concepts:
 * - The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * - Recursive formulation:
 *   - Base case: If root is null, depth is 0.
 *   - Inductive step: Depth of current node is `1 + max(depth(left), depth(right))`.
 * - Iterative BFS:
 *   - Increment depth counter after processing each level in a BFS queue.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is visited once.
 * - Space Complexity: O(h) - Stack space bounded by tree height (O(log n) balanced, O(n) skewed).
 */
public class LC0104_MaximumDepthOfBinaryTree {

    /**
     * Recursive DFS approach.
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * Iterative BFS level counting.
     */
    public int maxDepthIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }

        return depth;
    }

    public static void main(String[] args) {
        LC0104_MaximumDepthOfBinaryTree solver = new LC0104_MaximumDepthOfBinaryTree();

        // Scenario 1: [3, 9, 20, null, null, 15, 7] -> depth 3
        TreeNode root1 = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Depth of tree 1: " + solver.maxDepth(root1) + " (Expected: 3)");
        System.out.println("Depth (Iterative): " + solver.maxDepthIterative(root1) + " (Expected: 3)");

        // Scenario 2: [1, null, 2] -> depth 2
        TreeNode root2 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("Depth of tree 2: " + solver.maxDepth(root2) + " (Expected: 2)");

        // Scenario 3: Null tree -> depth 0
        System.out.println("Depth of null: " + solver.maxDepth(null) + " (Expected: 0)");
    }
}
