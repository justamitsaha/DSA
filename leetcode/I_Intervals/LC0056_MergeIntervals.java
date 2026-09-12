package leetcode.I_Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 56: Merge Intervals
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/merge-intervals/
 *
 * Algorithm:
 * - Sorting + Greedy Linear Interval Merging
 *
 * Concepts:
 * - Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals.
 * - Key Observation:
 *   - If we sort intervals by their start time, all intervals that could possibly overlap become adjacent!
 * - Once sorted:
 *   - Initialize a `merged` list with the first interval.
 *   - For each subsequent interval `current`:
 *     - If `current.start <= lastMerged.end`: There is an overlap!
 *       Merge them by updating `lastMerged.end = Math.max(lastMerged.end, current.end)`.
 *     - Else: No overlap, append `current` as a new disjoint interval in `merged`.
 *
 * Complexity:
 * - Time Complexity:  O(n log n) - Sorting dominates the time. The merge pass is O(n).
 * - Space Complexity: O(log n) to O(n) - For sorting overhead and output list.
 */
public class LC0056_MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals by starting time in ascending order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            int[] lastMerged = merged.get(merged.size() - 1);

            if (currentInterval[0] <= lastMerged[1]) {
                // Overlapping intervals: extend the end of the existing interval
                lastMerged[1] = Math.max(lastMerged[1], currentInterval[1]);
            } else {
                // Disjoint interval: add as a new interval
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        LC0056_MergeIntervals solver = new LC0056_MergeIntervals();

        // Scenario 1: Standard overlapping intervals
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Scenario 1: " + Arrays.deepToString(solver.merge(intervals1)));
        // Expected: [[1, 6], [8, 10], [15, 18]]

        // Scenario 2: Consecutive touching intervals
        int[][] intervals2 = {{1, 4}, {4, 5}};
        System.out.println("Scenario 2: " + Arrays.deepToString(solver.merge(intervals2)));
        // Expected: [[1, 5]]

        // Scenario 3: Completely enclosed interval
        int[][] intervals3 = {{1, 10}, {2, 3}, {4, 8}};
        System.out.println("Scenario 3: " + Arrays.deepToString(solver.merge(intervals3)));
        // Expected: [[1, 10]]

        // Scenario 4: Already disjoint intervals
        int[][] intervals4 = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("Scenario 4: " + Arrays.deepToString(solver.merge(intervals4)));
        // Expected: [[1, 2], [3, 4], [5, 6]]
    }
}
