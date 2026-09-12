package leetcode.H_MathsGeometry;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 54: Spiral Matrix
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/spiral-matrix/
 *
 * Algorithm:
 * - Four-Boundary Shrinking Simulation
 *
 * Concepts:
 * - We traverse the matrix in spiral clockwise order:
 *   1. Left to Right along the current `top` row, then increment `top`.
 *   2. Top to Bottom along the current `right` column, then decrement `right`.
 *   3. Right to Left along the current `bottom` row (if `top <= bottom`), then decrement `bottom`.
 *   4. Bottom to Top along the current `left` column (if `left <= right`), then increment `left`.
 * - The check `top <= bottom` and `left <= right` before the reverse traversals prevents double-counting
 *   in non-square matrices (e.g., 1xN or Nx1).
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Every cell is visited exactly once.
 * - Space Complexity: O(1)     - Excluding output list (or O(m * n) to store result).
 */
public class LC0054_SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return output;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;

        while (top <= bottom && left <= right) {
            // Traverse from left to right along top boundary
            for (int col = left; col <= right; col++) {
                output.add(matrix[top][col]);
            }
            top++;

            // Traverse from top to bottom along right boundary
            for (int row = top; row <= bottom; row++) {
                output.add(matrix[row][right]);
            }
            right--;

            // Traverse from right to left along bottom boundary (if still within bounds)
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    output.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // Traverse from bottom to top along left boundary (if still within bounds)
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    output.add(matrix[row][left]);
                }
                left++;
            }
        }

        return output;
    }

    public static void main(String[] args) {
        LC0054_SpiralMatrix solver = new LC0054_SpiralMatrix();

        // Scenario 1: 3x3 square matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Scenario 1: " + solver.spiralOrder(matrix1));
        // Expected: [1, 2, 3, 6, 9, 8, 7, 4, 5]

        // Scenario 2: 3x4 rectangular matrix
        int[][] matrix2 = {
            { 1,  2,  3,  4},
            { 5,  6,  7,  8},
            { 9, 10, 11, 12}
        };
        System.out.println("Scenario 2: " + solver.spiralOrder(matrix2));
        // Expected: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

        // Scenario 3: Single row (1x4)
        int[][] matrix3 = {{1, 2, 3, 4}};
        System.out.println("Scenario 3 (1 row): " + solver.spiralOrder(matrix3));
        // Expected: [1, 2, 3, 4]

        // Scenario 4: Single column (4x1)
        int[][] matrix4 = {{1}, {2}, {3}, {4}};
        System.out.println("Scenario 4 (1 col): " + solver.spiralOrder(matrix4));
        // Expected: [1, 2, 3, 4]
    }
}
