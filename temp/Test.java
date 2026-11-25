package temp;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        int zero = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && zero >= 0){
                var temp = nums[i];
                nums[i] = nums[zero];
                nums[zero] = temp;
                zero = i;
            } else
                zero = i;
        }

        System.out.println(Arrays.toString(nums));
    }
}