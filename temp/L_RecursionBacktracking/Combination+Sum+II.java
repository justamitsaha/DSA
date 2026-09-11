class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        Set<List<Integer>> combinations = new HashSet<>();
        
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num: candidates) {
            if (freq.containsKey(num)) {
                freq.put(num, freq.get(num) + 1);
            }
            else {
                freq.put(num, 1);
            }
        }
        List<Integer> uniq = new ArrayList<>();
        for(int num: freq.keySet()) {
            uniq.add(num);
        }
        Collections.sort(uniq);
        
        backtrack(combinations, new ArrayList<>(), freq, uniq, target, 0);
        
        return new ArrayList<>(combinations);
    }
    
    public void backtrack(Set<List<Integer>> combinations, List<Integer> currentSubset, Map<Integer, Integer> freq, List<Integer> uniqueCandidates, int remainingTarget, int i) {
        
        if (i == uniqueCandidates.size()) {
            if (remainingTarget == 0) {
                combinations.add(new ArrayList<>(currentSubset));
            }
        }
        else {
            int count = freq.get(uniqueCandidates.get(i));
            for(int j = 0; j <= count && remainingTarget >= uniqueCandidates.get(i)*j; j++) {
                for(int k = 1; k <= j; k++) {
                    currentSubset.add(uniqueCandidates.get(i));
                }
                backtrack(combinations, currentSubset, freq, uniqueCandidates, remainingTarget - uniqueCandidates.get(i)*j, i + 1);
                
                for(int k = 1; k <= j; k++) {
                    currentSubset.remove(currentSubset.size() - 1);
                }
            }
        }
    }
}
