import java.util.HashMap;

public class LongestSubString {
    public static void main(String[] args) {

    }

    public static  int lengthOfLongestSubstring(String s) {
        int count = 0;
        for(int i =0; i< s.length(); i++){
            HashMap<Character, Integer> map = new HashMap<>();
            if(null == map.put(s.charAt(i), i)){
                count ++;
            } else {
                count = 0;
                i = map.get(s.charAt(i)) +1;
            }
        }

        return count;
    }

}
