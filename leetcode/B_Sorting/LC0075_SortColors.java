package leetcode.B_Sorting;

import java.util.Arrays;

/**
 * LeetCode 75: Sort Colors
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/sort-colors/
 *
 * Algorithms:
 * 1. Dutch National Flag Algorithm (Dijkstra's 3-Way Partitioning) -> Optimal One-Pass
 * 2. Counting Sort (Frequency Array) -> Two-Pass
 *
 * Problem:
 * Given an array nums with n objects colored red (0), white (1), or blue (2), sort them
 * in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * You must solve this problem without using the library's sort function.
 *
 * Dutch National Flag Intuition:
 * - We partition the array into 4 zones using 3 pointers (low, mid, high):
 *     - nums[0 .. low-1]  : 0s (Red)
 *     - nums[low .. mid-1]: 1s (White)
 *     - nums[mid .. high] : Unexplored
 *     - nums[high+1 .. n-1]: 2s (Blue)
 * - If nums[mid] == 0: Swap(nums[low], nums[mid]), low++, mid++
 * - If nums[mid] == 1: mid++
 * - If nums[mid] == 2: Swap(nums[mid], nums[high]), high-- (do not advance mid, inspect swapped element)
 *
 * Complexity:
 * - One-Pass Dutch National Flag: Time O(n), Space O(1)
 * - Two-Pass Counting Sort:       Time O(n), Space O(1) (fixed 3-element frequency array)
 */
public class LC0075_SortColors {

    /**
     * Approach 1: Dutch National Flag Algorithm (Optimal One-Pass, In-Place).
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    /**
     * Approach 2: Counting Sort (Two-Pass).
     * Counts occurrences of 0, 1, and 2, then overwrites the array.
     */
    public void sortColorsCountingSort(int[] nums) {
        int[] count = new int[3];

        // Pass 1: Count frequencies
        for (int num : nums) {
            count[num]++;
        }

        // Pass 2: Overwrite array according to counts
        int index = 0;
        for (int color = 0; color < 3; color++) {
            while (count[color] > 0) {
                nums[index++] = color;
                count[color]--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        LC0075_SortColors solver = new LC0075_SortColors();

        // Scenario 1: Mixed colors
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        solver.sortColors(nums1);
        System.out.println("Scenario 1 - Mixed:         " + Arrays.toString(nums1));
        System.out.println("Expected:                   [0, 0, 1, 1, 2, 2]\n");

        // Scenario 2: Two colors
        int[] nums2 = {2, 0, 1};
        solver.sortColors(nums2);
        System.out.println("Scenario 2 - 2, 0, 1:       " + Arrays.toString(nums2));
        System.out.println("Expected:                   [0, 1, 2]\n");

        // Scenario 3: Already sorted
        int[] nums3 = {0, 0, 1, 1, 2, 2};
        solver.sortColors(nums3);
        System.out.println("Scenario 3 - Already Sorted:" + Arrays.toString(nums3));
        System.out.println("Expected:                   [0, 0, 1, 1, 2, 2]\n");

        // Scenario 4: All same color or single element
        int[] nums4 = {0};
        solver.sortColors(nums4);
        System.out.println("Scenario 4 - Single Element:" + Arrays.toString(nums4));
        System.out.println("Expected:                   [0]");
    }
}
