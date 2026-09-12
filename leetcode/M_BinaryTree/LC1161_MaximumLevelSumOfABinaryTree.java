package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 1161: Maximum Level Sum of a Binary Tree
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
 *
 * Algorithm:
 * - Breadth-First Search (BFS) Level Accumulation
 *
 * Concepts:
 * - The level of root is 1, its children level 2, etc.
 * - Find the SMALLEST level `x` such that the sum of all node values at level `x` is maximal.
 * - Queue-Based Level Processing:
 *   - Process nodes level-by-level using `int size = queue.size()`.
 *   - Compute the sum of all node values for that level.
 *   - If `currentLevelSum > maxLevelSum`, update `maxLevelSum = currentLevelSum` and `bestLevel = currentLevel`.
 *   - By using strictly greater (`>`), any subsequent level with the identical sum will NOT overwrite `bestLevel`,
 *     satisfying the requirement to return the SMALLEST level number in case of ties!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is processed once in the BFS queue.
 * - Space Complexity: O(w) - Where w is the maximum width of the binary tree.
 */
public class LC1161_MaximumLevelSumOfABinaryTree {

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        long maxSum = Long.MIN_VALUE;
        int bestLevel = 1;
        int currentLevel = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            long currentLevelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevelSum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // Strictly greater ensures smallest level is retained on ties
            if (currentLevelSum > maxSum) {
                maxSum = currentLevelSum;
                bestLevel = currentLevel;
            }

            currentLevel++;
        }

        return bestLevel;
    }

    public static void main(String[] args) {
        LC1161_MaximumLevelSumOfABinaryTree solver = new LC1161_MaximumLevelSumOfABinaryTree();

        // Scenario 1: [1, 7, 0, 7, -8, null, null]
        // Level 1: sum = 1
        // Level 2: sum = 7 + 0 = 7
        // Level 3: sum = 7 + (-8) = -1
        // Max sum is 7 at level 2.
        TreeNode root1 = new TreeNode(1,
            new TreeNode(7, new TreeNode(7), new TreeNode(-8)),
            new TreeNode(0)
        );
        System.out.println("Tree 1 max level: " + solver.maxLevelSum(root1) + " (Expected: 2)");

        // Scenario 2: Negative values [989, null, 10250, 98693, -89388, null, null, null, -32127]
        TreeNode root2 = new TreeNode(989,
            null,
            new TreeNode(10250,
                new TreeNode(98693),
                new TreeNode(-89388, null, new TreeNode(-32127))
            )
        );
        System.out.println("Tree 2 max level: " + solver.maxLevelSum(root2) + " (Expected: 2)");

        // Scenario 3: Single node
        TreeNode root3 = new TreeNode(-100);
        System.out.println("Single node max level: " + solver.maxLevelSum(root3) + " (Expected: 1)");
    }
}
