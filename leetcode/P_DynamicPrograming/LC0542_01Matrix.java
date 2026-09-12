package leetcode.P_DynamicPrograming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 542: 01 Matrix
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/01-matrix/
 *
 * Algorithm:
 * - Method 1: Multi-Source Breadth-First Search (BFS) - O(m * n) Time & Space
 * - Method 2: Two-Pass Dynamic Programming (Top-Left & Bottom-Right) - O(m * n) Time, O(1) Auxiliary Space
 *
 * Concepts:
 * - Given an m x n binary matrix `mat`, return the distance of the nearest 0 for each cell.
 * - Multi-Source BFS:
 *   - Rather than searching from each 1 to find a 0 (which would be slow), reverse the problem!
 *   - Start BFS simultaneously from ALL cells containing 0 (multi-source queue).
 *   - Mark cells containing 1 as unvisited (`-1`).
 *   - Expand level-by-level to all 4 orthogonal neighbors. The first time an unvisited cell is reached,
 *     its distance is guaranteed to be minimal.
 * - Two-Pass DP:
 *   - Pass 1 (Top & Left): check min distance coming from top and left neighbors.
 *   - Pass 2 (Bottom & Right): check min distance coming from bottom and right neighbors.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Every cell is processed a constant number of times.
 * - Space Complexity: O(m * n) for BFS queue; O(1) auxiliary for Two-Pass DP (in-place).
 */
public class LC0542_01Matrix {

    /**
     * Multi-Source BFS approach.
     */
    public int[][] updateMatrix(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return mat;
        }

        int m = mat.length;
        int n = mat[0].length;
        int[][] dist = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0) {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                } else {
                    dist[r][c] = -1; // Marker for unvisited
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return dist;
    }

    /**
     * Two-Pass DP approach (O(1) extra space).
     */
    public int[][] updateMatrixDP(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int inf = m + n; // Maximum possible Manhattan distance in grid
        int[][] dist = new int[m][n];

        // Pass 1: Top-Left to Bottom-Right
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0) {
                    dist[r][c] = 0;
                } else {
                    int top = (r > 0) ? dist[r - 1][c] : inf;
                    int left = (c > 0) ? dist[r][c - 1] : inf;
                    dist[r][c] = Math.min(top, left) + 1;
                }
            }
        }

        // Pass 2: Bottom-Right to Top-Left
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (mat[r][c] != 0) {
                    int bottom = (r < m - 1) ? dist[r + 1][c] : inf;
                    int right = (c < n - 1) ? dist[r][c + 1] : inf;
                    dist[r][c] = Math.min(dist[r][c], Math.min(bottom, right) + 1);
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        LC0542_01Matrix solver = new LC0542_01Matrix();

        // Scenario 1:
        // [0, 0, 0]
        // [0, 1, 0]
        // [0, 0, 0]
        int[][] mat1 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Scenario 1 (BFS): " + Arrays.deepToString(solver.updateMatrix(mat1)));
        System.out.println("Scenario 1 (DP):  " + Arrays.deepToString(solver.updateMatrixDP(mat1)));
        // Expected: [[0, 0, 0], [0, 1, 0], [0, 0, 0]]

        // Scenario 2:
        // [0, 0, 0]
        // [0, 1, 0]
        // [1, 1, 1]
        int[][] mat2 = {
            {0, 0, 0},
            {0, 1, 0},
            {1, 1, 1}
        };
        System.out.println("Scenario 2: " + Arrays.deepToString(solver.updateMatrix(mat2)));
        // Expected: [[0, 0, 0], [0, 1, 0], [1, 2, 1]]
    }
}
