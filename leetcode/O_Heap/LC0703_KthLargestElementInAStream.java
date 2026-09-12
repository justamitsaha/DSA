package leetcode.O_Heap;

import java.util.PriorityQueue;

/**
 * LeetCode 703: Kth Largest Element in a Stream
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * Algorithm:
 * - Bounded Min-Heap of Size k
 *
 * Concepts:
 * - Maintain the `k` largest elements from a continuous stream of incoming numbers.
 * - Min-Heap Invariant:
 *   - The heap stores exactly `k` elements (or fewer if fewer than k numbers have been ingested so far).
 *   - The top of the Min-Heap is the SMALLEST of the `k` largest elements, which is by definition the `k`th largest element!
 * - On `add(val)`:
 *   - If `minHeap.size() < k`, simply offer `val`.
 *   - If `minHeap.size() == k` and `val > minHeap.peek()`, evict the smallest (`poll()`) and insert `val`.
 *   - Return `minHeap.peek()`.
 *
 * Complexity:
 * - Time Complexity:
 *   - Initialization: O(N log k) - Where N is initial nums.length.
 *   - `add`:          O(log k)   - Inserting and polling from size-k heap.
 * - Space Complexity: O(k)       - Priority queue stores at most k elements.
 */
public class LC0703_KthLargestElementInAStream {

    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public LC0703_KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k);

        if (nums != null) {
            for (int num : nums) {
                add(num);
            }
        }
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {
        // Scenario 1: k = 3, nums = [4, 5, 8, 2]
        int[] initial = {4, 5, 8, 2};
        LC0703_KthLargestElementInAStream kthLargest = new LC0703_KthLargestElementInAStream(3, initial);

        System.out.println("add(3): " + kthLargest.add(3) + " (Expected: 4)");
        System.out.println("add(5): " + kthLargest.add(5) + " (Expected: 5)");
        System.out.println("add(10): " + kthLargest.add(10) + " (Expected: 5)");
        System.out.println("add(9): " + kthLargest.add(9) + " (Expected: 8)");
        System.out.println("add(4): " + kthLargest.add(4) + " (Expected: 8)");
    }
}
