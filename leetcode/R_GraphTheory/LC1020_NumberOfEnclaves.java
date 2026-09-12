package leetcode.R_GraphTheory;

/**
 * LeetCode 1020: Number of Enclaves
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/number-of-enclaves/
 *
 * Algorithm:
 * - Boundary-Connected Flood Fill via Depth-First Search (DFS) / Breadth-First Search (BFS)
 *
 * Concepts:
 * - You are given an m x n binary matrix `grid`, where 0 represents a sea cell and 1 represents a land cell.
 * - A move consists of walking from one land cell to another 4-directionally adjacent land cell or walking
 *   off the boundary of the grid.
 * - Return the number of land cells in `grid` for which we cannot walk off the boundary in any number of moves.
 * - Strategy:
 *   1. Any land cell ('1') that connects to any of the 4 borders can walk off the boundary.
 *   2. Run DFS from all '1' cells on the borders (top row, bottom row, left col, right col), sinking
 *      all boundary-reachable land cells by turning them into '0' (sea).
 *   3. After the boundary flood-fill completes, scan the matrix:
 *      Any remaining '1' cell cannot reach the boundary and is an enclave cell!
 *      Sum up all remaining '1' cells.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Every cell is visited a constant number of times.
 * - Space Complexity: O(m * n) - Maximum depth of the recursion stack in the worst case.
 */
public class LC1020_NumberOfEnclaves {

    public int numEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Step 1: Sink boundary-connected land from left and right borders
        for (int r = 0; r < m; r++) {
            if (grid[r][0] == 1) {
                dfs(grid, r, 0);
            }
            if (grid[r][n - 1] == 1) {
                dfs(grid, r, n - 1);
            }
        }

        // Step 1: Sink boundary-connected land from top and bottom borders
        for (int c = 0; c < n; c++) {
            if (grid[0][c] == 1) {
                dfs(grid, 0, c);
            }
            if (grid[m - 1][c] == 1) {
                dfs(grid, m - 1, c);
            }
        }

        // Step 2: Count remaining un-sinkable land cells (enclaves)
        int enclaveCount = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    enclaveCount++;
                }
            }
        }

        return enclaveCount;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) {
            return;
        }

        // Sink the land cell
        grid[r][c] = 0;

        dfs(grid, r - 1, c); // Up
        dfs(grid, r + 1, c); // Down
        dfs(grid, r, c - 1); // Left
        dfs(grid, r, c + 1); // Right
    }

    public static void main(String[] args) {
        LC1020_NumberOfEnclaves solver = new LC1020_NumberOfEnclaves();

        // Scenario 1: Standard grid with 3 enclave cells
        int[][] grid1 = {
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };
        System.out.println("Enclaves count 1: " + solver.numEnclaves(grid1)); // Expected: 3

        // Scenario 2: All land cells connected to border
        int[][] grid2 = {
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        };
        System.out.println("Enclaves count 2: " + solver.numEnclaves(grid2)); // Expected: 0

        // Scenario 3: Single interior land cell
        int[][] grid3 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Enclaves count 3: " + solver.numEnclaves(grid3)); // Expected: 1
    }
}
