class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        
        String ans = "";
        boolean[][] dp = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
            ans = s.substring(i, i + 1);
        }
        
        for(int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i+1] = true;
                ans = s.substring(i, i + 2);
            }    
        }
        
        for(int length = 3; length <= n; length++) {
            for(int start = 0; start + length -1 < n; start++) {
                int end = start + length - 1;
                if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1] == true) {
                    dp[start][end] = true;
                    ans = s.substring(start, end + 1);
                }
            }
        }
        
        return ans;
    }
}