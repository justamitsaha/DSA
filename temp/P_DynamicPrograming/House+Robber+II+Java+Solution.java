class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int option1 = calculate(nums, 0, n - 2);
        int option2 = calculate(nums, 1, n - 1);
        
        return Math.max(option1, option2);
    }
    
    public int calculate(int[] nums, int start, int end) {
        int n = nums.length;
        int[] dp = new int[n];
        
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        
        for(int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[end];
    }
}