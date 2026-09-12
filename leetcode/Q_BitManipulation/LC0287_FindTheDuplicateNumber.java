package leetcode.Q_BitManipulation;

/**
 * LeetCode 287: Find the Duplicate Number
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-the-duplicate-number/
 *
 * Algorithm:
 * - Method 1: Floyd's Tortoise and Hare Cycle Detection (O(n) Time, O(1) Space)
 * - Method 2: Binary Search on Value Space (Pigeonhole Principle - O(n log n) Time, O(1) Space)
 *
 * Concepts:
 * - Given an array `nums` containing `n + 1` integers where each integer is in the range `[1, n]`.
 * - Floyd's Cycle-Finding Algorithm:
 *   - Treat the array as a linked list where `i -> nums[i]`.
 *   - Because each value is within `[1, n]`, `nums[0]` is never part of a cycle (it has no incoming edge).
 *   - The duplicate value has multiple incoming pointers, which creates the entrance of a cycle!
 *   - Phase 1: Fast pointer advances by 2 steps, slow pointer by 1 step until they intersect inside the cycle.
 *   - Phase 2: Reset slow to `nums[0]`. Advance both by 1 step until they meet at the cycle entrance, which is the duplicate!
 *
 * Complexity:
 * - Floyd's Cycle Detection:
 *   - Time Complexity:  O(n) - Single traversal of the cycle.
 *   - Space Complexity: O(1) - Constant auxiliary space without modifying array.
 */
public class LC0287_FindTheDuplicateNumber {

    /**
     * Floyd's Cycle-Finding Algorithm (Optimal O(n) time, O(1) space).
     */
    public int findDuplicate(int[] nums) {
        // Phase 1: Detect intersection within the cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Phase 2: Locate the entrance to the cycle (the duplicate number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /**
     * Binary Search on Value Range [1..n] (Pigeonhole Principle).
     */
    public int findDuplicateBinarySearch(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        int duplicate = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Count how many numbers in array are <= mid
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                duplicate = mid;
                high = mid - 1; // Duplicate lies in the lower half
            } else {
                low = mid + 1;  // Duplicate lies in the upper half
            }
        }

        return duplicate;
    }

    public static void main(String[] args) {
        LC0287_FindTheDuplicateNumber solver = new LC0287_FindTheDuplicateNumber();

        // Scenario 1: [1, 3, 4, 2, 2] -> 2
        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("Duplicate (Floyd's): " + solver.findDuplicate(nums1) + " (Expected: 2)");
        System.out.println("Duplicate (BinarySearch): " + solver.findDuplicateBinarySearch(nums1) + " (Expected: 2)");

        // Scenario 2: [3, 1, 3, 4, 2] -> 3
        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println("Duplicate (Floyd's): " + solver.findDuplicate(nums2) + " (Expected: 3)");

        // Scenario 3: All duplicates [2, 2, 2, 2, 2] -> 2
        int[] nums3 = {2, 2, 2, 2, 2};
        System.out.println("Duplicate (all same): " + solver.findDuplicate(nums3) + " (Expected: 2)");
    }
}
