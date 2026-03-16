public class E_FibonacciOptimization {
    
    private static long[] cache;

    public static void main(String[] args) {
        int n = 50; // O(2^n) would take forever for 50. O(n) is instant.
        cache = new long[n + 1];

        long start = System.nanoTime();
        long result = fibMemo(n);
        long end = System.nanoTime();

        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Time taken: " + (end - start) / 1_000_000.0 + " ms");
    }

    public static long fibMemo(int k) {
        if (k <= 1) return k;

        // 1. Check if we already have the answer in the cache
        if (cache[k] != 0) return cache[k];

        // 2. Otherwise, calculate it and SAVE it to the cache
        cache[k] = fibMemo(k - 1) + fibMemo(k - 2);
        
        return cache[k];
    }
}