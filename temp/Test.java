package temp;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {20, 10, 40, 50, 25, 15};
        for (int i = 0; i < arr.length -1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j]< min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
        System.out.println(Arrays.toString(arr));
    }
}