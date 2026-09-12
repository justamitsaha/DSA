package leetcode.R_GraphTheory;

import java.util.Arrays;

/**
 * LeetCode 733: Flood Fill
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/flood-fill/
 *
 * Algorithm:
 * - Depth-First Search (DFS) / Breadth-First Search (BFS) Flood Fill
 *
 * Concepts:
 * - An image is represented by an m x n integer grid `image` where `image[i][j]` represents the pixel value.
 * - Given starting pixel (sr, sc) and a target color `color`:
 *   Change the color of the starting pixel and all 4-directionally adjacent pixels having the same original color,
 *   continuing recursively.
 * - Crucial Guard Condition:
 *   If `image[sr][sc] == color`, the starting pixel already has the target color.
 *   Returning immediately is mandatory to prevent an infinite recursion loop!
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - In the worst case, all pixels are of the initial color and must be updated.
 * - Space Complexity: O(m * n) - Call stack size during DFS recursion in the worst case.
 */
public class LC0733_FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int initialColor = image[sr][sc];
        // Guard against infinite recursion when old color == new color
        if (initialColor == color) {
            return image;
        }

        dfs(image, sr, sc, initialColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int initialColor, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != initialColor) {
            return;
        }

        image[r][c] = newColor;

        dfs(image, r - 1, c, initialColor, newColor); // Up
        dfs(image, r + 1, c, initialColor, newColor); // Down
        dfs(image, r, c - 1, initialColor, newColor); // Left
        dfs(image, r, c + 1, initialColor, newColor); // Right
    }

    public static void main(String[] args) {
        LC0733_FloodFill solver = new LC0733_FloodFill();

        // Scenario 1: Standard 3x3 flood fill
        int[][] image1 = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int[][] res1 = solver.floodFill(image1, 1, 1, 2);
        System.out.println("Flood filled image 1:");
        for (int[] row : res1) {
            System.out.println("  " + Arrays.toString(row));
        }
        // Expected:
        // [2, 2, 2]
        // [2, 2, 0]
        // [2, 0, 1]

        // Scenario 2: Starting color already equals new color
        int[][] image2 = {
            {0, 0, 0},
            {0, 0, 0}
        };
        int[][] res2 = solver.floodFill(image2, 0, 0, 0);
        System.out.println("Same color check image 2: " + Arrays.deepToString(res2));
    }
}
