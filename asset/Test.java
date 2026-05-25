
public class Test {

    static int[] unsortedArray = new int[1_000_000];

    static {
        for (int i = 0; i < unsortedArray.length; i++) {
            unsortedArray[i] = (int) (Math.random() * 1_000_000);
        }
    }

    public static void main(String[] args) {
        // int[] unsortedArray = {4, 1, 3, 6, 8, 9, 5, 2, 7, 10, 5};
        // mergeSort(unsortedArray);
        // System.out.println(arrayToString(unsortedArray));
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
        // System.out.println(binarySearch(arr, 1));

        for (int i = 0; i < 10; i++) {
            power(2, i);
        }

    }

    /*
     * The time complexity of
     */
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                System.out.println("high");
                low = mid + 1;
            } else {
                System.out.println("low");
                high = mid - 1;
            }
        }
        return -1;
    }

    public static long power(long x, int n) {
        long result = 1;
        int count = 0;
        while (n > 0) {
            if (n % 2 == 1)
                result *= x;
            x *= x;
            n /= 2;
            count++;
        }
        System.out.println("Count: " + count + " Result: " + result);
        return result;
    }

    public static void mergeSort(int[] arr) {
        if (arr.length < 2)
            return;
        int mid = arr.length / 2;
        int[] left = java.util.Arrays.copyOfRange(arr, 0, mid);
        int[] right = java.util.Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left); // log n recursive calls
        mergeSort(right);

        merge(arr, left, right); // O(n) merge process
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        // System.out.println("Parent Array: " + arrayToString(arr) + " Left: " +
        // arrayToString(left) + " Right: " + arrayToString(right));
        int l = 0, r = 0, i = 0; // l for left, r for right, i for main
        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                // arr[i++] = left[l++];
                arr[i] = left[l];
                i++;
                l++;
            } else {
                // arr[i++] = right[r++];
                arr[i] = right[r];
                i++;
                r++;
            }
        }
        // One element will be left we don't know if its left or right so we check both
        // and add to main
        while (l < left.length) {
            // arr[i++] = left[l++];
            arr[i++] = left[l++];
            i++;
            l++;
        }
        while (r < right.length) {
            // arr[i++] = right[r++];
            arr[i] = right[r];
            i++;
            r++;
        }
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1)
                sb.append(", ");
        }
        return sb.toString();
    }

}
