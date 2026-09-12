package leetcode.E_Stacks;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 503: Next Greater Element II
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/next-greater-element-ii/
 *
 * Algorithm:
 * - Monotonic Decreasing Stack with Circular Array Simulation (2 * N Iterations)
 *
 * Concepts:
 * - We need to find the next greater numeric element for each index in a CIRCULAR array.
 * - Circularity can be elegantly simulated by iterating through the array TWICE (indices 0 to 2*n - 1)
 *   and accessing elements using modulo: `nums[i % n]`.
 * - Monotonic Stack Properties:
 *     - Maintain a stack storing array INDICES in monotonic decreasing order of their values.
 *     - For each element `curr = nums[i % n]`:
 *         - While the stack is not empty and `nums[stack.peek()] < curr`:
 *             - The next greater element for `stack.pop()` is `curr`!
 *         - During the first pass (`i < n`), push `i` onto the stack.
 * - Pre-fill the result array with `-1` so any element that never finds a greater value defaults to `-1`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Each index is pushed onto the stack at most once and popped at most once.
 * - Space Complexity: O(n) - Stack holds at most n indices.
 */
public class LC0503_NextGreaterElementII {

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);

        Deque<Integer> stack = new ArrayDeque<>(); // Stores indices

        // Loop twice through the array to simulate circular traversal
        for (int i = 0; i < 2 * n; i++) {
            int current = nums[i % n];

            while (!stack.isEmpty() && nums[stack.peek()] < current) {
                int resolvedIndex = stack.pop();
                result[resolvedIndex] = current;
            }

            // Only push indices during the first pass
            if (i < n) {
                stack.push(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LC0503_NextGreaterElementII solver = new LC0503_NextGreaterElementII();

        // Scenario 1: Standard circular wrapping [1, 2, 1] -> [2, -1, 2]
        int[] nums1 = {1, 2, 1};
        System.out.println("Scenario 1: " + Arrays.toString(nums1));
        System.out.println("  Next Greater: " + Arrays.toString(solver.nextGreaterElements(nums1)));
        System.out.println("  Expected:     [2, -1, 2]\n");

        // Scenario 2: Strictly decreasing array [5, 4, 3, 2, 1] -> [-1, 5, 5, 5, 5]
        int[] nums2 = {5, 4, 3, 2, 1};
        System.out.println("Scenario 2: " + Arrays.toString(nums2));
        System.out.println("  Next Greater: " + Arrays.toString(solver.nextGreaterElements(nums2)));
        System.out.println("  Expected:     [-1, 5, 5, 5, 5]\n");

        // Scenario 3: All identical elements [2, 2, 2] -> [-1, -1, -1]
        int[] nums3 = {2, 2, 2};
        System.out.println("Scenario 3: " + Arrays.toString(nums3));
        System.out.println("  Next Greater: " + Arrays.toString(solver.nextGreaterElements(nums3)));
        System.out.println("  Expected:     [-1, -1, -1]");
    }
}
