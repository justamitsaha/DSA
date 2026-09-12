package leetcode.R_GraphTheory;

import java.util.*;

/**
 * LeetCode 994: Rotting Oranges
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/rotting-oranges/
 *
 * Algorithm:
 * - Multi-Source Breadth-First Search (BFS) / Level-Order Grid Traversal
 *
 * Concepts:
 * - You are given an m x n grid where each cell can have one of three values:
 *   - 0 representing an empty cell,
 *   - 1 representing a fresh orange, or
 *   - 2 representing a rotten orange.
 * - Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * - Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 *   If this is impossible, return -1.
 * - Strategy:
 *   1. Scan grid: count all fresh oranges and enqueue all initial rotten oranges into a BFS queue.
 *   2. If there are 0 fresh oranges, 0 minutes are needed.
 *   3. Execute multi-source BFS level-by-level (each level = 1 minute):
 *      - Dequeue all rotten oranges at the current minute.
 *      - For each, infect 4-directionally adjacent fresh oranges: mark them '2', decrement `freshCount`, and enqueue.
 *   4. Continue until either queue is empty or freshCount reaches 0.
 *   5. If freshCount == 0, return elapsed minutes; otherwise return -1 (some oranges never rot).
 *
 * Complexity:
 * - Time Complexity:  O(m * n) - Each cell is visited and processed at most once.
 * - Space Complexity: O(m * n) - Queue size in the worst case (e.g., all cells are rotten).
 */
public class LC0994_RottingOranges {

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // Infect to rotten
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            minutes++;
        }

        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        LC0994_RottingOranges solver = new LC0994_RottingOranges();

        // Scenario 1: Standard grid (takes 4 minutes)
        int[][] grid1 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("Minutes to rot grid 1: " + solver.orangesRotting(grid1)); // Expected: 4

        // Scenario 2: Fresh orange isolated in bottom-right corner
        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("Minutes to rot grid 2: " + solver.orangesRotting(grid2)); // Expected: -1

        // Scenario 3: No fresh oranges initially
        int[][] grid3 = {{0, 2}};
        System.out.println("Minutes to rot grid 3: " + solver.orangesRotting(grid3)); // Expected: 0
    }
}
