package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 51: N-Queens
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/n-queens/
 *
 * Algorithm:
 * - Backtracking with Row-by-Row Constraint Propagation
 *
 * Concepts:
 * - Place `n` non-attacking queens on an n x n chessboard.
 * - Queens can attack horizontally, vertically, and diagonally.
 * - Strategy:
 *   - Place exactly one queen per row, advancing row-by-row from row 0 to row n - 1.
 *   - This automatically guarantees no two queens share the same row.
 *   - For each column in the current row, check if placing a queen is safe:
 *     1. Vertical check: No queen in the same column above (`board[r][col] == 'Q'`).
 *     2. Upper-left diagonal check: `row - i, col - i`.
 *     3. Upper-right diagonal check: `row - i, col + i`.
 *   - If safe: place 'Q', recurse to `row + 1`, then backtrack ('Q' -> '.').
 *   - When `row == n`, a valid full board configuration is found.
 *
 * Complexity:
 * - Time Complexity:  O(n!) - The number of placements checked decreases with each row placed.
 * - Space Complexity: O(n^2) - To store the board configuration and output solutions.
 */
public class LC0051_NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        if (n <= 0) {
            return solutions;
        }

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack(board, 0, solutions);
        return solutions;
    }

    private void backtrack(char[][] board, int row, List<List<String>> solutions) {
        int n = board.length;

        // Base case: all rows successfully placed
        if (row == n) {
            solutions.add(constructBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(board, row + 1, solutions);
                board[row][col] = '.'; // backtrack
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;

        // Check vertical column above
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int r = row - 1, c = col + 1; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private List<String> constructBoard(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }

    public static void main(String[] args) {
        LC0051_NQueens solver = new LC0051_NQueens();

        // Scenario 1: n = 4 (2 distinct solutions)
        System.out.println("N = 4 solutions (" + solver.solveNQueens(4).size() + " total):");
        for (List<String> solution : solver.solveNQueens(4)) {
            for (String row : solution) {
                System.out.println("  " + row);
            }
            System.out.println();
        }

        // Scenario 2: n = 1 (1 solution)
        System.out.println("N = 1 solution: " + solver.solveNQueens(1));

        // Scenario 3: n = 2 (0 solutions)
        System.out.println("N = 2 solutions: " + solver.solveNQueens(2));

        // Scenario 4: n = 8 (92 distinct solutions)
        System.out.println("N = 8 total solution count: " + solver.solveNQueens(8).size() + " (Expected: 92)");
    }
}
