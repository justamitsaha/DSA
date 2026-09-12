package leetcode.R_GraphTheory;

import java.util.Arrays;

/**
 * LeetCode 130: Surrounded Regions
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/surrounded-regions/
 *
 * Algorithm:
 * - Boundary-Connected DFS / Multi-Source Border Inversion
 *
 * Concepts:
 * - Given an m x n matrix `board` containing 'X' and 'O', capture all regions surrounded by 'X'.
 * - Any 'O' that is connected to the BORDER of the board cannot be captured, nor can any of its 'O' neighbors!
 * - Strategy:
 *   1. Phase 1 (Border Flood Fill):
 *      Start DFS from all 'O' cells located on the 4 borders (top row, bottom row, left col, right col).
 *      Temporarily mark all border-reachable 'O' cells with an escape marker (e.g., `'#'`).
 *   2. Phase 2 (Matrix Sweep):
 *      Iterate through every cell in the grid:
 *      - If the cell is `'O'`, it is completely surrounded by 'X's -> capture it by changing to `'X'`.
 *      - If the cell is `'#'`, it was saved by border connectivity -> restore it back to `'O'`.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Each cell is visited a constant number of times.
 * - Space Complexity: O(m * n) - Call stack depth in the worst case (e.g., grid filled with 'O').
 */
public class LC0130_SurroundedRegions {

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        // Step 1: Mark border-connected 'O' cells with '#'
        // Top and bottom borders
        for (int c = 0; c < n; c++) {
            if (board[0][c] == 'O') {
                dfs(board, 0, c);
            }
            if (board[m - 1][c] == 'O') {
                dfs(board, m - 1, c);
            }
        }

        // Left and right borders
        for (int r = 0; r < m; r++) {
            if (board[r][0] == 'O') {
                dfs(board, r, 0);
            }
            if (board[r][n - 1] == 'O') {
                dfs(board, r, n - 1);
            }
        }

        // Step 2: Flip unescaped 'O' to 'X' and restore '#' back to 'O'
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X'; // Captured
                } else if (board[r][c] == '#') {
                    board[r][c] = 'O'; // Restored
                }
            }
        }
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }

        board[r][c] = '#'; // Mark as border-connected (safe)

        dfs(board, r - 1, c);
        dfs(board, r + 1, c);
        dfs(board, r, c - 1);
        dfs(board, r, c + 1);
    }

    public static void main(String[] args) {
        LC0130_SurroundedRegions solver = new LC0130_SurroundedRegions();

        // Scenario 1: Standard 4x4 grid
        char[][] board1 = {
            {'X', 'X', 'X', 'X'},
            {'X', 'O', 'O', 'X'},
            {'X', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'X'}
        };
        solver.solve(board1);
        System.out.println("Scenario 1 output:");
        for (char[] row : board1) {
            System.out.println("  " + Arrays.toString(row));
        }
        // Expected:
        // ['X', 'X', 'X', 'X']
        // ['X', 'X', 'X', 'X']
        // ['X', 'X', 'X', 'X']
        // ['X', 'O', 'X', 'X']

        // Scenario 2: Single cell 'X'
        char[][] board2 = {{'X'}};
        solver.solve(board2);
        System.out.println("Single 'X': " + Arrays.deepToString(board2));
    }
}
