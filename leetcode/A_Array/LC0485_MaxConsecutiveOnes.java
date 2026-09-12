package leetcode.A_Array;

import java.util.Arrays;

/**
 * LeetCode 485: Max Consecutive Ones
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/max-consecutive-ones/
 *
 * Algorithm:
 * - One-Pass Linear Scan with Reset Counter
 *
 * Concepts:
 * - Maintain a running count of consecutive 1s (`currentCount`) and a global maximum (`maxCount`).
 * - For each number:
 *     - If it is 1: increment `currentCount` and update `maxCount = Math.max(maxCount, currentCount)`.
 *     - If it is 0: reset `currentCount` back to 0.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the array.
 * - Space Complexity: O(1) - Uses only two integer counters.
 */
public class LC0485_MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxCount = 0;
        int currentCount = 0;

        for (int num : nums) {
            if (num == 1) {
                currentCount++;
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                }
            } else {
                currentCount = 0;
            }
        }

        return maxCount;
    }

    public static void main(String[] args) {
        LC0485_MaxConsecutiveOnes solver = new LC0485_MaxConsecutiveOnes();

        // Scenario 1: Standard binary array
        int[] nums1 = {1, 1, 0, 1, 1, 1};
        System.out.println("Scenario 1: " + Arrays.toString(nums1));
        System.out.println("Result:     " + solver.findMaxConsecutiveOnes(nums1) + " | Expected: 3\n");

        // Scenario 2: Trailing streak of ones
        int[] nums2 = {1, 0, 1, 1, 0, 1};
        System.out.println("Scenario 2: " + Arrays.toString(nums2));
        System.out.println("Result:     " + solver.findMaxConsecutiveOnes(nums2) + " | Expected: 2\n");

        // Scenario 3: All ones
        int[] nums3 = {1, 1, 1, 1};
        System.out.println("Scenario 3: " + Arrays.toString(nums3));
        System.out.println("Result:     " + solver.findMaxConsecutiveOnes(nums3) + " | Expected: 4\n");

        // Scenario 4: All zeroes
        int[] nums4 = {0, 0, 0};
        System.out.println("Scenario 4: " + Arrays.toString(nums4));
        System.out.println("Result:     " + solver.findMaxConsecutiveOnes(nums4) + " | Expected: 0");
    }
}
