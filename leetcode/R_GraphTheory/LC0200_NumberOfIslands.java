package leetcode.R_GraphTheory;

/**
 * LeetCode 200: Number of Islands
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/number-of-islands/
 *
 * Algorithm:
 * - Connected Components via Depth-First Search (DFS) / Breadth-First Search (BFS) on a 2D Grid
 *
 * Concepts:
 * - Given an m x n 2D binary grid `grid` which represents a map of '1's (land) and '0's (water),
 *   return the number of islands.
 * - An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * - Iterate through every cell (r, c). When a land cell `'1'` is found:
 *   1. Increment `islandCount`.
 *   2. Trigger a DFS/BFS flood-fill starting at (r, c) to visit all connected land cells.
 *   3. Mark each visited cell as `'0'` (or `'2'`) in-place to avoid re-visitation.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) where m is rows and n is cols. Each cell is visited at most once.
 * - Space Complexity: O(m * n) in the worst case for DFS call stack depth (e.g., entire grid is land).
 */
public class LC0200_NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != '1') {
            return;
        }

        // Mark as visited (sink the island piece)
        grid[r][c] = '0';

        // Explore 4-directionally
        dfs(grid, r - 1, c); // Up
        dfs(grid, r + 1, c); // Down
        dfs(grid, r, c - 1); // Left
        dfs(grid, r, c + 1); // Right
    }

    public static void main(String[] args) {
        LC0200_NumberOfIslands solver = new LC0200_NumberOfIslands();

        // Scenario 1: Single connected island
        char[][] grid1 = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println("Grid 1 Islands: " + solver.numIslands(grid1)); // Expected: 1

        // Scenario 2: Three distinct islands
        char[][] grid2 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("Grid 2 Islands: " + solver.numIslands(grid2)); // Expected: 3

        // Scenario 3: All water
        char[][] grid3 = {
            {'0', '0'},
            {'0', '0'}
        };
        System.out.println("Grid 3 Islands: " + solver.numIslands(grid3)); // Expected: 0
    }
}
