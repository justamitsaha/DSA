package leetcode.J_BinarySearch_1;

/**
 * LeetCode 153: Find Minimum in Rotated Sorted Array
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * Algorithm:
 * - Modified Binary Search (Pivot / Inflection Point Detection)
 *
 * Concepts:
 * - A sorted array of unique elements was rotated between 1 and n times.
 * - Key Observation:
 *   - Compare `nums[mid]` with the last element `nums[n - 1]` (or `nums[end]`):
 *     1. If `nums[mid] <= nums[n - 1]`:
 *        The right half is sorted! The minimum could be `nums[mid]` itself or lie somewhere to its left.
 *        Record `ans = nums[mid]` and eliminate the right half (`end = mid - 1`).
 *     2. If `nums[mid] > nums[n - 1]`:
 *        `nums[mid]` belongs to the larger left rotated portion. The minimum MUST lie strictly to the right.
 *        Eliminate the left half (`start = mid + 1`).
 *
 * Complexity:
 * - Time Complexity:  O(log n) - Halving search space at each iteration.
 * - Space Complexity: O(1)     - Constant space.
 */
public class LC0153_FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int n = nums.length;
        int start = 0;
        int end = n - 1;
        int ans = nums[0];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // If mid element is <= last element, mid is in the rotated right portion (or array is unrotated)
            if (nums[mid] <= nums[n - 1]) {
                ans = nums[mid];
                end = mid - 1; // Try to find an even smaller element to the left
            } else {
                start = mid + 1; // Minimum must be in the right half
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LC0153_FindMinimumInRotatedSortedArray solver = new LC0153_FindMinimumInRotatedSortedArray();

        // Scenario 1: Rotated 3 times
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Scenario 1 [3, 4, 5, 1, 2]: " + solver.findMin(nums1) + " (Expected: 1)");

        // Scenario 2: Rotated 4 times
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Scenario 2 [4, 5, 6, 7, 0, 1, 2]: " + solver.findMin(nums2) + " (Expected: 0)");

        // Scenario 3: Rotated n times (fully sorted / unrotated)
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Scenario 3 [11, 13, 15, 17]: " + solver.findMin(nums3) + " (Expected: 11)");

        // Scenario 4: Single element array
        int[] nums4 = {1};
        System.out.println("Scenario 4 [1]: " + solver.findMin(nums4) + " (Expected: 1)");

        // Scenario 5: Two elements rotated
        int[] nums5 = {2, 1};
        System.out.println("Scenario 5 [2, 1]: " + solver.findMin(nums5) + " (Expected: 1)");
    }
}
