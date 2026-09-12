package leetcode.B_Sorting;

import java.util.Arrays;

/**
 * LeetCode 169: Majority Element
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/majority-element/
 *
 * Algorithms:
 * 1. Boyer-Moore Voting Algorithm -> Optimal Time O(n), Space O(1)
 * 2. Sorting Approach             -> Time O(n log n), Space O(1) or O(n)
 *
 * Problem:
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 *
 * Boyer-Moore Voting Intuition:
 * - Since the majority element appears more than n/2 times, its count will outnumber
 *   all other elements combined!
 * - We maintain a `candidate` and a `count`.
 * - For each number:
 *     - If count == 0, we assign candidate = current number.
 *     - If current number == candidate, increment count++.
 *     - Otherwise, decrement count-- (different elements cancel each other out).
 * - The surviving candidate is guaranteed to be the majority element.
 *
 * Complexity:
 * - Boyer-Moore Voting: Time O(n), Space O(1)
 * - Sorting:            Time O(n log n), Space O(1)
 */
public class LC0169_MajorityElement {

    /**
     * Approach 1: Boyer-Moore Voting Algorithm (Optimal O(n) Time, O(1) Space).
     */
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    /**
     * Approach 2: Sorting Approach (Intuitive).
     * If the array is sorted, the majority element must occupy index n / 2.
     */
    public int majorityElementSorting(int[] nums) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        return copy[copy.length / 2];
    }

    public static void main(String[] args) {
        LC0169_MajorityElement solver = new LC0169_MajorityElement();

        // Scenario 1: Standard majority element [3, 2, 3] -> 3
        int[] nums1 = {3, 2, 3};
        System.out.println("Scenario 1: " + Arrays.toString(nums1));
        System.out.println("Boyer-Moore: " + solver.majorityElement(nums1) + " | Expected: 3");
        System.out.println("Sorting:     " + solver.majorityElementSorting(nums1) + " | Expected: 3\n");

        // Scenario 2: Larger array with negative values [2, 2, 1, 1, 1, 2, 2] -> 2
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Scenario 2: " + Arrays.toString(nums2));
        System.out.println("Boyer-Moore: " + solver.majorityElement(nums2) + " | Expected: 2\n");

        // Scenario 3: Single element array [42] -> 42
        int[] nums3 = {42};
        System.out.println("Scenario 3: " + Arrays.toString(nums3));
        System.out.println("Boyer-Moore: " + solver.majorityElement(nums3) + " | Expected: 42\n");

        // Scenario 4: Negative numbers [-1, 1, -1, -1, 2] -> -1
        int[] nums4 = {-1, 1, -1, -1, 2};
        System.out.println("Scenario 4: " + Arrays.toString(nums4));
        System.out.println("Boyer-Moore: " + solver.majorityElement(nums4) + " | Expected: -1");
    }
}
