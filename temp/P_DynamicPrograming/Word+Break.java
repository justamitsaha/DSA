class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> validWords = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        
        dp[0] = true;
        
        for(int i = 1; i <= n; i++) {
            for(int start = 0; start < i && dp[i] == false; start++) {
                dp[i] = dp[i] || (dp[start] && validWords.contains(s.substring(start, i)));
            }
        }
                                  
        return dp[n];
    }
}
