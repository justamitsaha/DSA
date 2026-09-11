class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            set.add(num);
        }
        
        int ans = 0;
        for(int num: nums) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (set.contains(currentNum + 1)) {
                    currentStreak++;
                    currentNum++;
                }
                if (currentStreak > ans) {
                    ans = currentStreak;
                }
            }
        }
        
        return ans;
    }
}
