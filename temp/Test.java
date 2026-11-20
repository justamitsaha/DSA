package temp;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr ={7,9,18,4,11,19,27};


    }

    public static void mergeSort(int[] arr){
        int start = 0;

    }

    public static int[] merge(int[] arr, int start, int mid, int end){
        int[] result = new int[end];
        int i = start;
        int start2 = mid;
        while (start < start2 && mid <end){
            if(arr[start]< arr[mid]){
                result[i] = arr[start];
                start++;
            } else {
                result[i] = arr[mid];
                mid++;
            }
            i++;
        }

        System.out.println(start+""+mid);
        while (start < start2){
            result[i] = arr[start];
            start ++;
            i++;
        }

        while (mid < end){
            result[i] = arr[mid];
            mid ++;
            i++;
        }
        return result;
    }
}