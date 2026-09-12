package leetcode.P_DynamicPrograming;

import java.util.Arrays;

/**
 * LeetCode 322: Coin Change
 * Difficulty: Medium
 * Link: https://leetcode.com/problems/coin-change/
 *
 * Algorithm:
 * - Dynamic Programming (Unbounded Knapsack / Minimum Coins)
 *
 * Concepts:
 * - Find the minimum number of coins needed to make up a given `amount` using unlimited coins of given denominations.
 * - Return -1 if the amount cannot be made.
 * - Bottom-Up DP Formulation:
 *   - Let `dp[i]` be the minimum number of coins required to form amount `i`.
 *   - Base Case: `dp[0] = 0` (0 coins needed for amount 0).
 *   - Initialize all other entries to `amount + 1` (a sentinel value representing infinity).
 *   - Transitions:
 *     For each target amount `x` from 1 to `amount`:
 *       For each coin denomination `coin`:
 *         if `x - coin >= 0`:
 *           `dp[x] = min(dp[x], dp[x - coin] + 1)`.
 *   - If `dp[amount] > amount`, the target amount is impossible -> return -1.
 *
 * Complexity:
 * - Time Complexity:  O(amount * n) - Where n is coins.length.
 * - Space Complexity: O(amount)      - 1D DP table of size amount + 1.
 */
public class LC0322_CoinChange {

    public int coinChange(int[] coins, int amount) {
        if (amount < 0 || coins == null || coins.length == 0) {
            return -1;
        }

        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        LC0322_CoinChange solver = new LC0322_CoinChange();

        // Scenario 1: [1, 2, 5], amount = 11 -> 3 (5 + 5 + 1)
        int[] coins1 = {1, 2, 5};
        System.out.println("Coins [1, 2, 5], amount 11: " + solver.coinChange(coins1, 11) + " (Expected: 3)");

        // Scenario 2: [2], amount = 3 -> -1 (impossible)
        int[] coins2 = {2};
        System.out.println("Coins [2], amount 3: " + solver.coinChange(coins2, 3) + " (Expected: -1)");

        // Scenario 3: [1], amount = 0 -> 0
        int[] coins3 = {1};
        System.out.println("Coins [1], amount 0: " + solver.coinChange(coins3, 0) + " (Expected: 0)");

        // Scenario 4: Greedy trap [1, 6, 9, 10], amount = 12 -> 2 (6 + 6, not 10 + 1 + 1)
        int[] coins4 = {1, 6, 9, 10};
        System.out.println("Coins [1, 6, 9, 10], amount 12: " + solver.coinChange(coins4, 12) + " (Expected: 2)");
    }
}
