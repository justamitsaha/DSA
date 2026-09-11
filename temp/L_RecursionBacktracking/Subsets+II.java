class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> subsets = new HashSet<>();
        
        Arrays.sort(nums);
        
        backtrack(subsets, new ArrayList<>(), nums, 0);
        
        return new ArrayList<>(subsets);
        
    }
    
    public void backtrack(Set<List<Integer>> subsets, List<Integer> currentSubset, int[] nums, int i) {
        if (i == nums.length) {
            subsets.add(new ArrayList<>(currentSubset));
        }
        else {
            currentSubset.add(nums[i]);
            backtrack(subsets, currentSubset, nums, i + 1);
            currentSubset.remove(currentSubset.size() - 1);
            
            backtrack(subsets, currentSubset, nums, i + 1);
        }
    }
}
