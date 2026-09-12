package leetcode.P_DynamicPrograming;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 397: Integer Replacement
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/integer-replacement/
 *
 * Algorithm:
 * - Method 1: Greedy Bit Manipulation (O(log n) Time, O(1) Space - Optimal)
 * - Method 2: Memoized Dynamic Programming / DFS (O(log n) Time, O(log n) Space)
 *
 * Concepts:
 * - Given a positive integer `n`:
 *   - If `n` is even, replace `n` with `n / 2`.
 *   - If `n` is odd, replace `n` with either `n + 1` or `n - 1`.
 * - Goal: Find the minimum operations to reduce `n` to 1.
 * - Greedy Bit Observation:
 *   - Dividing an even number by 2 shifts bits right by 1, removing a '0'.
 *   - For an odd number, we want the subsequent division by 2 to produce as many trailing zeros as possible!
 *   - Look at the last two bits `n & 3`:
 *     - If `(n & 3) == 3` (binary `..11`): Adding 1 (`n + 1`) produces `..100`, eliminating multiple '1's!
 *     - Exception: When `n == 3`, `3 -> 2 -> 1` takes 2 steps, whereas `3 -> 4 -> 2 -> 1` takes 3 steps. Thus, 3 should decrement!
 *     - If `(n & 3) == 1` (binary `..01`): Subtracting 1 (`n - 1`) produces `..000`.
 *   - Overflow Trap:
 *     - When `n = Integer.MAX_VALUE` (2147483647), `n + 1` overflows a 32-bit signed int to `Integer.MIN_VALUE`.
 *     - Using 64-bit `long` completely avoids overflow.
 *
 * Complexity:
 * - Method 1: Time: O(log n), Space: O(1)
 * - Method 2: Time: O(log n), Space: O(log n)
 */
public class LC0397_IntegerReplacement {

    /**
     * Optimal Greedy Bit Manipulation (O(1) space).
     */
    public int integerReplacement(int n) {
        long num = n;
        int operations = 0;

        while (num > 1) {
            if ((num & 1) == 0) {
                // Even: divide by 2
                num >>= 1;
            } else if (num == 3 || (num & 3) == 1) {
                // Odd ending in 01, or special case 3: decrement
                num--;
            } else {
                // Odd ending in 11: increment to produce consecutive trailing zeros
                num++;
            }
            operations++;
        }

        return operations;
    }

    /**
     * Memoized DFS approach using 64-bit long keys.
     */
    private final Map<Long, Integer> memo = new HashMap<>();

    public int integerReplacementMemo(int n) {
        memo.clear();
        return dfs((long) n);
    }

    private int dfs(long num) {
        if (num == 1) {
            return 0;
        }
        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        int steps;
        if (num % 2 == 0) {
            steps = 1 + dfs(num / 2);
        } else {
            steps = 1 + Math.min(dfs(num + 1), dfs(num - 1));
        }

        memo.put(num, steps);
        return steps;
    }

    public static void main(String[] args) {
        LC0397_IntegerReplacement solver = new LC0397_IntegerReplacement();

        // Scenario 1: 8 -> 3 steps (8 -> 4 -> 2 -> 1)
        System.out.println("Replace 8: " + solver.integerReplacement(8) + " (Expected: 3)");

        // Scenario 2: 7 -> 4 steps (7 -> 8 -> 4 -> 2 -> 1)
        System.out.println("Replace 7: " + solver.integerReplacement(7) + " (Expected: 4)");

        // Scenario 3: 4 -> 2 steps
        System.out.println("Replace 4: " + solver.integerReplacement(4) + " (Expected: 2)");

        // Scenario 4: Boundary case Integer.MAX_VALUE (2147483647) -> 32 steps
        System.out.println("Replace MAX_VALUE: " + solver.integerReplacement(Integer.MAX_VALUE) + " (Expected: 32)");
        System.out.println("Replace MAX_VALUE (Memo): " + solver.integerReplacementMemo(Integer.MAX_VALUE) + " (Expected: 32)");
    }
}
