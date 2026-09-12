package leetcode.I_Intervals;

import java.util.Arrays;

/**
 * LeetCode 252: Meeting Rooms
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/meeting-rooms/
 *
 * Algorithm:
 * - Sorting + Adjacent Interval Overlap Check
 *
 * Concepts:
 * - Given an array of meeting time intervals [start_i, end_i], determine if a person could attend all meetings
 *   (i.e., whether any two meetings overlap).
 * - Key Observation:
 *   - If we sort the meetings by start time, we only need to check if any meeting starts BEFORE the previous meeting finishes!
 *   - If `intervals[i - 1][1] > intervals[i][0]`, the person cannot attend both meetings -> return false.
 *   - If no adjacent meetings overlap, no non-adjacent meetings can overlap either -> return true.
 *
 * Complexity:
 * - Time Complexity:  O(n log n) - Dominated by sorting intervals.
 * - Space Complexity: O(log n) or O(1) - Depending on sorting algorithm auxiliary space.
 */
public class LC0252_MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }

        // Sort meetings by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Check for any overlap between consecutive meetings
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LC0252_MeetingRooms solver = new LC0252_MeetingRooms();

        // Scenario 1: Overlapping meetings (cannot attend all)
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Scenario 1: " + solver.canAttendMeetings(intervals1) + " (Expected: false)");

        // Scenario 2: Non-overlapping meetings (can attend all)
        int[][] intervals2 = {{7, 10}, {2, 4}};
        System.out.println("Scenario 2: " + solver.canAttendMeetings(intervals2) + " (Expected: true)");

        // Scenario 3: Consecutive meetings touching at boundary [2, 4] and [4, 6] (can attend)
        int[][] intervals3 = {{2, 4}, {4, 6}};
        System.out.println("Scenario 3 (touching endpoints): " + solver.canAttendMeetings(intervals3) + " (Expected: true)");

        // Scenario 4: Empty or single meeting
        int[][] intervals4 = {};
        System.out.println("Scenario 4 (empty): " + solver.canAttendMeetings(intervals4) + " (Expected: true)");
    }
}
