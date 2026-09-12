package leetcode.B_Sorting;

import java.util.Arrays;

/**
 * LeetCode 283: Move Zeroes
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/move-zeroes/
 *
 * Algorithm:
 * - Two-Pointer In-Place Zero Compaction (Snowball / Partitioning)
 *
 * Problem:
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * Two-Pointer Intuition:
 * - Pointer `lastNonZeroIndex` tracks where the next non-zero element should be placed.
 * - Pointer `current` iterates through the entire array:
 *     - Whenever `nums[current] != 0`:
 *         - Swap `nums[lastNonZeroIndex]` with `nums[current]`.
 *         - Increment `lastNonZeroIndex++`.
 * - All non-zero elements bubble forward into order, pushing zeros backward without extra memory.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the array.
 * - Space Complexity: O(1) - In-place swaps without extra storage.
 */
public class LC0283_MoveZeroes {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int lastNonZeroIndex = 0;

        for (int current = 0; current < nums.length; current++) {
            if (nums[current] != 0) {
                // Swap non-zero element with the first available zero slot
                int temp = nums[lastNonZeroIndex];
                nums[lastNonZeroIndex] = nums[current];
                nums[current] = temp;

                lastNonZeroIndex++;
            }
        }
    }

    public static void main(String[] args) {
        LC0283_MoveZeroes solver = new LC0283_MoveZeroes();

        // Scenario 1: Standard mixed array
        int[] nums1 = {0, 1, 0, 3, 12};
        solver.moveZeroes(nums1);
        System.out.println("Scenario 1 - Mixed:       " + Arrays.toString(nums1));
        System.out.println("Expected:                 [1, 3, 12, 0, 0]\n");

        // Scenario 2: Array with single zero
        int[] nums2 = {0};
        solver.moveZeroes(nums2);
        System.out.println("Scenario 2 - Single Zero: " + Arrays.toString(nums2));
        System.out.println("Expected:                 [0]\n");

        // Scenario 3: Array with no zeroes
        int[] nums3 = {1, 2, 3, 4};
        solver.moveZeroes(nums3);
        System.out.println("Scenario 3 - No Zeroes:   " + Arrays.toString(nums3));
        System.out.println("Expected:                 [1, 2, 3, 4]\n");

        // Scenario 4: Multiple zeroes at the front
        int[] nums4 = {0, 0, 0, 1};
        solver.moveZeroes(nums4);
        System.out.println("Scenario 4 - Front Zeroes:" + Arrays.toString(nums4));
        System.out.println("Expected:                 [1, 0, 0, 0]\n");

        // Scenario 5: Multiple zeroes at the end
        int[] nums5 = {1, 2, 0, 0};
        solver.moveZeroes(nums5);
        System.out.println("Scenario 5 - End Zeroes:  " + Arrays.toString(nums5));
        System.out.println("Expected:                 [1, 2, 0, 0]");
    }
}
