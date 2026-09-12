package leetcode.E_Stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 155: Min Stack
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/min-stack/
 *
 * Algorithm:
 * - Two-Stack Synchronized Minimum Tracking (or Pair Stack)
 *
 * Problem Constraint:
 * - You must design a stack that supports push, pop, top, and retrieving the minimum element
 *   all in O(1) time complexity.
 *
 * Concepts:
 * - A standard stack only supports O(1) push, pop, and top, but finding min takes O(N).
 * - By maintaining an auxiliary stack (`minStack`):
 *     - `dataStack` stores every value as usual.
 *     - `minStack` stores the minimum value observed up to each element.
 *     - When pushing `val`:
 *         - `dataStack.push(val)`
 *         - If `minStack` is empty or `val <= minStack.peek()`: `minStack.push(val)`.
 *     - When popping:
 *         - If `dataStack.peek().equals(minStack.peek())`: pop from `minStack` as well!
 *         - Pop from `dataStack`.
 *     - `getMin()` simply inspects `minStack.peek()` in O(1).
 *
 * Complexity:
 * - Time Complexity:  O(1) for all operations (push, pop, top, getMin).
 * - Space Complexity: O(n) - Two stacks proportional to number of elements.
 */
public class LC0155_MinStack {

    private final Deque<Integer> dataStack;
    private final Deque<Integer> minStack;

    public LC0155_MinStack() {
        dataStack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        dataStack.push(val);
        // Push to minStack if empty or if val is a new or duplicate minimum
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            // Note: use equals() or intValue() to avoid Integer object reference comparison issues
            if (dataStack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            dataStack.pop();
        }
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        LC0155_MinStack minStack = new LC0155_MinStack();

        // Scenario: Push -2, 0, -3
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println("After pushing -2, 0, -3:");
        System.out.println("  getMin(): " + minStack.getMin() + " | Expected: -3");

        minStack.pop(); // Removes -3
        System.out.println("After pop():");
        System.out.println("  top():    " + minStack.top() + " | Expected: 0");
        System.out.println("  getMin(): " + minStack.getMin() + " | Expected: -2");

        // Testing duplicate minimum values
        minStack.push(-2);
        minStack.push(-2);
        System.out.println("After pushing two more -2s:");
        System.out.println("  getMin(): " + minStack.getMin() + " | Expected: -2");
        minStack.pop();
        System.out.println("After popping one -2:");
        System.out.println("  getMin(): " + minStack.getMin() + " | Expected: -2");
    }
}
