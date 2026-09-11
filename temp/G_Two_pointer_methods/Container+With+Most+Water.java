class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        
        while (left < right) {
            int heightOfContainer = Math.min(height[left], height[right]);
            int width = right - left;
            
            int area = heightOfContainer*width;
            
            if (area > ans) {
                ans = area;
            }
            
            if (height[left] < height[right]) {
                left++;
            }
            else {
                right--;
            }
        }
        
        return ans;
    }
}
