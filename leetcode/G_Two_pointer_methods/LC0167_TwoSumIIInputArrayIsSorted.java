package leetcode.G_Two_pointer_methods;

import java.util.Arrays;

/**
 * LeetCode 167: Two Sum II - Input Array Is Sorted
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Algorithm:
 * - Two-Pointer Inward Search on Sorted Array
 *
 * Concepts:
 * - Because the array is ALREADY sorted in ascending order:
 *     - Place `left = 0` and `right = numbers.length - 1`.
 *     - If `numbers[left] + numbers[right] == target`: we found our pair!
 *     - If `sum < target`: the sum is too small. Because numbers[right] is the largest available,
 *       we can only increase the sum by advancing `left++`.
 *     - If `sum > target`: the sum is too large. We can only decrease the sum by moving `right--`.
 * - The problem explicitly specifies 1-BASED INDEXING, so we return `[left + 1, right + 1]`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Converging linear scan.
 * - Space Complexity: O(1) - Two pointer variables.
 */
public class LC0167_TwoSumIIInputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // 1-based indexing required by problem
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        LC0167_TwoSumIIInputArrayIsSorted solver = new LC0167_TwoSumIIInputArrayIsSorted();

        // Scenario 1: Standard sorted array [2, 7, 11, 15], target = 9 -> [1, 2]
        int[] num1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Scenario 1: " + Arrays.toString(num1) + ", target = " + target1);
        System.out.println("  Indices:  " + Arrays.toString(solver.twoSum(num1, target1)) + " | Expected: [1, 2]\n");

        // Scenario 2: Array with duplicate target numbers [2, 3, 4], target = 6 -> [1, 3]
        int[] num2 = {2, 3, 4};
        int target2 = 6;
        System.out.println("Scenario 2: " + Arrays.toString(num2) + ", target = " + target2);
        System.out.println("  Indices:  " + Arrays.toString(solver.twoSum(num2, target2)) + " | Expected: [1, 3]\n");

        // Scenario 3: Negative numbers [-1, 0], target = -1 -> [1, 2]
        int[] num3 = {-1, 0};
        int target3 = -1;
        System.out.println("Scenario 3: " + Arrays.toString(num3) + ", target = " + target3);
        System.out.println("  Indices:  " + Arrays.toString(solver.twoSum(num3, target3)) + " | Expected: [1, 2]");
    }
}
