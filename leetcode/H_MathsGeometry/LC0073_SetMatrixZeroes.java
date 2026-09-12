package leetcode.H_MathsGeometry;

import java.util.Arrays;

/**
 * LeetCode 73: Set Matrix Zeroes
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/set-matrix-zeroes/
 *
 * Algorithm:
 * - Constant Space Matrix In-Place Flagging (O(1) Auxiliary Space)
 *
 * Concepts:
 * - Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's.
 * - Naive approach: Allocate an m x n matrix (O(m*n) space).
 * - Better approach: Use two boolean arrays row[m] and col[n] (O(m+n) space).
 * - Optimal approach: Use the first row (`matrix[0][..]`) and first column (`matrix[..][0]`)
 *   of the matrix itself as the marker storage!
 * - Because `matrix[0][0]` is shared by both the first row and first column, we use separate boolean flags
 *   (`firstRowHasZero`, `firstColHasZero`) to track whether the original first row or column contain any zeros.
 * - Iterate over the rest of the matrix (1..m-1, 1..n-1), marking row and column heads with 0.
 * - Fill inner cells according to the markers.
 * - Finally, zero out the first row and/or first column if indicated by their initial flags.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Two passes over the matrix.
 * - Space Complexity: O(1)     - In-place modification with constant extra variables.
 */
public class LC0073_SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        // Check if first row originally has any zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // Check if first column originally has any zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        // Use first row and first column as markers for the rest of the matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Update inner matrix elements based on row and column markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero out the first row if needed
        if (firstRowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero out the first column if needed
        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        LC0073_SetMatrixZeroes solver = new LC0073_SetMatrixZeroes();

        // Scenario 1: Standard 3x3 matrix with one zero
        int[][] matrix1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        solver.setZeroes(matrix1);
        System.out.println("Scenario 1:");
        System.out.println(Arrays.deepToString(matrix1));
        // Expected: [[1, 0, 1], [0, 0, 0], [1, 0, 1]]

        // Scenario 2: Multiple zeroes including first row and column
        int[][] matrix2 = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };
        solver.setZeroes(matrix2);
        System.out.println("Scenario 2:");
        System.out.println(Arrays.deepToString(matrix2));
        // Expected: [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]

        // Scenario 3: Single element matrix
        int[][] matrix3 = {{0}};
        solver.setZeroes(matrix3);
        System.out.println("Scenario 3 (single zero): " + Arrays.deepToString(matrix3));
        // Expected: [[0]]
    }
}
