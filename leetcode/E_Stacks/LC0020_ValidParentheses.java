package leetcode.E_Stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 20: Valid Parentheses
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/valid-parentheses/
 *
 * Algorithm:
 * - Stack-Based Bracket Matching
 *
 * Concepts:
 * - A string of brackets is valid if:
 *     1. Open brackets must be closed by the same type of brackets.
 *     2. Open brackets must be closed in the correct order (LIFO - Last In First Out).
 *     3. Every close bracket has a corresponding open bracket of the same type.
 * - By using a Stack (LIFO data structure):
 *     - When an opening bracket '(', '{', '[' is encountered, push it onto the stack (or push expected closing bracket).
 *     - When a closing bracket ')', '}', ']' is encountered:
 *         - If the stack is empty -> invalid (no matching opening bracket).
 *         - Pop the top and verify it matches the closing bracket type.
 *     - After scanning all characters, the stack must be empty for the string to be valid.
 *
 * Note on Java Stack:
 * - `java.util.ArrayDeque` is preferred over legacy `java.util.Stack` as it is faster and not synchronized.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the string of length n.
 * - Space Complexity: O(n) - Stack stores at most n characters in the worst case (e.g. "(((((").
 */
public class LC0020_ValidParentheses {

    /**
     * Determines if the input string s has valid bracket matching.
     *
     * @param s string consisting of '(', ')', '{', '}', '[' and ']'
     * @return true if valid, false otherwise
     */
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false; // Odd length can never have complete pairs
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Push corresponding expected closing character onto stack
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else {
                // If closing bracket arrives but stack is empty or mismatch
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }

        // Stack must be completely empty at the end
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        LC0020_ValidParentheses solver = new LC0020_ValidParentheses();

        // Scenario 1: Simple valid brackets
        String s1 = "()";
        System.out.println("Scenario 1 - \"()\":      " + solver.isValid(s1) + " | Expected: true");

        // Scenario 2: Multiple valid consecutive and nested brackets
        String s2 = "()[]{}";
        String s3 = "{[()]}";
        System.out.println("Scenario 2 - \"()[]{}\":  " + solver.isValid(s2) + " | Expected: true");
        System.out.println("Scenario 2 - \"{[()]}\":  " + solver.isValid(s3) + " | Expected: true");

        // Scenario 3: Mismatched bracket types
        String s4 = "(]";
        String s5 = "([)]";
        System.out.println("Scenario 3 - \"(]\":      " + solver.isValid(s4) + " | Expected: false");
        System.out.println("Scenario 3 - \"([)]\":    " + solver.isValid(s5) + " | Expected: false");

        // Scenario 4: Premature closing bracket (empty stack)
        String s6 = "]";
        System.out.println("Scenario 4 - \"]\":       " + solver.isValid(s6) + " | Expected: false");

        // Scenario 5: Unclosed opening brackets
        String s7 = "(((";
        System.out.println("Scenario 5 - \"(((\":     " + solver.isValid(s7) + " | Expected: false");
    }
}
