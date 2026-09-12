package leetcode.H_MathsGeometry;

import java.util.Arrays;

/**
 * LeetCode 48: Rotate Image
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/rotate-image/
 *
 * Algorithm:
 * - In-Place Matrix Transposition + Horizontal Reflection (Reverse Rows)
 *
 * Concepts:
 * - Rotating an n x n 2D matrix by 90 degrees clockwise can be decomposed into two fundamental geometric transformations:
 *   1. Transpose the matrix: Swap matrix[i][j] with matrix[j][i] along the main diagonal (for all i < j).
 *   2. Reflect / Reverse each row horizontally: For each row, swap element at `left` with `right` until pointers meet.
 * - This achieves an exact 90-degree clockwise rotation in-place without allocating an auxiliary n x n matrix.
 *
 * Complexity:
 * - Time Complexity:  O(n^2) - We visit every element twice (once during transpose, once during row reversal).
 * - Space Complexity: O(1)   - In-place modification without extra space.
 */
public class LC0048_RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int n = matrix.length;

        // Step 1: Transpose matrix (swap rows with columns across main diagonal)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row horizontally (two pointers)
        for (int row = 0; row < n; row++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[row][left];
                matrix[row][left] = matrix[row][right];
                matrix[row][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        LC0048_RotateImage solver = new LC0048_RotateImage();

        // Scenario 1: Standard 3x3 matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        solver.rotate(matrix1);
        System.out.println("Scenario 1 (3x3 rotated 90 deg clockwise):");
        System.out.println(Arrays.deepToString(matrix1));
        // Expected: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]

        // Scenario 2: Standard 4x4 matrix
        int[][] matrix2 = {
            { 5,  1,  9, 11},
            { 2,  4,  8, 10},
            {13,  3,  6,  7},
            {15, 14, 12, 16}
        };
        solver.rotate(matrix2);
        System.out.println("Scenario 2 (4x4 rotated 90 deg clockwise):");
        System.out.println(Arrays.deepToString(matrix2));
        // Expected: [[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]

        // Scenario 3: 1x1 matrix (Boundary case)
        int[][] matrix3 = {{1}};
        solver.rotate(matrix3);
        System.out.println("Scenario 3 (1x1): " + Arrays.deepToString(matrix3));
        // Expected: [[1]]
    }
}
