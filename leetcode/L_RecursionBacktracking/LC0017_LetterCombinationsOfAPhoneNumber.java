package leetcode.L_RecursionBacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 17: Letter Combinations of a Phone Number
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Algorithm:
 * - Backtracking / Depth-First Search on Combinatorial Tree
 *
 * Concepts:
 * - Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 *   that the number could represent based on the telephone keypad mapping:
 *     2 -> "abc", 3 -> "def", 4 -> "ghi", 5 -> "jkl", 6 -> "mno", 7 -> "pqrs", 8 -> "tuv", 9 -> "wxyz".
 * - Decision Tree:
 *   - At each recursion level `index` (representing `digits.charAt(index)`):
 *     - Loop through all letters mapped to that digit.
 *     - Append the letter to `currentCombination`, recurse to `index + 1`.
 *     - Backtrack (pop character) to explore the next branch.
 *   - Base case: When `index == digits.length()`, a complete combination of length `n` is formed; add to result.
 *
 * Complexity:
 * - Time Complexity:  O(4^n * n) - Where n is digits.length(). At most 4 choices per digit (7 and 9 have 4 letters),
 *                     and building/copying each string takes O(n).
 * - Space Complexity: O(n) auxiliary recursion stack depth.
 */
public class LC0017_LetterCombinationsOfAPhoneNumber {

    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return combinations;
        }

        StringBuilder current = new StringBuilder();
        backtrack(digits, 0, current, combinations);
        return combinations;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> combinations) {
        if (index == digits.length()) {
            combinations.add(current.toString());
            return;
        }

        int digit = digits.charAt(index) - '0';
        String letters = KEYPAD[digit];

        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));
            backtrack(digits, index + 1, current, combinations);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        LC0017_LetterCombinationsOfAPhoneNumber solver = new LC0017_LetterCombinationsOfAPhoneNumber();

        // Scenario 1: Two digits
        String digits1 = "23";
        System.out.println("Digits \"23\": " + solver.letterCombinations(digits1));
        // Expected: [ad, ae, af, bd, be, bf, cd, ce, cf]

        // Scenario 2: Empty digits
        String digits2 = "";
        System.out.println("Digits \"\": " + solver.letterCombinations(digits2));
        // Expected: []

        // Scenario 3: Single digit
        String digits3 = "2";
        System.out.println("Digits \"2\": " + solver.letterCombinations(digits3));
        // Expected: [a, b, c]

        // Scenario 4: Digits with 4 mapped characters ('7' and '9')
        String digits4 = "79";
        List<String> res4 = solver.letterCombinations(digits4);
        System.out.println("Digits \"79\" count: " + res4.size() + " (Expected: 16)");
    }
}
