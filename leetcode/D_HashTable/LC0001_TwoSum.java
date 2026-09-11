package leetcode.D_HashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1: Two Sum
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/two-sum/
 *
 * Problem:
 * Given an array of integers nums and an integer target, return indices of the two
 * numbers such that they add up to target. You may assume that each input would have
 * exactly one solution, and you may not use the same element twice.
 *
 * Complexities:
 * - Optimal (HashMap): Time O(n), Space O(n)
 * - Brute Force:       Time O(n^2), Space O(1)
 */
public class LC0001_TwoSum {

    /**
     * Optimal Solution: One-pass HashMap
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    /**
     * Brute Force Solution: Check all pairs
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        LC0001_TwoSum solver = new LC0001_TwoSum();

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("Output: " + Arrays.toString(solver.twoSum(nums1, target1))); // Expected: [0, 1]

        int[] nums2 = {3, 3};
        int target2 = 6;
        System.out.println("Input: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("Output: " + Arrays.toString(solver.twoSum(nums2, target2))); // Expected: [0, 1]
    }
}
