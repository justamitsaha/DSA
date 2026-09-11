import java.util.*;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Create a min-heap to store the end times of meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Add the end time of the first meeting to the min-heap
        minHeap.offer(intervals[0][1]);

        // Iterate through the remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            // If the start time of the current meeting is later than or equal to
            // the end time of the earliest ending meeting, we can reuse the room
            // by removing the earliest ending meeting from the min-heap
            if (currentStart >= minHeap.peek()) {
                minHeap.poll();
            }

            // Add the end time of the current meeting to the min-heap
            minHeap.offer(currentEnd);
        }

        // The size of the min-heap represents the number of meeting rooms needed
        return minHeap.size();
    }
}

