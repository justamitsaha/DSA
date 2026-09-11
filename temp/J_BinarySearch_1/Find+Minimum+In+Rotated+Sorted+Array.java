class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int n = nums.length;
        int end = n - 1;
        
        int ans = nums[0];
        
        while (start <= end) {
            int mid = (start + end)/2;
            
            if (nums[mid] <= nums[n - 1]) {
                ans = nums[mid];
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        
        return ans;
        
    }
}
