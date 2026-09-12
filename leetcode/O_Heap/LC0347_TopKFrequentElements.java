package leetcode.O_Heap;

import java.util.*;

/**
 * LeetCode 347: Top K Frequent Elements
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Algorithm:
 * - Method 1: Min-Heap of Size k on Frequency Map (O(n log k))
 * - Method 2: Bucket Sort / Frequency Array (O(n) Linear Time)
 *
 * Concepts:
 * - Count the occurrences of each element using a frequency map.
 * - Min-Heap Approach:
 *   - Push each entry into a Min-Heap keyed on frequency.
 *   - When the heap exceeds size `k`, poll the least frequent element.
 *   - The remaining `k` elements are the top k frequent elements.
 * - Bucket Sort Approach:
 *   - Since frequency cannot exceed `n = nums.length`, create an array of lists `buckets[n + 1]`.
 *   - Place each number into `buckets[frequency]`.
 *   - Iterate from highest frequency `n` downwards, collecting numbers until `k` elements are gathered.
 *
 * Complexity:
 * - Min-Heap:   Time: O(n log k), Space: O(n)
 * - BucketSort: Time: O(n),       Space: O(n)
 */
public class LC0347_TopKFrequentElements {

    /**
     * Min-Heap approach.
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Min-heap ordered by frequency ascending
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
            Comparator.comparingInt(Map.Entry::getValue)
        );

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Evict least frequent
            }
        }

        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll().getKey();
        }

        return result;
    }

    /**
     * Optimal Bucket Sort approach (O(n) time).
     */
    @SuppressWarnings("unchecked")
    public int[] topKFrequentBucketSort(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // buckets[f] contains list of numbers that appear f times
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : frequencyMap.keySet()) {
            int freq = frequencyMap.get(key);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        int[] result = new int[k];
        int index = 0;

        for (int freq = buckets.length - 1; freq >= 0 && index < k; freq--) {
            if (buckets[freq] != null) {
                for (int num : buckets[freq]) {
                    result[index++] = num;
                    if (index == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LC0347_TopKFrequentElements solver = new LC0347_TopKFrequentElements();

        // Scenario 1: [1,1,1,2,2,3], k = 2
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        System.out.println("Top 2 (Heap): " + Arrays.toString(solver.topKFrequent(nums1, 2)));
        System.out.println("Top 2 (Bucket): " + Arrays.toString(solver.topKFrequentBucketSort(nums1, 2)));
        // Expected: [1, 2] (in any order)

        // Scenario 2: Single element [1], k = 1
        int[] nums2 = {1};
        System.out.println("Top 1 (single): " + Arrays.toString(solver.topKFrequent(nums2, 1)) + " (Expected: [1])");
    }
}
