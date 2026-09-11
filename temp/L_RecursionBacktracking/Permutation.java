class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(answer, new ArrayList<Integer>(), new HashMap<Integer, Boolean>(), 0, nums);
        return answer;
    }
    
    public void backtrack(List<List<Integer>> answer, List<Integer> current, HashMap<Integer, Boolean> used, int i, int[] nums) {
        if (i == nums.length) {
            answer.add(new ArrayList<>(current));
        }
        else {
            for(int num: nums) {
                if (!used.containsKey(num) || used.get(num) == false) {
                    current.add(num);
                    used.put(num, true);
                    backtrack(answer, current, used, i + 1, nums);
                    current.remove(current.size() - 1);
                    used.put(num, false);
                }
            }
        }
    }
}
