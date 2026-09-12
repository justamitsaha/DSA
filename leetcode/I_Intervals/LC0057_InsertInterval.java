package leetcode.I_Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 57: Insert Interval
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/insert-interval/
 *
 * Algorithm:
 * - Three-Phase Linear Interval Insertion
 *
 * Concepts:
 * - Given a sorted, non-overlapping list of intervals and a `newInterval`, insert `newInterval` such that
 *   the list remains sorted and non-overlapping (merging if necessary).
 * - Because the input is already sorted, we can solve this in a single O(n) pass in 3 distinct phases:
 *   1. Phase 1 (Preceding Disjoint Intervals):
 *      Add all intervals that end strictly before `newInterval` starts (`intervals[i][1] < newInterval[0]`).
 *   2. Phase 2 (Overlapping Intervals):
 *      Merge all intervals that overlap with `newInterval` (`intervals[i][0] <= newInterval[1]`) by expanding:
 *      `newInterval[0] = min(newInterval[0], intervals[i][0])`
 *      `newInterval[1] = max(newInterval[1], intervals[i][1])`
 *      Once no more overlap, add the merged `newInterval`.
 *   3. Phase 3 (Succeeding Disjoint Intervals):
 *      Add all remaining intervals that come after `newInterval`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass over the interval list.
 * - Space Complexity: O(n) - To store the output intervals.
 */
public class LC0057_InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add all intervals ending before newInterval begins
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge all overlapping intervals into newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Phase 3: Add all remaining intervals that start after newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        LC0057_InsertInterval solver = new LC0057_InsertInterval();

        // Scenario 1: Standard insertion with single overlap
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        System.out.println("Scenario 1: " + Arrays.deepToString(solver.insert(intervals1, newInterval1)));
        // Expected: [[1, 5], [6, 9]]

        // Scenario 2: Insertion spanning multiple intervals
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        System.out.println("Scenario 2: " + Arrays.deepToString(solver.insert(intervals2, newInterval2)));
        // Expected: [[1, 2], [3, 10], [12, 16]]

        // Scenario 3: Insert into empty intervals list
        int[][] intervals3 = {};
        int[] newInterval3 = {5, 7};
        System.out.println("Scenario 3 (empty): " + Arrays.deepToString(solver.insert(intervals3, newInterval3)));
        // Expected: [[5, 7]]

        // Scenario 4: Insert at the very beginning (no overlap)
        int[][] intervals4 = {{3, 5}, {6, 9}};
        int[] newInterval4 = {1, 2};
        System.out.println("Scenario 4 (at start): " + Arrays.deepToString(solver.insert(intervals4, newInterval4)));
        // Expected: [[1, 2], [3, 5], [6, 9]]
    }
}
