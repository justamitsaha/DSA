package leetcode.G_Two_pointer_methods;

import java.util.Arrays;

/**
 * LeetCode 11: Container With Most Water
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/container-with-most-water/
 *
 * Algorithm:
 * - Two-Pointer Greedy Inward Squeeze
 *
 * Concepts:
 * - We have vertical lines at coordinates (i, height[i]).
 * - The area formed between lines at indices `left` and `right` is:
 *     area = (right - left) * min(height[left], height[right])
 * - Two-Pointer Greedy Proof:
 *     - Start with the widest possible container: `left = 0, right = n - 1`.
 *     - The height is bottlenecked by `min(height[left], height[right])`.
 *     - If we move the taller line inward, the width decreases AND the height is still bounded by the shorter line,
 *       so the area can NEVER increase!
 *     - Therefore, the only logical choice to find a potentially larger area is to advance the SHORTER line inward!
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass where left and right converge.
 * - Space Complexity: O(1) - Constant memory.
 */
public class LC0011_ContainerWithMostWater {

    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            int currentWidth = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int currentArea = currentWidth * minHeight;

            if (currentArea > maxWater) {
                maxWater = currentArea;
            }

            // Move pointer with smaller height inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        LC0011_ContainerWithMostWater solver = new LC0011_ContainerWithMostWater();

        // Scenario 1: Standard case [1, 8, 6, 2, 5, 4, 8, 3, 7] -> 49 (between index 1 and 8: width 7, height 7)
        int[] h1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Scenario 1: " + Arrays.toString(h1));
        System.out.println("  Max Water: " + solver.maxArea(h1) + " | Expected: 49\n");

        // Scenario 2: Two elements [1, 1] -> 1
        int[] h2 = {1, 1};
        System.out.println("Scenario 2: " + Arrays.toString(h2));
        System.out.println("  Max Water: " + solver.maxArea(h2) + " | Expected: 1\n");

        // Scenario 3: Monotonically increasing [1, 2, 4, 8, 16] -> 16
        int[] h3 = {1, 2, 4, 8, 16};
        System.out.println("Scenario 3: " + Arrays.toString(h3));
        System.out.println("  Max Water: " + solver.maxArea(h3) + " | Expected: 16");
    }
}
