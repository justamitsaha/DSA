package leetcode.O_Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 253: Meeting Rooms II
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/meeting-rooms-ii/
 *
 * Algorithm:
 * - Method 1: Min-Heap / Priority Queue on Active End-Times (O(n log n))
 * - Method 2: Two-Pointer Chronological Event Sweep (O(n log n) time, O(n) space)
 *
 * Concepts:
 * - Given an array of meeting time intervals [start_i, end_i], find the minimum number of conference rooms required.
 * - Min-Heap Approach:
 *   1. Sort meetings in ascending order by their start times.
 *   2. Maintain a Min-Heap containing the END times of all meetings currently occupying rooms.
 *   3. For each meeting:
 *      - If its start time >= `minHeap.peek()` (earliest room being vacated):
 *        That room is freed and can be reused! Poll that meeting's end time.
 *      - Regardless, push the current meeting's end time into the heap (allocating or continuing that room).
 *   4. The peak number of rooms held simultaneously equals `minHeap.size()`.
 *
 * Complexity:
 * - Time Complexity:  O(n log n) - Dominated by sorting meetings and heap operations.
 * - Space Complexity: O(n)       - Priority queue stores at most n end times.
 */
public class LC0253_MeetingRoomsII {

    /**
     * Min-Heap approach.
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort meetings by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap to store the end times of active meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the end time of the first meeting
        minHeap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // If the earliest finishing meeting ends before or at current start, reuse its room
            if (currentStart >= minHeap.peek()) {
                minHeap.poll();
            }

            // Assign room (push current meeting's end time)
            minHeap.offer(currentEnd);
        }

        return minHeap.size();
    }

    /**
     * Chronological Event Sweep (Two-Pointer).
     */
    public int minMeetingRoomsTwoPointer(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int n = intervals.length;
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int roomsNeeded = 0;
        int endPointer = 0;

        for (int startPointer = 0; startPointer < n; startPointer++) {
            if (startTimes[startPointer] < endTimes[endPointer]) {
                roomsNeeded++;
            } else {
                endPointer++;
            }
        }

        return roomsNeeded;
    }

    public static void main(String[] args) {
        LC0253_MeetingRoomsII solver = new LC0253_MeetingRoomsII();

        // Scenario 1: [0, 30], [5, 10], [15, 20] -> 2 rooms
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Scenario 1 (Heap): " + solver.minMeetingRooms(intervals1) + " (Expected: 2)");
        System.out.println("Scenario 1 (Sweep): " + solver.minMeetingRoomsTwoPointer(intervals1) + " (Expected: 2)");

        // Scenario 2: [7, 10], [2, 4] -> 1 room (non-overlapping)
        int[][] intervals2 = {{7, 10}, {2, 4}};
        System.out.println("Scenario 2: " + solver.minMeetingRooms(intervals2) + " (Expected: 1)");

        // Scenario 3: Adjacent meetings [1, 5], [5, 10] -> 1 room (reusable at boundary)
        int[][] intervals3 = {{1, 5}, {5, 10}};
        System.out.println("Scenario 3: " + solver.minMeetingRooms(intervals3) + " (Expected: 1)");
    }
}
