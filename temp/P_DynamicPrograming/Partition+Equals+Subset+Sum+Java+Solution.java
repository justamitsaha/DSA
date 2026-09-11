class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num: nums) {
            sum += num;
        }
        
        if (sum%2 != 0) {
            return false;
        }
        
        int target = sum/2;
        Boolean dp[][] = new Boolean[nums.length][target + 1];
        return f(0, target, nums, dp);
        
    }
    
    private Boolean f(int i, int target, int[] nums, Boolean[][] dp)  {
        if (target < 0) {
            return false;
        }
        else if (dp[i][target] != null) {
            return dp[i][target];
        }
        else if (i == nums.length - 1) {
            if (target == 0 || target == nums[i]) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            boolean isPossible = f(i + 1, target - nums[i], nums, dp) || f(i + 1, target , nums, dp);
            dp[i][target] = isPossible;
            return isPossible;
        }
    }
}