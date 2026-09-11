class Solution {
    private int[][] dp;
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        dp = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        
        return lcs(text1, text2, n - 1, m - 1);
        
    }
    
    private int lcs(String text1, String text2, int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (dp[i][j] == -1) {
            if (text1.charAt(i) == text2.charAt(j)) {
                dp[i][j] = lcs(text1, text2, i - 1, j - 1) + 1;
            }
            else {
                dp[i][j] = Math.max(lcs(text1, text2, i - 1, j), lcs(text1, text2, i, j - 1));
            }
        }
        return dp[i][j];   
    }
}