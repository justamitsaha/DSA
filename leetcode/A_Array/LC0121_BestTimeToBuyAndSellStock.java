package leetcode.A_Array;

/**
 * LeetCode 121: Best Time to Buy and Sell Stock
 * Difficulty: Easy
 * Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Algorithm:
 * - One-Pass Greedy / Peak-Valley Algorithm
 *
 * Concepts:
 * - As we iterate through the daily prices, we maintain the minimum buying price seen so far (`minPriceSoFar`).
 * - For each day, the potential profit if sold today is `prices[i] - minPriceSoFar`.
 * - We update `maxProfit` if today's profit is greater than the previous maximum.
 * - This guarantees that the buy day always precedes the sell day in a single linear scan.
 *
 * Complexity:
 * - Time Complexity:  O(n) - Single pass through the prices array.
 * - Space Complexity: O(1) - Constant auxiliary memory (only tracks min price and max profit).
 */
public class LC0121_BestTimeToBuyAndSellStock {

    /**
     * Finds the maximum profit possible from a single buy and sell transaction.
     *
     * @param prices daily stock prices
     * @return maximum profit, or 0 if no profit is achievable
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int minPriceSoFar = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Calculate potential profit if selling at current day's price
            int currentProfit = prices[i] - minPriceSoFar;

            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
            }

            // Update the minimum buying price seen so far
            if (prices[i] < minPriceSoFar) {
                minPriceSoFar = prices[i];
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        LC0121_BestTimeToBuyAndSellStock solver = new LC0121_BestTimeToBuyAndSellStock();

        // Scenario 1: Standard case with profitable valley and peak
        // Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int result1 = solver.maxProfit(prices1);
        System.out.println("Scenario 1 - Standard: " + result1 + " | Expected: 5");

        // Scenario 2: Monotonically decreasing prices (no profit possible)
        int[] prices2 = {7, 6, 4, 3, 1};
        int result2 = solver.maxProfit(prices2);
        System.out.println("Scenario 2 - Decreasing: " + result2 + " | Expected: 0");

        // Scenario 3: Monotonically increasing prices
        // Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5 - 1 = 4
        int[] prices3 = {1, 2, 3, 4, 5};
        int result3 = solver.maxProfit(prices3);
        System.out.println("Scenario 3 - Increasing: " + result3 + " | Expected: 4");

        // Scenario 4: Edge Case - Single day or identical prices
        int[] prices4 = {5};
        int[] prices5 = {3, 3, 3, 3};
        System.out.println("Scenario 4 - Single Day: " + solver.maxProfit(prices4) + " | Expected: 0");
        System.out.println("Scenario 4 - Flat Prices: " + solver.maxProfit(prices5) + " | Expected: 0");
    }
}
