package leetcode.L_RecursionBacktracking;

/**
 * LeetCode 79: Word Search
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/word-search/
 *
 * Algorithm:
 * - 2D Grid DFS Backtracking with In-Place Visited Masking
 *
 * Concepts:
 * - Given an m x n grid of characters and a string `word`, return true if the word exists in the grid.
 * - Cells can connect horizontally or vertically. A single cell cannot be visited more than once in the same path.
 * - Optimization (In-Place Marker):
 *   - Instead of allocating an m x n boolean visited array, temporarily mask `board[r][c] = '#'` before recursing.
 *   - Restore `board[r][c] = originalChar` during backtracking.
 * - Pruning:
 *   - Immediately return false if current cell coordinates are out of bounds or `board[r][c] != word.charAt(index)`.
 *   - Once `index == word.length()`, the entire word has been successfully matched!
 *
 * Complexity:
 * - Time Complexity:  O(m * n * 3^L) - Where m, n are board dimensions and L is word length.
 *                     From each cell we explore at most 3 directions (excluding where we just came from).
 * - Space Complexity: O(L)           - Call stack depth bounded by the word length L.
 */
public class LC0079_WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int r, int c, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        // Boundary and mismatch check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Mark current cell as visited in-place
        char temp = board[r][c];
        board[r][c] = '#';

        // Explore 4 cardinal directions: Up, Down, Left, Right
        boolean found = dfs(board, r + 1, c, word, index + 1)
                     || dfs(board, r - 1, c, word, index + 1)
                     || dfs(board, r, c + 1, word, index + 1)
                     || dfs(board, r, c - 1, word, index + 1);

        // Backtrack: restore cell
        board[r][c] = temp;

        return found;
    }

    public static void main(String[] args) {
        LC0079_WordSearch solver = new LC0079_WordSearch();

        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        // Scenario 1: Word exists ("ABCCED")
        System.out.println("Search \"ABCCED\": " + solver.exist(board, "ABCCED") + " (Expected: true)");

        // Scenario 2: Word exists ("SEE")
        System.out.println("Search \"SEE\": " + solver.exist(board, "SEE") + " (Expected: true)");

        // Scenario 3: Word does not exist ("ABCB") - requires reusing cell
        System.out.println("Search \"ABCB\": " + solver.exist(board, "ABCB") + " (Expected: false)");

        // Scenario 4: Single cell matching
        char[][] single = {{'a'}};
        System.out.println("Single 'a' for \"a\": " + solver.exist(single, "a") + " (Expected: true)");
        System.out.println("Single 'a' for \"b\": " + solver.exist(single, "b") + " (Expected: false)");
    }
}
