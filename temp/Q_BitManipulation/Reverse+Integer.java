class Solution {
    public int reverse(int x) {
        int reversedNum = 0;
        
        while (x != 0) {
            int digit = x%10;
            int newReversedNum = reversedNum*10 + digit;
            
            if (((newReversedNum - digit)/10) != reversedNum) {
                return 0;
            }
            
            reversedNum = newReversedNum;
            
            x /= 10;
        }
        
        return reversedNum;
    }
}
