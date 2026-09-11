class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        
        for(int coin: coins) {
            if (coin <= amount) {
                dp[coin] = 1;
            }
        }
        // coins = [1, 6, 9, 10]
        // x = 8
        for(int x = 1; x <= amount; x++) {
            int ans = -1;
            for(int coin: coins) {
                if (coin > x) continue;
                if (dp[x - coin] != -1 && (ans == -1 || dp[x - coin] + 1 < ans)) {
                    ans = dp[x - coin] + 1;
                }
            }
            dp[x] = ans;
        }
        
        return dp[amount];
    }
}