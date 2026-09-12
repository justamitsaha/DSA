package leetcode.O_Heap;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * LeetCode 215: Kth Largest Element in an Array
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Algorithm:
 * - Method 1: Min-Heap of Size k (O(n log k))
 * - Method 2: QuickSelect (Hoare's Selection Algorithm - O(n) Average Time)
 *
 * Concepts:
 * - Given an integer array `nums` and an integer `k`, return the `k`th largest element in the array.
 * - Min-Heap Approach:
 *   - Maintain a min-heap containing the `k` largest elements seen so far.
 *   - For each number: push into the heap. If heap size exceeds `k`, remove the smallest (`poll()`).
 *   - After examining all numbers, the root of the heap (`peek()`) is the `k`th largest element.
 * - QuickSelect Approach:
 *   - Partition the array around a random pivot (like QuickSort).
 *   - If pivot lands exactly at index `n - k`, the pivot element is the kth largest!
 *   - Recurse only into the side containing index `n - k`.
 *
 * Complexity:
 * - Min-Heap:
 *   - Time Complexity:  O(n log k)
 *   - Space Complexity: O(k)
 * - QuickSelect:
 *   - Time Complexity:  O(n) Average, O(n^2) Worst case
 *   - Space Complexity: O(1) Auxiliary
 */
public class LC0215_KthLargestElementInAnArray {

    /**
     * Min-Heap approach (guaranteed O(n log k) time).
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);

        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll(); // Evict smallest element, retaining the k largest
            }
        }

        return minHeap.peek();
    }

    /**
     * QuickSelect approach (O(n) average time).
     */
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int targetIndex = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, targetIndex);
    }

    private int quickSelect(int[] nums, int left, int right, int kSmallest) {
        if (left == right) {
            return nums[left];
        }

        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex);

        if (pivotIndex == kSmallest) {
            return nums[kSmallest];
        } else if (pivotIndex < kSmallest) {
            return quickSelect(nums, pivotIndex + 1, right, kSmallest);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, kSmallest);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LC0215_KthLargestElementInAnArray solver = new LC0215_KthLargestElementInAnArray();

        // Scenario 1: [3, 2, 1, 5, 6, 4], k = 2 -> 5
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        System.out.println("Heap: 2nd largest in [3, 2, 1, 5, 6, 4]: " + solver.findKthLargest(nums1, 2) + " (Expected: 5)");
        System.out.println("QuickSelect: " + solver.findKthLargestQuickSelect(nums1.clone(), 2) + " (Expected: 5)");

        // Scenario 2: [3, 2, 3, 1, 2, 4, 5, 5, 6], k = 4 -> 4
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("Heap: 4th largest: " + solver.findKthLargest(nums2, 4) + " (Expected: 4)");
        System.out.println("QuickSelect: " + solver.findKthLargestQuickSelect(nums2.clone(), 4) + " (Expected: 4)");
    }
}
