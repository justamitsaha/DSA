class Solution {
    public List<String> letterCombinations(String digits) {
        
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
     
        String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        List<String> combinations = new ArrayList<>();
        String temp = "";
        
        backtrack(combinations, mapping, temp, 0, digits);
        
        return combinations;
    }
    
    public void backtrack(List<String> combinations, String[] mapping, String current, int i, String digits) {
        if (i == digits.length()) {
            combinations.add(current);
        }
        else {
            int digit = digits.charAt(i) - '0';
            String validLetters = mapping[digit];
            for(int j = 0; j < validLetters.length(); j++) {
                backtrack(combinations, mapping, current + validLetters.charAt(j), i + 1, digits);
            }
        }
    }
}
