package leetcode.P_DynamicPrograming;

import java.util.Arrays;

/**
 * LeetCode 62: Unique Paths
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/unique-paths/
 *
 * Algorithm:
 * - Method 1: Space-Optimized 1D Dynamic Programming (O(m * n) Time, O(n) Space)
 * - Method 2: Combinatorics Formula C(m + n - 2, m - 1) (O(min(m, n)) Time, O(1) Space)
 *
 * Concepts:
 * - A robot on an m x n grid starts at (0, 0) and wants to reach (m - 1, n - 1).
 * - It can only move either Down or Right.
 * - Dynamic Programming:
 *   - Any cell (i, j) can only be reached from (i - 1, j) or (i, j - 1).
 *   - `dp[i][j] = dp[i - 1][j] + dp[i][j - 1]`.
 *   - Space can be reduced to a 1D array of size `n` because computing row `i` only requires values from the previous row and the current row's previous column.
 * - Combinatorics:
 *   - Total steps required = `(m - 1) + (n - 1) = m + n - 2`.
 *   - Number of unique paths = `(m + n - 2)! / ((m - 1)! * (n - 1)!)`.
 *
 * Complexity:
 * - Time Complexity:  O(m * n) for 1D DP; O(min(m, n)) for Combinatorics.
 * - Space Complexity: O(n) for 1D DP; O(1) for Combinatorics.
 */
public class LC0062_UniquePaths {

    /**
     * Space-optimized 1D DP.
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];
    }

    /**
     * Combinatorial approach: C(m + n - 2, m - 1).
     */
    public int uniquePathsCombinatorics(int m, int n) {
        long result = 1;
        int totalSteps = m + n - 2;
        int k = Math.min(m - 1, n - 1);

        for (int i = 1; i <= k; i++) {
            result = result * (totalSteps - k + i) / i;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        LC0062_UniquePaths solver = new LC0062_UniquePaths();

        // Scenario 1: 3 x 7 -> 28 paths
        System.out.println("Paths for 3 x 7: " + solver.uniquePaths(3, 7) + " (Expected: 28)");
        System.out.println("Paths for 3 x 7 (Combinatorics): " + solver.uniquePathsCombinatorics(3, 7) + " (Expected: 28)");

        // Scenario 2: 3 x 2 -> 3 paths
        System.out.println("Paths for 3 x 2: " + solver.uniquePaths(3, 2) + " (Expected: 3)");

        // Scenario 3: 1 x 1 -> 1 path
        System.out.println("Paths for 1 x 1: " + solver.uniquePaths(1, 1) + " (Expected: 1)");
    }
}
