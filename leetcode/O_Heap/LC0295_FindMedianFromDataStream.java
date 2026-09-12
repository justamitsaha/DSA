package leetcode.O_Heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * LeetCode 295: Find Median from Data Stream
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/find-median-from-data-stream/
 *
 * Algorithm:
 * - Dual Heaps (Max-Heap for lower half, Min-Heap for upper half)
 *
 * Concepts:
 * - Finding the median of a dynamically growing stream of integers.
 * - Two-Heap Balance Invariant:
 *   - `maxHeap`: Stores the smaller half of numbers.
 *   - `minHeap`: Stores the larger half of numbers.
 *   - Balancing Condition:
 *     `maxHeap.size()` is either equal to `minHeap.size()` (even total numbers),
 *     or exactly `minHeap.size() + 1` (odd total numbers).
 *   - Value Invariant:
 *     Every element in `maxHeap` is <= every element in `minHeap`.
 * - Median Retrieval:
 *   - If total elements is odd, median is simply `maxHeap.peek()`.
 *   - If total elements is even, median is `(maxHeap.peek() + minHeap.peek()) / 2.0`.
 *
 * Complexity:
 * - Time Complexity:
 *   - `addNum`:     O(log n) - Inserting into a heap of size n/2.
 *   - `findMedian`: O(1)     - Peeking at the root of the heaps.
 * - Space Complexity: O(n)   - Storing all n numbers across both heaps.
 */
public class LC0295_FindMedianFromDataStream {

    private final PriorityQueue<Integer> maxHeap; // Lower half
    private final PriorityQueue<Integer> minHeap; // Upper half

    public LC0295_FindMedianFromDataStream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);

        // Maintain value invariant: max of lower half must be <= min of upper half
        minHeap.offer(maxHeap.poll());

        // Maintain size invariant: maxHeap size >= minHeap size
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        LC0295_FindMedianFromDataStream medianFinder = new LC0295_FindMedianFromDataStream();

        medianFinder.addNum(1);
        System.out.println("After adding 1, median: " + medianFinder.findMedian() + " (Expected: 1.0)");

        medianFinder.addNum(2);
        System.out.println("After adding 2, median: " + medianFinder.findMedian() + " (Expected: 1.5)");

        medianFinder.addNum(3);
        System.out.println("After adding 3, median: " + medianFinder.findMedian() + " (Expected: 2.0)");

        medianFinder.addNum(0);
        System.out.println("After adding 0, median: " + medianFinder.findMedian() + " (Expected: 1.5)");
    }
}
