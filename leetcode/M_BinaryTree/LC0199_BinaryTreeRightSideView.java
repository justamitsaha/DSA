package leetcode.M_BinaryTree;

import leetcode.common.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 199: Binary Tree Right Side View
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Algorithm:
 * - Method 1: Level-by-Level BFS (Queue Last-Element Sampling)
 * - Method 2: Reverse Pre-Order DFS (Root -> Right -> Left with Depth Check)
 *
 * Concepts:
 * - We imagine looking at the tree from the right side; we can only see the rightmost node at each vertical depth level.
 * - BFS Approach:
 *   - Traverse level by level.
 *   - The node processed at the last index of each level (`i == size - 1`) is appended to `result`.
 * - DFS Approach:
 *   - Traverse in Root -> Right -> Left order.
 *   - If `depth == result.size()`, this is the first time we are visiting this depth level,
 *     and since we traversed right first, this node is guaranteed to be the rightmost!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Every node is visited once.
 * - Space Complexity: O(w) for BFS (max tree width) or O(h) for DFS (tree height).
 */
public class LC0199_BinaryTreeRightSideView {

    /**
     * BFS level-order traversal.
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // If this is the last node in the current level, it is visible from the right
                if (i == levelSize - 1) {
                    result.add(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return result;
    }

    /**
     * Reverse Pre-Order DFS (Root -> Right -> Left).
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode node, int depth, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (depth == result.size()) {
            result.add(node.val);
        }

        // Traverse right subtree first so rightmost nodes are visited first
        dfs(node.right, depth + 1, result);
        dfs(node.left, depth + 1, result);
    }

    public static void main(String[] args) {
        LC0199_BinaryTreeRightSideView solver = new LC0199_BinaryTreeRightSideView();

        // Scenario 1: [1, 2, 3, null, 5, null, 4]
        //       1            <---
        //     /   \
        //    2     3         <---
        //     \     \
        //      5     4       <---
        TreeNode root1 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(5)),
            new TreeNode(3, null, new TreeNode(4))
        );
        System.out.println("Tree 1 right side view (BFS): " + solver.rightSideView(root1) + " (Expected: [1, 3, 4])");
        System.out.println("Tree 1 right side view (DFS): " + solver.rightSideViewDFS(root1) + " (Expected: [1, 3, 4])");

        // Scenario 2: [1, null, 3]
        TreeNode root2 = new TreeNode(1, null, new TreeNode(3));
        System.out.println("Tree 2 right side view: " + solver.rightSideView(root2) + " (Expected: [1, 3])");

        // Scenario 3: Empty tree
        System.out.println("Empty tree: " + solver.rightSideView(null) + " (Expected: [])");
    }
}
