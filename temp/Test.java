package temp;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {10, 345, 23, 189, 94, 68};


        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int n = 10;
        while (max / n > 1) {
            arr = countingSort(arr, n);
            n = n * 10;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static int[] countingSort(int[] arr, int base) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }


        int[] frequencyArray = new int[10];
        for (int i = 0; i < arr.length; i++) {
            var digit = arr[i]/base;
            frequencyArray[arr[i]] = frequencyArray[digit] + 1;

        }

        var sum = 0;
        int[] frequencyCumulative = new int[frequencyArray.length];
        for (int i = 0; i < frequencyArray.length; i++) {
            sum = sum + frequencyArray[i];
            frequencyCumulative[i] = sum;
        }

        int[] result = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            var item = arr[i]/10;
            var digit = arr[i];
            var position = frequencyCumulative[item] - 1;
            frequencyCumulative[item] = frequencyCumulative[item] - 1;
            result[position] = digit;
        }
        return result;
    }
}