package leetcode.O_Heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LeetCode 1046: Last Stone Weight
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/last-stone-weight/
 *
 * Algorithm:
 * - Max-Heap Simulation
 *
 * Concepts:
 * - We are playing a game with stones. On each turn, we choose the heaviest two stones `x` and `y` (with `x <= y`):
 *   - If `x == y`, both stones are completely destroyed.
 *   - If `x != y`, the stone of weight `x` is destroyed, and the stone of weight `y` has new weight `y - x`.
 * - Max-Heap:
 *   - A Max-Heap guarantees that extracting the top two elements yields the two heaviest stones in O(log n) time.
 *   - While `heap.size() > 1`:
 *     - Poll `y` (heaviest) and `x` (second heaviest).
 *     - If `y > x`, offer the remaining difference `y - x` back into the heap.
 *   - When at most one stone remains, return its weight (or 0 if heap is empty).
 *
 * Complexity:
 * - Time Complexity:  O(n log n) - In the worst case, n - 1 collisions, each performing heap push/poll operations.
 * - Space Complexity: O(n)       - Storing stones in the PriorityQueue.
 */
public class LC1046_LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(); // Heaviest
            int x = maxHeap.poll(); // Second heaviest

            if (y > x) {
                maxHeap.offer(y - x);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    public static void main(String[] args) {
        LC1046_LastStoneWeight solver = new LC1046_LastStoneWeight();

        // Scenario 1: [2, 7, 4, 1, 8, 1] -> 1
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        System.out.println("Stones [2, 7, 4, 1, 8, 1]: " + solver.lastStoneWeight(stones1) + " (Expected: 1)");

        // Scenario 2: Single stone [1] -> 1
        int[] stones2 = {1};
        System.out.println("Single stone [1]: " + solver.lastStoneWeight(stones2) + " (Expected: 1)");

        // Scenario 3: All stones annihilate [2, 2] -> 0
        int[] stones3 = {2, 2};
        System.out.println("Equal stones [2, 2]: " + solver.lastStoneWeight(stones3) + " (Expected: 0)");
    }
}
