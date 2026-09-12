package leetcode.A_Array;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 36: Valid Sudoku
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/valid-sudoku/
 *
 * Algorithm:
 * - Hash Set / Bitmask Validation across Rows, Columns, and 3x3 Sub-boxes
 *
 * Rules of a Valid Sudoku Board (9x9):
 * 1. Each row must contain digits 1-9 without repetition.
 * 2. Each column must contain digits 1-9 without repetition.
 * 3. Each of the 9 3x3 sub-boxes must contain digits 1-9 without repetition.
 * (Note: Empty cells are denoted by '.', and a board does NOT need to be solvable to be valid).
 *
 * Mathematical Indexing for 3x3 Sub-boxes:
 * - Given cell (i, j), its 3x3 sub-box index (0 to 8) is:
 *     boxIndex = (i / 3) * 3 + (j / 3)
 * - Row 0-2 & Col 0-2 -> Box 0
 * - Row 0-2 & Col 3-5 -> Box 1
 * - Row 0-2 & Col 6-8 -> Box 2, etc.
 *
 * Complexity:
 * - Time Complexity:  O(1) - Board size is fixed at 9x9 = 81 iterations.
 * - Space Complexity: O(1) - Fixed size hash sets for 9 rows, 9 cols, and 9 sub-boxes.
 */
public class LC0036_ValidSudoku {

    /**
     * Validates whether the 9x9 board satisfies Sudoku rules.
     */
    public boolean isValidSudoku(char[][] board) {
        @SuppressWarnings("unchecked")
        Set<Character>[] rowSet = new HashSet[9];
        @SuppressWarnings("unchecked")
        Set<Character>[] colSet = new HashSet[9];
        @SuppressWarnings("unchecked")
        Set<Character>[] boxSet = new HashSet[9];

        for (int i = 0; i < 9; i++) {
            rowSet[i] = new HashSet<>();
            colSet[i] = new HashSet<>();
            boxSet[i] = new HashSet<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];

                if (val == '.') {
                    continue; // Skip empty cells
                }

                int boxIndex = (i / 3) * 3 + (j / 3);

                // If character already exists in row, col, or box -> invalid
                if (rowSet[i].contains(val) || colSet[j].contains(val) || boxSet[boxIndex].contains(val)) {
                    return false;
                }

                rowSet[i].add(val);
                colSet[j].add(val);
                boxSet[boxIndex].add(val);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LC0036_ValidSudoku solver = new LC0036_ValidSudoku();

        // Scenario 1: Valid board
        char[][] validBoard = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Scenario 1 - Valid Board:   " + solver.isValidSudoku(validBoard) + " | Expected: true");

        // Scenario 2: Invalid row (duplicate '8' in top row)
        char[][] invalidRowBoard = {
            {'8','3','.','.','7','.','.','.','8'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Scenario 2 - Duplicate Row: " + solver.isValidSudoku(invalidRowBoard) + " | Expected: false");

        // Scenario 3: Invalid 3x3 sub-box (duplicate '8' in top-left 3x3 box)
        char[][] invalidBoxBoard = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','8','.','6','.','.','.','3'}, // Note duplicate 8
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println("Scenario 3 - Duplicate Box: " + solver.isValidSudoku(invalidBoxBoard) + " | Expected: false");
    }
}
