package leetcode.S_GreedyAlgorithm;

/**
 * LeetCode 921: Minimum Add to Make Parentheses Valid
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 *
 * Algorithm:
 * - Greedy Balance Counter (O(n) Time, O(1) Space)
 *
 * Concepts:
 * - A parentheses string is valid if:
 *   1. It is empty.
 *   2. It can be written as AB (A concatenated with B), where A and B are valid strings.
 *   3. It can be written as (A), where A is a valid string.
 * - Given a string `s`, find the minimum number of insertions needed to make it valid.
 * - Greedy Balance Logic:
 *   - Track `openNeeded` (unmatched `(` that still need closing `)`) and `closeNeeded` (unmatched `)` that need `(` inserted).
 *   - For each character:
 *     - If `'('`: increment `openNeeded`.
 *     - If `')'`:
 *       - If `openNeeded > 0`, decrement `openNeeded` (it successfully closes a pending open paren).
 *       - Else, increment `closeNeeded` (a new `(` must be inserted to balance this orphaned `)`).
 *   - Total additions required = `openNeeded + closeNeeded`.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the string.
 * - Space Complexity: O(1) - Constant auxiliary space (no stack allocation needed).
 */
public class LC0921_MinimumAddToMakeParenthesesValid {

    public int minAddToMakeValid(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int openNeeded = 0;
        int closeNeeded = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                openNeeded++;
            } else if (ch == ')') {
                if (openNeeded > 0) {
                    openNeeded--;
                } else {
                    closeNeeded++;
                }
            }
        }

        return openNeeded + closeNeeded;
    }

    public static void main(String[] args) {
        LC0921_MinimumAddToMakeParenthesesValid solver = new LC0921_MinimumAddToMakeParenthesesValid();

        // Scenario 1: "())" -> 1 (add '(' at start)
        System.out.println("String \"())\": " + solver.minAddToMakeValid("())") + " (Expected: 1)");

        // Scenario 2: "(((" -> 3 (add 3 ')' at end)
        System.out.println("String \"(((\": " + solver.minAddToMakeValid("(((") + " (Expected: 3)");

        // Scenario 3: "()" -> 0 (already valid)
        System.out.println("String \"()\": " + solver.minAddToMakeValid("()") + " (Expected: 0)");

        // Scenario 4: "()))((" -> 4 (two '(' at start, two ')' at end)
        System.out.println("String \"()))((\": " + solver.minAddToMakeValid("()))((") + " (Expected: 4)");

        // Scenario 5: Empty string
        System.out.println("Empty string: " + solver.minAddToMakeValid("") + " (Expected: 0)");
    }
}
