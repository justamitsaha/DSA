package leetcode.F_SlidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 239: Sliding Window Maximum
 * Difficulty: Hard
 * Link: https://leetcode.com/problems/sliding-window-maximum/
 *
 * Algorithm:
 * - Monotonic Decreasing Double-Ended Queue (Deque)
 *
 * Concepts:
 * - We slide a window of size k across array nums of size n.
 * - Naive scanning each window takes O(k) -> total O(n * k).
 * - A Monotonic Deque storing INDICES solves this in O(n) total time:
 *     1. The values represented by indices in the deque are kept in STRICTLY DESCENDING ORDER.
 *     2. Before inserting index `i`:
 *        - Evict stale indices from the FRONT that fall outside the window: `deque.peekFirst() < i - k + 1`.
 *        - Evict elements from the BACK that are smaller than `nums[i]`:
 *          they can never be the maximum of this or any subsequent window because `nums[i]` is both
 *          larger AND lasts longer in future windows!
 *     3. Insert `i` at the back.
 *     4. Once `i >= k - 1`, the maximum element of the current window is guaranteed to be at `deque.peekFirst()`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Each element is pushed and popped from the deque at most once.
 * - Space Complexity: O(k) - Deque holds at most k indices at any moment.
 */
public class LC0239_SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[]{};
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // Stores indices in decreasing value order

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that are out of the current sliding window [i - k + 1, i]
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 2. Remove indices whose corresponding values are smaller than nums[i]
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. Add current index to the back
            deque.offerLast(i);

            // 4. Record maximum once the first window of size k is formed
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LC0239_SlidingWindowMaximum solver = new LC0239_SlidingWindowMaximum();

        // Scenario 1: Standard mixed array [1,3,-1,-3,5,3,6,7], k = 3
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Scenario 1: " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("  Window Maxima: " + Arrays.toString(solver.maxSlidingWindow(nums1, k1)));
        System.out.println("  Expected:      [3, 3, 5, 5, 6, 7]\n");

        // Scenario 2: Single element array [1], k = 1
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("Scenario 2: " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("  Window Maxima: " + Arrays.toString(solver.maxSlidingWindow(nums2, k2)));
        System.out.println("  Expected:      [1]\n");

        // Scenario 3: Monotonically decreasing array [9, 8, 7, 6, 5], k = 3
        int[] nums3 = {9, 8, 7, 6, 5};
        int k3 = 3;
        System.out.println("Scenario 3: " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("  Window Maxima: " + Arrays.toString(solver.maxSlidingWindow(nums3, k3)));
        System.out.println("  Expected:      [9, 8, 7]");
    }
}
