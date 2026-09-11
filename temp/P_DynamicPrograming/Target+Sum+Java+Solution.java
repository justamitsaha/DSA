class Solution {
    private Map<String, Integer> map;
    
    public int findTargetSumWays(int[] nums, int target) {
        map = new HashMap<>();
        return f(0, target, nums);
    }
    
    // i = 3, target = 30
    // Key: "3-30" , Value: x
    private int f(int i, int target, int[] nums) {
        String key = i + "-" + target;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (i < nums.length - 1) {
            map.put(key, f(i + 1, target - nums[i], nums) + f(i + 1, target + nums[i], nums));
            return map.get(key);
        }
        else {
            if (Math.abs(target) == Math.abs(nums[i])) {
                if (nums[i] == 0) {
                    return 2;
                }
                else {
                    return 1;
                }
            }
            else {
                return 0;
            }
        }
    }
}