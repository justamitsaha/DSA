class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[2]; 
        dp[0] = 0;
        dp[1] = 0;
        
        int result = 0;
        for(int i = 2; i <= n; i++) {
            result = Math.min(dp[1] + cost[i-1], dp[0] + cost[i-2]);
            dp[0] = dp[1];
            dp[1] = result;
        }
        
        return result;
    }
}