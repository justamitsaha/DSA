package leetcode.I_Intervals;

/**
 * LeetCode 35: Search Insert Position
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/search-insert-position/
 *
 * Algorithm:
 * - Binary Search (Lower Bound / Insertion Point Finder)
 *
 * Concepts:
 * - Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * - If not found, return the index where it would be if it were inserted in order.
 * - Standard Binary Search:
 *   - When nums[mid] == target, return `mid`.
 *   - When nums[mid] < target, search right: `start = mid + 1`.
 *   - When nums[mid] > target, search left: `end = mid - 1`.
 * - When the loop terminates (`start > end`), `start` points exactly to the insertion index because:
 *   - All elements to the left of `start` are strictly less than `target`.
 *   - All elements to the right of `end` (from `start` onwards) are strictly greater than `target`.
 *
 * Complexity:
 * - Time Complexity:  O(log n) - Halving the search space each step.
 * - Space Complexity: O(1)     - Constant auxiliary space.
 */
public class LC0035_SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // When not found, start is the correct insertion index
        return start;
    }

    public static void main(String[] args) {
        LC0035_SearchInsertPosition solver = new LC0035_SearchInsertPosition();

        int[] nums = {1, 3, 5, 6};

        // Scenario 1: Target exists in array
        System.out.println("Insert 5 into [1, 3, 5, 6]: " + solver.searchInsert(nums, 5) + " (Expected: 2)");

        // Scenario 2: Target does not exist, inserted in middle
        System.out.println("Insert 2 into [1, 3, 5, 6]: " + solver.searchInsert(nums, 2) + " (Expected: 1)");

        // Scenario 3: Target greater than all elements (insert at end)
        System.out.println("Insert 7 into [1, 3, 5, 6]: " + solver.searchInsert(nums, 7) + " (Expected: 4)");

        // Scenario 4: Target smaller than all elements (insert at beginning)
        System.out.println("Insert 0 into [1, 3, 5, 6]: " + solver.searchInsert(nums, 0) + " (Expected: 0)");
    }
}
