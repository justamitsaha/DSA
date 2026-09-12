package leetcode.K_BinarySearch_2;

import java.util.Arrays;

/**
 * LeetCode 1552: Magnetic Force Between Two Balls
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/magnetic-force-between-two-balls/
 *
 * Algorithm:
 * - Binary Search on Answer (Maximize the Minimum Distance / Aggressive Cows Pattern)
 *
 * Concepts:
 * - We have `n` baskets at given `position` coordinates. We need to place `m` balls into the baskets
 *   such that the minimum magnetic force (distance) between any two balls is MAXIMIZED.
 * - Monotonicity:
 *   - If it is possible to place `m` balls with a minimum pairwise distance of `d`, it is also possible
 *     with any distance `d' < d`.
 *   - If it is NOT possible with distance `d`, it is impossible for any `d' > d`.
 *   - This allows binary searching over the distance `d`!
 * - Greedy Placement (Feasibility Check):
 *   - First, sort `position` in ascending order.
 *   - Always place the first ball at `position[0]` (greedily leaves maximal room for subsequent balls).
 *   - Iterate through positions; place the next ball at the earliest position `p` where `p - lastPosition >= d`.
 *   - If we successfully place at least `m` balls, distance `d` is feasible.
 *
 * Search Space:
 * - `start = 1` (smallest possible integer distance).
 * - `end = position[n - 1] - position[0]` (maximum possible distance between extremes).
 *
 * Complexity:
 * - Time Complexity:  O(n log n + n * log(maxPosition - minPosition))
 *                     Sorting takes O(n log n); Binary search takes O(n * log(range)).
 * - Space Complexity: O(log n) or O(1) - Sorting auxiliary space.
 */
public class LC1552_MagneticForceBetweenTwoBalls {

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;

        int start = 1;
        int end = position[n - 1] - position[0];
        int ans = 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (canPlaceBalls(position, m, mid)) {
                ans = mid;
                // Try searching for a larger minimum magnetic force
                start = mid + 1;
            } else {
                // Distance too large, decrease distance
                end = mid - 1;
            }
        }

        return ans;
    }

    private boolean canPlaceBalls(int[] position, int m, int minDistance) {
        int ballsPlaced = 1;
        int lastPosition = position[0];

        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPosition >= minDistance) {
                ballsPlaced++;
                lastPosition = position[i];
                if (ballsPlaced >= m) {
                    return true;
                }
            }
        }

        return ballsPlaced >= m;
    }

    public static void main(String[] args) {
        LC1552_MagneticForceBetweenTwoBalls solver = new LC1552_MagneticForceBetweenTwoBalls();

        // Scenario 1: Standard case
        int[] position1 = {1, 2, 3, 4, 7};
        int m1 = 3;
        System.out.println("Scenario 1: " + solver.maxDistance(position1, m1) + " (Expected: 3)");

        // Scenario 2: Large distances
        int[] position2 = {5, 4, 3, 2, 1, 1000000000};
        int m2 = 2;
        System.out.println("Scenario 2: " + solver.maxDistance(position2, m2) + " (Expected: 999999999)");

        // Scenario 3: Exact count of positions equal to m (all baskets used)
        int[] position3 = {1, 5, 10};
        int m3 = 3;
        System.out.println("Scenario 3: " + solver.maxDistance(position3, m3) + " (Expected: 4)");
    }
}
