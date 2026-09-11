class Solution {
    public int getSum(int a, int b) {
        int carry = 0;
        while (b != 0) {
            int sumWithoutCarry = a^b;
            carry = (a&b) << 1 ;
            b = carry;
            a = sumWithoutCarry;           
        }
        
        return a;
    }
}
