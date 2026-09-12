package leetcode.D_HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15: 3Sum
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/3sum/
 *
 * Algorithm:
 * - Sorting + Two-Pointer Search with Duplicate Skipping
 *
 * Concepts:
 * - We must find all unique triplets [nums[i], nums[j], nums[k]] such that i != j != k and nums[i] + nums[j] + nums[k] == 0.
 * - The solution set must NOT contain duplicate triplets.
 * - Algorithm Steps:
 *     1. Sort the array in ascending order: O(n log n).
 *     2. Fix the first element `nums[i]` in a loop from 0 to n - 3:
 *        - If `nums[i] > 0`, break early (sum of 3 positive numbers can never be 0).
 *        - If `i > 0 && nums[i] == nums[i - 1]`, skip to avoid duplicate triplets for the first number.
 *     3. Use two pointers `left = i + 1` and `right = n - 1` to find pairs where `nums[left] + nums[right] == -nums[i]`.
 *        - When a sum is 0, add triplet to results, then advance both pointers while skipping identical elements.
 *        - If sum < 0, advance `left++`.
 *        - If sum > 0, decrement `right--`.
 *
 * Complexity:
 * - Time Complexity:  O(n^2) - O(n log n) sort + O(n^2) two-pointer search.
 * - Space Complexity: O(1) or O(n) depending on sort implementation (excluding output list).
 */
public class LC0015_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return triplets;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Optimization: If smallest number is > 0, sum cannot be 0
            if (nums[i] > 0) {
                break;
            }

            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate elements on left and right
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return triplets;
    }

    public static void main(String[] args) {
        LC0015_ThreeSum solver = new LC0015_ThreeSum();

        // Scenario 1: Standard case with multiple triplets
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Scenario 1: " + Arrays.toString(nums1));
        System.out.println("  Triplets: " + solver.threeSum(nums1));
        System.out.println("  Expected: [[-1, -1, 2], [-1, 0, 1]]\n");

        // Scenario 2: No possible triplets [0, 1, 1]
        int[] nums2 = {0, 1, 1};
        System.out.println("Scenario 2: " + Arrays.toString(nums2));
        System.out.println("  Triplets: " + solver.threeSum(nums2));
        System.out.println("  Expected: []\n");

        // Scenario 3: All zeroes [0, 0, 0] -> [[0, 0, 0]]
        int[] nums3 = {0, 0, 0};
        System.out.println("Scenario 3: " + Arrays.toString(nums3));
        System.out.println("  Triplets: " + solver.threeSum(nums3));
        System.out.println("  Expected: [[0, 0, 0]]");
    }
}
