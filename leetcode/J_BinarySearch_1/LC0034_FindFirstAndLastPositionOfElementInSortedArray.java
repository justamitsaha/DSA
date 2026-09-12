package leetcode.J_BinarySearch_1;

import java.util.Arrays;

/**
 * LeetCode 34: Find First and Last Position of Element in Sorted Array
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Algorithm:
 * - Dual Binary Search (Modified Lower Bound & Upper Bound)
 *
 * Concepts:
 * - Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending position of a given `target` value.
 * - If target is not found in the array, return `[-1, -1]`.
 * - Run two separate binary searches:
 *   1. `findFirst(nums, target)`:
 *      - Standard binary search. When `nums[mid] == target`, record `ans = mid` and keep searching LEFT (`end = mid - 1`)
 *        to determine if an earlier occurrence exists.
 *   2. `findLast(nums, target)`:
 *      - Standard binary search. When `nums[mid] == target`, record `ans = mid` and keep searching RIGHT (`start = mid + 1`)
 *        to determine if a later occurrence exists.
 *
 * Complexity:
 * - Time Complexity:  O(log n) - Two binary search passes, each taking O(log n).
 * - Space Complexity: O(1)     - Constant extra space.
 */
public class LC0034_FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findFirst(nums, target);
        if (first == -1) {
            // Target does not exist at all, no need to search for last
            return new int[]{-1, -1};
        }
        int last = findLast(nums, target);

        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                ans = mid;
                // Target found; continue looking towards the left for the first occurrence
                end = mid - 1;
            }
        }

        return ans;
    }

    private int findLast(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                ans = mid;
                // Target found; continue looking towards the right for the last occurrence
                start = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LC0034_FindFirstAndLastPositionOfElementInSortedArray solver = new LC0034_FindFirstAndLastPositionOfElementInSortedArray();

        // Scenario 1: Target present multiple times
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        System.out.println("Scenario 1 (target 8): " + Arrays.toString(solver.searchRange(nums1, 8)) + " (Expected: [3, 4])");

        // Scenario 2: Target absent
        System.out.println("Scenario 2 (target 6): " + Arrays.toString(solver.searchRange(nums1, 6)) + " (Expected: [-1, -1])");

        // Scenario 3: Empty array
        int[] nums3 = {};
        System.out.println("Scenario 3 (empty): " + Arrays.toString(solver.searchRange(nums3, 0)) + " (Expected: [-1, -1])");

        // Scenario 4: Single element array - target present
        int[] nums4 = {1};
        System.out.println("Scenario 4 (single present): " + Arrays.toString(solver.searchRange(nums4, 1)) + " (Expected: [0, 0])");

        // Scenario 5: Single element array - target absent
        System.out.println("Scenario 5 (single absent): " + Arrays.toString(solver.searchRange(nums4, 2)) + " (Expected: [-1, -1])");

        // Scenario 6: All elements identical to target
        int[] nums6 = {2, 2, 2, 2, 2};
        System.out.println("Scenario 6 (all identical): " + Arrays.toString(solver.searchRange(nums6, 2)) + " (Expected: [0, 4])");
    }
}
