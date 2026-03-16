import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TimeComplexity {
    private static final int n = 100_000_000;
    private static final int[] arr = new int[n];
    private static HashMap<String, List<Long>> complexityTimes = new HashMap<>();

    /*
     * The 10,000-count loop is for the Just-In-Time (JIT) compiler. The JIT doesn't
     * care about the specific data in the array. Once it sees that code block run
     * 10,000 times, it says: "Okay, this logic is 'hot.' I will now compile these
     * instructions into highly optimized machine code
     * 
     * Data Warm-up (The CPU Cache's Job) :You could try to "warm up" the data by
     * loading it into the CPU cache, but there is a physical limitation: The
     * current CPU cache sizes (L1, L2, L3) are much smaller than 100 million
     * integers. Even if you accessed the entire array sequentially, only a small
     * portion would fit in the cache at any given time. So while you can attempt to
     * warm up the cache, it won't significantly reduce the time for an O(n)
     * operation on such a large dataset.
     */
    static {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        // By using i % 1000, the index will always stay between 0 and 999, no matter
        // how high i goes in your 10,000-count loop. This prevents an
        // ArrayIndexOutOfBoundsException
        for (int i = 0; i < 10000; i++) {
            int dummy = arr[i % 1000];
        }
        for (int i = 0; i < 100; i++) {
            linearSearchLogic(arr, -1);
        }
    }

    public static void main(String[] args) {
        List<Long> constantComplexity = new ArrayList<>();
        constantComplexity.add(measureConstantComplexity(0));
        constantComplexity.add(measureConstantComplexity(n / 2));
        constantComplexity.add(measureConstantComplexity(n - 1));
        complexityTimes.put("constantComplexity", constantComplexity);

        List<Long> linearComplexity = new ArrayList<>();

        linearComplexity.add(measureLinearComplexity(0));
        linearComplexity.add(measureLinearComplexity(n / 2));
        // We look for -1 to force the "Worst Case" (since -1 is not existing checking
        // every single element)
        linearComplexity.add(measureLinearComplexity(-1));
        complexityTimes.put("linearComplexity", linearComplexity);
        for (String key : complexityTimes.keySet()) {
            System.out.println(key + ": " + complexityTimes.get(key));
        }

    }

    public static long measureConstantComplexity(int index) {
        int iterations = 1000;
        long totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            int value = arr[index];
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime / iterations;
    }

    public static long measureLinearComplexity(int target) {
        // Fewer iterations because O(n) on 100M elements is slow!
        int iterations = 10;
        long totalTime = 0;

        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            linearSearchLogic(arr, target);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        long avgNano = totalTime / iterations;
        return avgNano;
    }

    // Extracted logic so JIT can optimize this specific method
    private static int linearSearchLogic(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target)
                return i;
        }
        return -1;
    }
}