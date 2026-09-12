package leetcode.E_Stacks;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 150: Evaluate Reverse Polish Notation
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Algorithm:
 * - Stack Evaluation of Postfix Expression (Reverse Polish Notation)
 *
 * Concepts:
 * - In Reverse Polish Notation (RPN), operators follow their operands: e.g. "3 4 +" means 3 + 4.
 * - An expression is evaluated using a Stack:
 *     - If the token is a number: push it onto the stack.
 *     - If the token is an operator (+, -, *, /):
 *         - Pop the top operand as `operand2` (right operand).
 *         - Pop the next operand as `operand1` (left operand).
 *         - Evaluate `operand1 OP operand2` (ORDER MATTERS for - and /).
 *         - Push the resulting value back onto the stack.
 * - Division between two integers should truncate toward zero (standard Java integer division).
 * - After processing all tokens, the final result remains as the single element on the stack.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Linear pass through all tokens.
 * - Space Complexity: O(n) - Stack stores at most n/2 numeric operands.
 */
public class LC0150_EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int operand2 = stack.pop(); // Note: operand2 was pushed last
                int operand1 = stack.pop(); // operand1 was pushed first

                int result = evaluate(token, operand1, operand2);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int evaluate(String op, int a, int b) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b; // Java integer division truncates toward zero
            default:  return 0;
        }
    }

    public static void main(String[] args) {
        LC0150_EvaluateReversePolishNotation solver = new LC0150_EvaluateReversePolishNotation();

        // Scenario 1: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) = 9
        String[] t1 = {"2", "1", "+", "3", "*"};
        System.out.println("Scenario 1: [\"2\", \"1\", \"+\", \"3\", \"*\"]");
        System.out.println("  Result:   " + solver.evalRPN(t1) + " | Expected: 9\n");

        // Scenario 2: ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) = (4 + 2) = 6
        String[] t2 = {"4", "13", "5", "/", "+"};
        System.out.println("Scenario 2: [\"4\", \"13\", \"5\", \"/\", \"+\"]");
        System.out.println("  Result:   " + solver.evalRPN(t2) + " | Expected: 6\n");

        // Scenario 3: Negative results and subtraction order ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
        String[] t3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("Scenario 3: Complex RPN expression");
        System.out.println("  Result:   " + solver.evalRPN(t3) + " | Expected: 22");
    }
}
