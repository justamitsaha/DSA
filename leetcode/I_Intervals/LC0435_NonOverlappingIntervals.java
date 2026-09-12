package leetcode.I_Intervals;

import java.util.Arrays;

/**
 * LeetCode 435: Non-overlapping Intervals
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/non-overlapping-intervals/
 *
 * Algorithm:
 * - Greedy Activity Selection / Interval Scheduling (Earliest Deadline First)
 *
 * Concepts:
 * - Given an array of intervals [start_i, end_i], find the minimum number of intervals you need to remove
 *   to make the rest of the intervals non-overlapping.
 * - Equivalent Problem:
 *   Minimizing the number of removed intervals is mathematically identical to MAXIMIZING the number of
 *   mutually compatible (non-overlapping) intervals that can be kept!
 * - Greedy Choice:
 *   - Sort intervals by their END time in ascending order.
 *   - By always choosing the interval that finishes earliest, we leave as much room as possible for future intervals.
 *   - Count compatible intervals: if `intervals[i][0] >= currentEnd`, include this interval and update `currentEnd`.
 *   - Result = `intervals.length - compatibleCount`.
 *
 * Complexity:
 * - Time Complexity:  O(n log n) - Dominated by sorting intervals by end time.
 * - Space Complexity: O(log n) or O(1) - Sorting auxiliary space.
 */
public class LC0435_NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return 0;
        }

        // Sort intervals by their ending time in ascending order
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int countKept = 1;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            // If current interval starts at or after the previous interval ended, it's non-overlapping
            if (intervals[i][0] >= lastEnd) {
                countKept++;
                lastEnd = intervals[i][1];
            }
        }

        // Minimum removals = Total intervals - Maximum non-overlapping intervals kept
        return intervals.length - countKept;
    }

    public static void main(String[] args) {
        LC0435_NonOverlappingIntervals solver = new LC0435_NonOverlappingIntervals();

        // Scenario 1: Standard overlapping intervals
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Scenario 1: " + solver.eraseOverlapIntervals(intervals1) + " (Expected: 1)");

        // Scenario 2: Identical intervals
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println("Scenario 2: " + solver.eraseOverlapIntervals(intervals2) + " (Expected: 2)");

        // Scenario 3: Already disjoint intervals
        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println("Scenario 3: " + solver.eraseOverlapIntervals(intervals3) + " (Expected: 0)");

        // Scenario 4: Nested intervals
        int[][] intervals4 = {{1, 100}, {11, 22}, {1, 11}, {2, 12}};
        System.out.println("Scenario 4: " + solver.eraseOverlapIntervals(intervals4) + " (Expected: 2)");
    }
}
