package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 417: Pacific Atlantic Water Flow
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/pacific-atlantic-water-flow/
 *
 * Algorithm:
 * - Reverse Multi-Source Depth-First Search (DFS) / Breadth-First Search (BFS)
 *
 * Concepts:
 * - There is an m x n rectangular island that borders both the Pacific Ocean (top and left)
 *   and Atlantic Ocean (bottom and right).
 * - Rain water can flow from a cell to an adjacent cell if the adjacent cell's height is less than
 *   or equal to the current cell's height.
 * - Key Intuition (Reverse Flow):
 *   Instead of simulating water flowing downhill from every interior cell to the oceans (which is O((m*n)^2)),
 *   simulate water flowing uphill from the oceans inland:
 *   - Start DFS/BFS from all Pacific border cells (row 0 and col 0). Water flows to neighbor if `neighborHeight >= currentHeight`.
 *   - Start DFS/BFS from all Atlantic border cells (row m-1 and col n-1) with the same condition.
 *   - Cells visited by BOTH Pacific and Atlantic searches can drain to both oceans!
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Each cell is visited at most twice (once for Pacific, once for Atlantic).
 * - Space Complexity: O(m * n) for the two boolean visited matrices and recursion call stack.
 */
public class LC0417_PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] canReachPacific = new boolean[m][n];
        boolean[][] canReachAtlantic = new boolean[m][n];

        // Horizontal borders (top = Pacific, bottom = Atlantic)
        for (int c = 0; c < n; c++) {
            dfs(heights, canReachPacific, 0, c);
            dfs(heights, canReachAtlantic, m - 1, c);
        }

        // Vertical borders (left = Pacific, right = Atlantic)
        for (int r = 0; r < m; r++) {
            dfs(heights, canReachPacific, r, 0);
            dfs(heights, canReachAtlantic, r, n - 1);
        }

        // Intersection: cells reachable from both oceans
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (canReachPacific[r][c] && canReachAtlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] canReach, int r, int c) {
        canReach[r][c] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            // Valid within bounds, not yet visited, and uphill/equal height
            if (nr >= 0 && nr < heights.length && nc >= 0 && nc < heights[0].length
                && !canReach[nr][nc] && heights[nr][nc] >= heights[r][c]) {
                dfs(heights, canReach, nr, nc);
            }
        }
    }

    public static void main(String[] args) {
        LC0417_PacificAtlanticWaterFlow solver = new LC0417_PacificAtlanticWaterFlow();

        // Scenario 1: Standard 5x5 grid
        int[][] heights1 = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        List<List<Integer>> res1 = solver.pacificAtlantic(heights1);
        System.out.println("Cells reaching both oceans (5x5): " + res1);
        // Expected: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]

        // Scenario 2: Single cell
        int[][] heights2 = {{1}};
        System.out.println("Single cell: " + solver.pacificAtlantic(heights2));
        // Expected: [[0, 0]]
    }
}
