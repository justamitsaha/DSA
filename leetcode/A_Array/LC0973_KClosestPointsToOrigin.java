package leetcode.A_Array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * LeetCode 973: K Closest Points to Origin
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * Algorithms:
 * 1. Quickselect (Hoare's Selection Algorithm with Random Pivot) -> Average O(N) time
 * 2. Max-Heap (PriorityQueue of size K) -> O(N log K) time
 *
 * Mathematical Formula:
 * - Distance from (x, y) to origin (0, 0) is sqrt(x^2 + y^2).
 * - Since sqrt is strictly monotonically increasing, comparing (x^2 + y^2) is equivalent
 *   and avoids floating-point operations and inaccuracies.
 *
 * Quickselect Intuition:
 * - Like QuickSort, partition puts the pivot at its correct final sorted position.
 * - If the pivot ends up at index (k - 1), then the first k elements are the k closest
 *   (order does not matter per problem statement).
 * - If pivotIndex < k - 1, we recurse on the right side; otherwise on the left side.
 * - Average Time: O(N) because the search space halves on average at each step: N + N/2 + N/4... = 2N.
 *
 * Complexity:
 * - Quickselect: Average Time O(N), Worst Case O(N^2); Space O(1) in-place.
 * - Max-Heap:    Time O(N log K); Space O(K) auxiliary heap space.
 */
public class LC0973_KClosestPointsToOrigin {

    private final Random random = new Random();

    /**
     * Approach 1: Quickselect Algorithm (Optimal Average O(N) time).
     */
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length <= k) {
            return points;
        }

        int left = 0;
        int right = points.length - 1;

        while (left <= right) {
            int pivotIndex = partition(points, left, right);
            if (pivotIndex == k - 1) {
                break;
            } else if (pivotIndex < k - 1) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        return Arrays.copyOfRange(points, 0, k);
    }

    /**
     * Approach 2: Max-Heap (PriorityQueue) of size K.
     * Retains the K smallest points by evicting the largest when size exceeds K.
     */
    public int[][] kClosestHeap(int[][] points, int k) {
        // Max-heap comparing squared distance
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(dist(b), dist(a))
        );

        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Evict the farthest point
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    private int partition(int[][] points, int left, int right) {
        // Choose random pivot to avoid worst-case O(N^2) on sorted inputs
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(points, randomIndex, right);

        int pivotDist = dist(points[right]);
        int i = left;

        for (int j = left; j < right; j++) {
            if (dist(points[j]) < pivotDist) {
                swap(points, i, j);
                i++;
            }
        }
        swap(points, i, right);
        return i;
    }

    private int dist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    public static void main(String[] args) {
        LC0973_KClosestPointsToOrigin solver = new LC0973_KClosestPointsToOrigin();

        // Scenario 1: Standard case with 2 points, k = 1
        // [1, 3] dist^2 = 10, [-2, 2] dist^2 = 8 -> [-2, 2] is closer
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        int[][] res1 = solver.kClosest(points1, k1);
        System.out.println("Scenario 1 (k = 1): " + Arrays.deepToString(res1) + " | Expected: [[-2, 2]]");

        // Scenario 2: 3 points, k = 2
        // [3, 3] dist^2 = 18, [5, -1] dist^2 = 26, [-2, 4] dist^2 = 20 -> [3, 3] and [-2, 4]
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        int[][] res2 = solver.kClosest(points2, k2);
        System.out.println("Scenario 2 (k = 2): " + Arrays.deepToString(res2) + " | Expected 2 closest: [[3, 3], [-2, 4]]");

        // Scenario 3: Heap comparison verification
        int[][] points3 = {{0, 1}, {1, 0}};
        int k3 = 2;
        int[][] res3 = solver.kClosestHeap(points3, k3);
        System.out.println("Scenario 3 - Heap (k = 2): " + Arrays.deepToString(res3) + " | Expected: [[1, 0], [0, 1]]");
    }
}
