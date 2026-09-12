package leetcode.G_Two_pointer_methods;

import java.util.Arrays;

/**
 * LeetCode 42: Trapping Rain Water
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/trapping-rain-water/
 *
 * Algorithms:
 * 1. Two-Pointer Approach           -> Optimal O(n) Time, O(1) Space
 * 2. Prefix & Suffix Arrays (DP)   -> O(n) Time, O(n) Space
 *
 * Core Intuition:
 * - Water trapped at index i is determined by:
 *     water[i] = max(0, min(maxLeft, maxRight) - height[i])
 * - In Approach 1 (Two Pointers):
 *     - We maintain pointers `left = 0` and `right = n - 1`, and running peaks `maxLeft` and `maxRight`.
 *     - If `maxLeft < maxRight`:
 *         - The bottleneck for the current left boundary is GUARANTEED to be `maxLeft`
 *           (it does not matter what lies between left and right; maxRight is already taller!).
 *         - Therefore, trapped water at left is `maxLeft - height[left]`. Advance `left++`.
 *     - Otherwise, the bottleneck is `maxRight`. Advance `right--`.
 *
 * Complexity:
 * - Two-Pointer: Time O(n), Space O(1).
 * - Prefix/Suffix Arrays: Time O(n), Space O(n).
 */
public class LC0042_TrappingRainWater {

    /**
     * Approach 1: Two-Pointer Technique (Optimal O(1) Space).
     */
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxLeft = 0;
        int maxRight = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    totalWater += maxLeft - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    totalWater += maxRight - height[right];
                }
                right--;
            }
        }

        return totalWater;
    }

    /**
     * Approach 2: Prefix and Suffix Max Arrays (O(n) Space).
     */
    public int trapDP(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return totalWater;
    }

    public static void main(String[] args) {
        LC0042_TrappingRainWater solver = new LC0042_TrappingRainWater();

        // Scenario 1: Standard case [0,1,0,2,1,0,1,3,2,1,2,1] -> 6
        int[] h1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Scenario 1: " + Arrays.toString(h1));
        System.out.println("  Two-Pointer Trapped Water: " + solver.trap(h1) + " | Expected: 6");
        System.out.println("  DP Array Trapped Water:    " + solver.trapDP(h1) + " | Expected: 6\n");

        // Scenario 2: [4, 2, 0, 3, 2, 5] -> 9
        int[] h2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Scenario 2: " + Arrays.toString(h2));
        System.out.println("  Trapped Water: " + solver.trap(h2) + " | Expected: 9\n");

        // Scenario 3: Monotonically decreasing (no valley, 0 water)
        int[] h3 = {5, 4, 3, 2, 1};
        System.out.println("Scenario 3 - Decreasing: " + solver.trap(h3) + " | Expected: 0");
    }
}
