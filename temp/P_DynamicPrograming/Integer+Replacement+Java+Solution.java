class Solution {
    private Map<Integer, Integer> ht;
    public int integerReplacement(int n) {
        ht = new HashMap<>();
        ht.put(1, 0);
        ht.put(2147483647, 32);
        return f(n);
    }
    
    int f(int x) {
        if (ht.containsKey(x)) {
            return ht.get(x);
        }
        int ans = 0;
        if (x%2 == 0) {
            ans = f(x/2) + 1;
        }
        else {
            ans = Math.min(f(x-1), f(x+1)) + 1;
        }
        ht.put(x, ans);
        return ans;
    }
}